package lk.earth.earthuniversity.controller;

import lk.earth.earthuniversity.dao.EmployeeDao;
import lk.earth.earthuniversity.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CrossOrigin
@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {

    @Autowired
    private EmployeeDao employeedao;

    @GetMapping(produces = "application/json")
    public List<Employee> get(@RequestParam HashMap<String, String> params) {

        List<Employee> employees = this.employeedao.findAll();

        if(params.isEmpty())  return employees;

        String regid = params.get("regid");
        String genderid= params.get("genderid");
        String fullname= params.get("fullname");
        String designationid= params.get("designationid");
        String nic= params.get("nic");

        Stream<Employee> estream = employees.stream();

        if(designationid!=null) estream = estream.filter(e -> e.getDesignation().getId()==Integer.parseInt(designationid));
        if(genderid!=null) estream = estream.filter(e -> e.getGender().getId()==Integer.parseInt(genderid));
        if(regid!=null) estream = estream.filter(e -> e.getRegid().equals(regid));
        if(nic!=null) estream = estream.filter(e -> e.getNic().contains(nic));
        if(fullname!=null) estream = estream.filter(e -> e.getFullname().contains(fullname));

        return estream.collect(Collectors.toList());

    }

    @GetMapping(path ="/list",produces = "application/json")
    public List<Employee> get() {

        List<Employee> employees = this.employeedao.findAllNameId();

        employees = employees.stream().map(
                employee -> {
                    Employee e = new Employee(employee.getId(), employee.getCallingname());
                    return  e;
                }
        ).collect(Collectors.toList());

        return employees;

    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> add(@RequestBody Employee employee){

        HashMap<String,String> response = new HashMap<>();
        String errors="";

        if(employeedao.findByRegid(employee.getRegid())!=null)
            errors = errors+"<br> Existing Registration Number";
        if(employeedao.findByNic(employee.getNic())!=null)
            errors = errors+"<br> Existing NIC";

        System.out.println(employee.getDoregistered());

        if(errors=="")
            employeedao.save(employee);
        else errors = "Server Validation Errors : <br> "+errors;

        response.put("id",String.valueOf(employee.getId()));
        response.put("url","/employees/"+employee.getId());
        response.put("errors",errors);

        return response;
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> update(@RequestBody Employee employee){

        HashMap<String,String> response = new HashMap<>();
        String errors="";

        Employee emp1 = employeedao.findByRegid(employee.getRegid());
        Employee emp2 = employeedao.findByNic(employee.getNic());

        if(emp1!=null && employee.getId()!=emp1.getId())
            errors = errors+"<br> Existing Number";
        if(emp2!=null && employee.getId()!=emp2.getId())
            errors = errors+"<br> Existing NIC";

        if(errors=="") employeedao.save(employee);
        else errors = "Server Validation Errors : <br> "+errors;

        response.put("id",String.valueOf(employee.getId()));
        response.put("url","/employees/"+employee.getId());
        response.put("errors",errors);

        return response;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> delete(@PathVariable Integer id){

        System.out.println(id);

        HashMap<String,String> response = new HashMap<>();
        String errors="";

        Employee emp1 = employeedao.findByMyId(id);

        if(emp1==null)
            errors = errors+"<br> Employee Does Not Existed";

        if(errors=="") employeedao.delete(emp1);
        else errors = "Server Validation Errors : <br> "+errors;

        response.put("id",String.valueOf(id));
        response.put("url","/employees/"+id);
        response.put("errors",errors);

        return response;
    }
    
}
