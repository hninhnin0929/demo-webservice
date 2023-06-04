package com.webservice.demo.service;

import com.webservice.demo.dto.SearchProductDto;
import com.webservice.demo.model.Product;
import com.webservice.demo.model.Response;


public interface ProductService {
    public String save(Product product);

    public Response searchByNameAndType(SearchProductDto searchDto);

    public Response findByFullTextSearch(SearchProductDto searchDto);
}
