package lk.earth.earthuniversity.controller;

import lk.earth.earthuniversity.dao.MaterialDao;
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
@RequestMapping(value = "/materials")
public class MaterialController {

    @Autowired
    private MaterialDao materialdao;

    @GetMapping(produces = "application/json")
    public List<Material> get(@RequestParam HashMap<String,String> params){

        List<Material> materials = this.materialdao.findAll();

        if (params.isEmpty()) return materials;

        String materialname = params.get("materialname");
        String materialstatusid = params.get("materialstatusid");
        String categoryid = params.get("categoryid");

        Stream<Material> istream = materials.stream();

        if (materialname!=null) istream = istream.filter(i->i.getName().contains(materialname));
        if (materialstatusid!=null) istream = istream.filter(i->i.getMaterialstatus().getId() == Integer.parseInt(materialstatusid));
        if (categoryid!=null) istream = istream.filter(i->i.getSubcategory().getCategory().getId() == Integer.parseInt(categoryid));

        return istream.collect(Collectors.toList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> add(@RequestBody Material Material){

        HashMap<String,String> response = new HashMap<>();
        String errors = "";

        if (materialdao.findByItemCode(Material.getItemcode())!=null)
            errors = errors+"<br> Existing Code";
        if (materialdao.findByName(Material.getName())!=null)
            errors = errors+"<br> Existing Name";

        if (errors == "") materialdao.save(Material);
        else errors = "Server validation errors : <br>" +errors;

        response.put("id",String.valueOf(Material.getId()));
        response.put("url","/materials/"+Material.getId());
        response.put("errors",errors);

        return response;
    }

    @PutMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> update(@RequestBody Material Material){

        HashMap<String,String> response = new HashMap<>();
        String errors = "";

        Material mat1 = (Material) materialdao.findByItemCode(Material.getItemcode());
        Material mat2 = (Material) materialdao.findByName(Material.getName());

        if(mat1 != null && Material.getItemcode() != mat1.getItemcode())
            errors = errors+"<br> Existing Item Code";
        if(mat2 != null && Material.getName() != mat2.getName())
            errors = errors+"<br> Existing Name";

        if(errors == "") materialdao.save(Material);
        else errors = "Server validation errors : <br>" +errors;

        response.put("id",String.valueOf(Material.getId()));
        response.put("url","/materials/"+Material.getId());
        response.put("errors",errors);

        return response;
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public HashMap<String,String> delete(@PathVariable Integer id){

        HashMap<String,String> response = new HashMap<>();
        String errors = "";

        Material mat = materialdao.findByMyId(id);

        if (mat==null) errors = errors+"<br> Material not found";

        if (errors=="") materialdao.delete(mat);
        else errors = "Server validation errors : <br>" +errors;

        response.put("id",String.valueOf(id));
        response.put("url","/materials/"+id);
        response.put("errors",errors);

        return response;
    }
    
}
