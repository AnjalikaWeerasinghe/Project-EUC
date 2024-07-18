package lk.earth.earthuniversity.controller;

import lk.earth.earthuniversity.dao.CountryDao;
import lk.earth.earthuniversity.dao.SupcountryDao;
import lk.earth.earthuniversity.entity.Country;
import lk.earth.earthuniversity.entity.Supcountry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "/supcountries")
public class SupcountryController {

    @Autowired
    private SupcountryDao supcountrydao;

    @GetMapping(path = "/list",produces = "application/json")
    public List<Supcountry> get(){

        List<Supcountry> supcountries = this.supcountrydao.findAll();

        supcountries = supcountries.stream().map(
                supcountry -> {Supcountry c = new Supcountry();
                    c.setId(supcountry.getId());
                    c.setName(supcountry.getName());
                    return c; }
        ).collect(Collectors.toList());

        return supcountries;
    }

}
