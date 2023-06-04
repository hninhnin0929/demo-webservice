package com.webservice.demo.service.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.webservice.demo.dto.SearchProductDto;
import com.webservice.demo.model.*;
import com.webservice.demo.repository.ProductRepository;
import com.webservice.demo.service.ProductService;
import org.apache.log4j.Logger;
import org.apache.poi.ss.formula.functions.T;
import org.hibernate.Hibernate;
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
        switch (searchDto.getType()) {
            case Product:
                response.setDataList(productList);
                break;
            case Review:
                productList.stream().forEach((product) -> {
                    response.getDataList().addAll(product.getReviewList());
                });
                break;
            case Category:
                List<Category> categoryList = new ArrayList<Category>();
                productList.stream().forEach((product) -> {
                    categoryList.addAll(product.getCategoryList()
                            .stream()
                            .filter(c -> !categoryList.contains(c))
                            .collect(Collectors.toList()));
                });
                response.setDataList(categoryList);
                break;
            case HashCode:
//                for (Product product: productList)
//                    Hibernate.initialize(product.getHashcode());
                List<HashCode> hashCodeList = productList.stream()
                        .map(product ->
                                product.getHashcode())
                        .collect(Collectors.toList());
                System.out.println(hashCodeList.get(0).getHashCodeNo());
                response.setDataList(hashCodeList);
                break;
            default:
                break;
        }
        System.out.println("no full text = " + response.getDataList().size());
        return response;
    }

    @Override
    public Response findByFullTextSearch(SearchProductDto searchDto) {

        Response response = new Response(true, "success");
        List<Product> productList = productRepository.findByFullTextSearch(searchDto.getName());
        switch (searchDto.getType()) {
            case Product:
                response.setDataList(productList);
                break;
            case Review:
                productList.stream().forEach((product) -> {
                    response.getDataList().addAll(product.getReviewList());
                });
                break;
            case Category:
                List<Category> categoryList = new ArrayList<Category>();
                productList.stream().forEach((product) -> {
                    categoryList.addAll(product.getCategoryList()
                            .stream()
                            .filter(c -> !categoryList.contains(c))
                            .collect(Collectors.toList()));
                });
                response.setDataList(categoryList);
                break;
            case HashCode:
                List<HashCode> hashCodeList = productList.stream()
                        .map(product ->
                                product.getHashcode())
                        .collect(Collectors.toList());
                System.out.println(hashCodeList.get(0).getHashCodeNo());
                response.setDataList(hashCodeList);
                break;
            default:
                break;
        }
        System.out.println("full text = " + response.getDataList().size());
        return response;
    }
}
