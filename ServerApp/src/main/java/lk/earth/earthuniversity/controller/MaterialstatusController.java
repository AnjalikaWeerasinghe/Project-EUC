package lk.earth.earthuniversity.controller;

import lk.earth.earthuniversity.dao.MaterialstatusDao;
import lk.earth.earthuniversity.entity.Materialstatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "/materialstatuses")
public class MaterialstatusController {

    @Autowired
    private MaterialstatusDao materialstatusdao;

    @GetMapping(path = "/list",produces = "application/json")
    public List<Materialstatus> get(){

        List<Materialstatus> materialstatuses = this.materialstatusdao.findAll();

        materialstatuses = materialstatuses.stream().map(
                materialstatus -> {Materialstatus ms = new Materialstatus();
                ms.setId(materialstatus.getId());
                ms.setStatus(materialstatus.getStatus());
                return ms; }
        ).collect(Collectors.toList());

        return materialstatuses;
    }
}
