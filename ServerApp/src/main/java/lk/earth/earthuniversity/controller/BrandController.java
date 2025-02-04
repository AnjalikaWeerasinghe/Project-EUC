package lk.earth.earthuniversity.controller;

import lk.earth.earthuniversity.dao.BrandDao;
import lk.earth.earthuniversity.entity.Brand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "/brands")
public class BrandController {

    @Autowired
    private BrandDao branddao;

    @GetMapping(path = "/list",produces = "application/json")
    public List<Brand> get(@RequestParam HashMap<String, String>params){

        List<Brand> brands = new ArrayList<Brand>();

        if (params.isEmpty()) brands = this.branddao.findAll();

        String categoryid = params.get("categoryid");

        if (categoryid != null) brands = this.branddao.findAllByBrand(Integer.parseInt(categoryid));

        return brands;
    }

}
