package com.webservice.demo.repository;

import com.webservice.demo.model.HashCode;
import com.webservice.demo.model.TestProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HashCodeRepository  extends JpaRepository<HashCode, String> {
}
