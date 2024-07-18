package lk.earth.earthuniversity.controller;

import lk.earth.earthuniversity.dao.WarehouseDao;
import lk.earth.earthuniversity.entity.Category;
import lk.earth.earthuniversity.entity.Warehouse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "/warehouses")
public class WarehouseController {

    @Autowired
    private WarehouseDao warehousedao;

    @GetMapping(path = "/list",produces = "application/json")
    public List<Warehouse> get(){

        List<Warehouse> warehouses = this.warehousedao.findAll();

        warehouses = warehouses.stream().map(
                warehouse -> { Warehouse w = new Warehouse();
                    w.setId(warehouse.getId());
                    w.setLocation(warehouse.getLocation());
                    return w; }
        ).collect(Collectors.toList());

        return warehouses;
    }

}
