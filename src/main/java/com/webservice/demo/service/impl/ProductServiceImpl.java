package com.webservice.demo.service.impl;

import com.webservice.demo.dto.SearchProductDto;
import com.webservice.demo.model.*;
import com.webservice.demo.repository.ProductRepository;
import com.webservice.demo.service.ProductService;
import org.apache.log4j.Logger;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    ProductRepository productRepository;
    private Logger logger = Logger.getLogger(HashCodeServiceImpl.class);

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public String save(Product product) {
        try {
            if (product.isBoIdRequired(product.getBoId()))
                product.setBoId("PRO" + productRepository.count());
            productRepository.save(product);
        } catch (Exception e) {
            logger.error("Error: " + e.getMessage());
            return "FAIL";
        }
        return "SUCCESS";
    }

    @Override
    public Response searchByNameAndType(SearchProductDto searchDto) {

        Response response = new Response(true, "success");
        List<Product> productList = productRepository.findByNameContainingAndEntityStatus(searchDto.getName(), EntityStatus.ACTIVE);
        List<Product> productList1 = new ArrayList<>();
        response.setDataList(productList);
        return response;
    }
}
