package lk.earth.earthuniversity.report;

import lk.earth.earthuniversity.report.dao.CountByDesignaitonDao;
import lk.earth.earthuniversity.report.dao.MaterialCountByCategoryDao;
import lk.earth.earthuniversity.report.entity.CountByDesignation;
import lk.earth.earthuniversity.report.entity.MaterialCountByCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/reports")
public class ReportController {

    @Autowired
    private CountByDesignaitonDao countbydesignaitondao;
    @Autowired
    private MaterialCountByCategoryDao materialcountbycategorydao;

    @GetMapping(path ="/countbydesignation",produces = "application/json")
    public List<CountByDesignation> get() {

        List<CountByDesignation> designations = this.countbydesignaitondao.countByDesignation();
        long totalCount = 0;

        for (CountByDesignation countByDesignation : designations) {
            totalCount += countByDesignation.getCount();
        }

        for (CountByDesignation countByDesignation : designations) {
            long count = countByDesignation.getCount();
            double percentage = (double) count / totalCount * 100;
            percentage = Math.round(percentage * 100.0) / 100.0;
            countByDesignation.setPercentage(percentage);
        }

        return designations;
    }

    @GetMapping(path = "/materialcountbycategory", produces = "application/json")
    public List<MaterialCountByCategory> getMaterialCountByCategory(){

        List<MaterialCountByCategory> materialCountByCategories = this.materialcountbycategorydao.materialCountByCategory();

        long totalCount = 0;

        for (MaterialCountByCategory materialCountByCategory : materialCountByCategories){
            totalCount += materialCountByCategory.getCount();
        }

        return materialCountByCategories;
    }
}


