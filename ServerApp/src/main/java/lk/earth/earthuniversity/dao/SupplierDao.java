package lk.earth.earthuniversity.dao;

import lk.earth.earthuniversity.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface SupplierDao extends JpaRepository<Supplier,Integer> {
    Supplier findByRegid(String regid);
    Supplier findByName(String name);
    Optional<Supplier> findById(Integer id);

    @Query("select s from Supplier s where s.id = :id")
    Supplier findByMyId(@Param("id") Integer id);

    @Query("SELECT NEW Supplier (s.id, s.contactperson) FROM Supplier s")
    List<Supplier> findByContactPerson();
}
