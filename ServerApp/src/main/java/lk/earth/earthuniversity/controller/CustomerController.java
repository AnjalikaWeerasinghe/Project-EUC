package lk.earth.earthuniversity.controller;

import lk.earth.earthuniversity.dao.CustomerDao;
import lk.earth.earthuniversity.entity.Customer;
import lk.earth.earthuniversity.entity.Material;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CrossOrigin
@RestController
@RequestMapping(value = "/customers")
public class CustomerController {

    @Autowired
    private CustomerDao customerdao;

    @GetMapping(produces = "application/json")
    public List<Customer> get(@RequestParam HashMap<String,String> params){

        List<Customer> customers = this.customerdao.findAll();

        if (params.isEmpty()) return customers;

        String customername = params.get("customername");
        String customerstatusid = params.get("customerstatusid");
        //String categoryid = params.get("categoryid");

        Stream<Customer> istream = customers.stream();

        if (customername!=null) istream = istream.filter(i->i.getName().contains(customername));
        if (customerstatusid!=null) istream = istream.filter(i->i.getCustomerstatus().getId() == Integer.parseInt(customerstatusid));
        //if (categoryid!=null) istream = istream.filter(i->i.getSubcategory().getCategory().getId() == Integer.parseInt(categoryid));

        return istream.collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> add(@RequestBody Customer Customer){

        HashMap<String,String> response = new HashMap<>();
        String errors = "";

        if (customerdao.findByRegId(Customer.getRegid())!=null)
            errors = errors+"<br> Existing Registration Id";
        if (customerdao.findByName(Customer.getName())!=null)
            errors = errors+"<br> Existing Name";

        if (errors == "") customerdao.save(Customer);
        else errors = "Server validation errors : <br>" +errors;

        response.put("id",String.valueOf(Customer.getId()));
        response.put("url","/customers/"+Customer.getId());
        response.put("errors",errors);

        return response;
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> update(@RequestBody Customer Customer){

        HashMap<String,String> response = new HashMap<>();
        String errors = "";

        Customer cus1 = (Customer) customerdao.findByMyId(Customer.getId());
        Customer cus2 = (Customer) customerdao.findByName(Customer.getName());

        if(cus1 != null && Customer.getId() != cus1.getId())
            errors = errors+"<br> Existing Id";
        if(cus2 != null && Customer.getName() != cus2.getName())
            errors = errors+"<br> Existing Name";

        if(errors == "") customerdao.save(Customer);
        else errors = "Server validation errors : <br>" +errors;

        response.put("id",String.valueOf(Customer.getId()));
        response.put("url","/customers/"+Customer.getId());
        response.put("errors",errors);

        return response;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> delete(@PathVariable Integer id){

        HashMap<String,String> response = new HashMap<>();
        String errors = "";

        Customer cus = customerdao.findByMyId(id);

        if (cus==null) errors = errors+"<br> Customer not found";

        if (errors=="") customerdao.delete(cus);
        else errors = "Server validation errors : <br>" +errors;

        response.put("id",String.valueOf(id));
        response.put("url","/customers/"+id);
        response.put("errors",errors);

        return response;
    }
}
