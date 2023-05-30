package com.webservice.demo.repository;

import com.webservice.demo.model.Category;
import com.webservice.demo.model.EntityStatus;
import org.hibernate.loader.ast.internal.CacheEntityLoaderHelper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CategoryRepository extends JpaRepository<Category, Long>, JpaSpecificationExecutor<Category> {
    public Category findByNameAndEntityStatus(String name, EntityStatus entityStatus);
}
