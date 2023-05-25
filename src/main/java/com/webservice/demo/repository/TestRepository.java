package com.webservice.demo.repository;

import com.webservice.demo.model.TestProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface TestRepository extends JpaRepository<TestProduct, String>{
//    List<TestProduct> findByDescription(String name);
}
