package lk.earth.earthuniversity.controller;

import lk.earth.earthuniversity.dao.UsertypeDao;
import lk.earth.earthuniversity.entity.Usertype;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping(value = "/usertypes")
public class UsertypeController {

    @Autowired
    private UsertypeDao usertypeDao;

    @GetMapping(path ="/list", produces = "application/json")
    public List<Usertype> get() {

        List<Usertype> usertypes = this.usertypeDao.findAll();

        usertypes = usertypes.stream().map(
                usertype -> { Usertype d = new Usertype();
                    d.setId(usertype.getId());
                    d.setName(usertype.getName());
                    return d; }
        ).collect(Collectors.toList());

        return usertypes;

    }
}
