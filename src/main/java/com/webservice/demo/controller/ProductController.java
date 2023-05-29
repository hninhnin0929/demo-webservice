package com.webservice.demo.controller;

import com.webservice.demo.dto.SearchProductDto;
import com.webservice.demo.model.Response;
import com.webservice.demo.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("product")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @ResponseBody
    @PostMapping("/searchProduct")
    public Response searchByNameAndType(@RequestBody @Valid SearchProductDto searchProductDto){

        System.out.println("Hello, this is product controller");
        System.out.println(searchProductDto.getName());

        return productService.searchByNameAndType(searchProductDto);
    }
}
