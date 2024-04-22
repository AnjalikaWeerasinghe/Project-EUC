package lk.earth.earthuniversity.controller;

import lk.earth.earthuniversity.dao.CustomerstatusDao;
import lk.earth.earthuniversity.entity.Customerstatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "/customerstatuses")
public class CustomerstatusController {

    @Autowired
    private CustomerstatusDao customerstatusdao;

    @GetMapping(path = "/list",produces = "application/json")
    public List<Customerstatus> get(){

        List<Customerstatus> customerstatuses = this.customerstatusdao.findAll();

        customerstatuses = customerstatuses.stream().map(
                customerstatus -> {Customerstatus cs = new Customerstatus();
                    cs.setId(customerstatus.getId());
                    cs.setStatus(customerstatus.getStatus());
                    return cs; }
        ).collect(Collectors.toList());

        return customerstatuses;
    }

}
