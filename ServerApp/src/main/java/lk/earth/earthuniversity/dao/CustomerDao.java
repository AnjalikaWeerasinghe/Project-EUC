package lk.earth.earthuniversity.dao;

import lk.earth.earthuniversity.entity.Customer;
import lk.earth.earthuniversity.entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CustomerDao extends JpaRepository<Customer,Integer> {

    @Query("select i from Customer i where i.regid=:regid")
    Customer findByRegId(@Param("regid")String regid);

    @Query("select i from Customer i where i.name=:name")
    Customer findByName(@Param("name")String name);

    @Query("select i from Customer i where i.id=:id")
    Customer findByMyId(@Param("id") Integer id);

}
