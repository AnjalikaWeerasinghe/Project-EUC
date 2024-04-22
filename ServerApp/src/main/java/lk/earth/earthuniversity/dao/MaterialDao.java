package lk.earth.earthuniversity.dao;

import lk.earth.earthuniversity.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MaterialDao extends JpaRepository<Material,Integer> {

    @Query("select i from Material i where i.itemcode=:itemcode")
    Material findByItemCode(@Param("itemcode")String itemcode);

    @Query("select i from Material i where i.name=:name")
    Material findByName(@Param("name")String name);

    @Query("select i from Material i where i.id=:id")
    Material findByMyId(@Param("id") Integer id);

}
