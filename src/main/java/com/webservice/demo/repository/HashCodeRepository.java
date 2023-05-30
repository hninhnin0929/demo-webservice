package com.webservice.demo.repository;

import com.webservice.demo.model.EntityStatus;
import com.webservice.demo.model.HashCode;
import com.webservice.demo.model.TestProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


public interface HashCodeRepository  extends JpaRepository<HashCode, Long>, JpaSpecificationExecutor<HashCode> {
    public HashCode findByHashCodeNoAndEntityStatus(String hashCodeNo, EntityStatus entityStatus);
}
