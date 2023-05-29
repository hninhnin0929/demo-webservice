package com.webservice.demo.repository;

import com.webservice.demo.model.EntityStatus;
import com.webservice.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    public List<Product> findByNameContainingAndEntityStatus(String name, EntityStatus entityStatus);
}
