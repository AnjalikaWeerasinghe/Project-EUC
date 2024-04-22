package lk.earth.earthuniversity.controller;

import lk.earth.earthuniversity.dao.CustomertypeDao;
import lk.earth.earthuniversity.entity.Customertype;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "/customertypes")
public class CustomertypeController {

    @Autowired
    private CustomertypeDao customertypedao;

    @GetMapping(path = "/list",produces = "application/json")
    public List<Customertype> get(){

        List<Customertype> customertypes = this.customertypedao.findAll();

        customertypes = customertypes.stream().map(
                customertype -> {Customertype c = new Customertype();
                    c.setId(customertype.getId());
                    c.setName(customertype.getName());
                    return c; }
        ).collect(Collectors.toList());

        return customertypes;
    }

}
