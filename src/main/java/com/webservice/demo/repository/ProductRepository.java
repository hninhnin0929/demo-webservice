package com.webservice.demo.repository;

import com.webservice.demo.model.EntityStatus;
import com.webservice.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    public List<Product> findByNameContainingAndEntityStatus(String name, EntityStatus entityStatus);

    @Query(value = "SELECT * FROM product WHERE " +
            "entityStatus='ACTIVE' AND " +
            "MATCH (name, description) AGAINST (?1)", nativeQuery = true)
    List<Product> findByFullTextSearch(String keyword);


}
