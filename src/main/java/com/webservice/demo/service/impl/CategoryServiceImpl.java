package com.webservice.demo.service.impl;

import com.webservice.demo.model.Category;
import com.webservice.demo.repository.CategoryRepository;
import com.webservice.demo.service.CategoryService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    CategoryRepository categoryRepository;

    private Logger logger = Logger.getLogger(HashCodeServiceImpl.class);

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public String save(Category category) {

        try{
            if (category.isBoIdRequired(category.getBoId()))
                category.setBoId("CAT" + categoryRepository.count());
            categoryRepository.save(category);
        }catch (Exception e){
            logger.error("Error: " + e.getMessage());
            return "FAIL";
        }
        return "SUCCESS";
    }
}
