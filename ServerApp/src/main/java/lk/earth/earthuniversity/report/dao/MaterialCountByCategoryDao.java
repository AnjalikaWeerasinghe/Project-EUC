package lk.earth.earthuniversity.report.dao;

import lk.earth.earthuniversity.report.entity.MaterialCountByCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MaterialCountByCategoryDao extends JpaRepository<MaterialCountByCategory, Integer> {

    @Query("select NEW MaterialCountByCategory(c.name, COUNT (*)) from Material m, Subcategory s, Category c where m.subcategory.id = s.id and s.category.id = c.id group by c.id")
    List<MaterialCountByCategory>  materialCountByCategory();
}
