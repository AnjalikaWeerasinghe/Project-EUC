package lk.earth.earthuniversity.controller;

import lk.earth.earthuniversity.dao.SupplierDao;
import lk.earth.earthuniversity.entity.Employee;
import lk.earth.earthuniversity.entity.Supplier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CrossOrigin
@RestController
@RequestMapping(value = "/suppliers")
public class SupplierController {

    @Autowired
    private SupplierDao supplierdao;

    @GetMapping(produces = "application/json")
    public List<Supplier> get(@RequestParam HashMap<String, String> params) {

        List<Supplier> suppliers = this.supplierdao.findAll();

        if(params.isEmpty())  return suppliers;

        String name = params.get("name");
        String regid= params.get("regid");
        String contactperson= params.get("contactperson");
        String email= params.get("email");
        String doregistered= params.get("doregistered");

        Stream<Supplier> estream = suppliers.stream();

        if(regid!=null) estream = estream.filter(e -> e.getRegid().equals(regid));
        if(name!=null) estream = estream.filter(e -> e.getName().contains(name));
        if(contactperson!=null) estream = estream.filter(e -> e.getContactperson().contains(contactperson));
        if(email!=null) estream = estream.filter(e -> e.getEmail().contains(email));
        if(doregistered!=null) estream = estream.filter(e -> e.getDoregistered().equals(doregistered));

        return estream.collect(Collectors.toList());

    }

    @GetMapping(path ="/list",produces = "application/json")
    public List<Supplier> get() {

        List<Supplier> suppliers = this.supplierdao.findByContactPerson();

        suppliers = suppliers.stream().map(
                supplier -> {
                    Supplier s = new Supplier(supplier.getId(), supplier.getName());
                    return  s;
                }
        ).collect(Collectors.toList());

        return suppliers;

    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> add(@RequestBody Supplier supplier){

        HashMap<String,String> response = new HashMap<>();
        String errors="";

        if(supplierdao.findByRegid(supplier.getRegid())!=null)
            errors = errors+"<br> Existing Registration Number";
        if(supplierdao.findByName(supplier.getName())!=null)
            errors = errors+"<br> Existing Supplier";

        System.out.println(supplier.getDoregistered());

        if(errors=="")
            supplierdao.save(supplier);
        else errors = "Server Validation Errors : <br> "+errors;

        response.put("id",String.valueOf(supplier.getId()));
        response.put("url","/suppliers/"+supplier.getId());
        response.put("errors",errors);

        return response;
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> update(@RequestBody Supplier supplier){

        HashMap<String,String> response = new HashMap<>();
        String errors="";

        Supplier sup1 = supplierdao.findByRegid(supplier.getRegid());
        Supplier sup2 = supplierdao.findByName(supplier.getName());

        if(sup1!=null && supplier.getId()!=sup1.getId())
            errors = errors+"<br> Existing Registration Number";
        if(sup2!=null && supplier.getId()!=sup2.getId())
            errors = errors+"<br> Existing Name";

        if(errors=="") supplierdao.save(supplier);
        else errors = "Server Validation Errors : <br> "+errors;

        response.put("id",String.valueOf(supplier.getId()));
        response.put("url","/suppliers/"+supplier.getId());
        response.put("errors",errors);

        return response;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> delete(@PathVariable Integer id){

        System.out.println(id);

        HashMap<String,String> response = new HashMap<>();
        String errors="";

        Supplier sup1 = supplierdao.findByMyId(id);

        if(sup1==null)
            errors = errors+"<br> Supplier Does Not Existed";

        if(errors=="") supplierdao.delete(sup1);
        else errors = "Server Validation Errors : <br> "+errors;

        response.put("id",String.valueOf(id));
        response.put("url","/suppliers/"+id);
        response.put("errors",errors);

        return response;
    }

}
