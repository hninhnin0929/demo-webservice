package com.webservice.demo.controller;

import com.webservice.demo.controller.model.TestProductID;
import com.webservice.demo.model.TestProduct;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping("/")
    public String hello(){
        return "Hello World";
    }

    @PostMapping("/testRoute")
    public ResponseEntity<TestProductID> createProduct(@RequestBody final TestProductID product){
        TestProductID result = new TestProductID(UUID.randomUUID().toString());
        System.out.println(product);
        return ResponseEntity.status(HttpStatus.CREATED)
                .contentType(MediaType.APPLICATION_JSON)
                .body(result);
    }

    @GetMapping("/testRoute")
    public List<TestProduct> getAllProducts(){
        List<TestProduct> result = new ArrayList<>();

        result.add(new TestProduct("1","Product1", new BigDecimal("258.33")));
        result.add(new TestProduct("2","Product2", new BigDecimal("258.33")));
        result.add(new TestProduct("3","Product3", new BigDecimal("258.33")));
        return result;
    }

    @GetMapping("/testRoute/{id}")
    public TestProduct getProductById(@PathVariable final String id){
        System.out.println(id);
        return new TestProduct("888","Returning a single product", new BigDecimal("458.44"));
    }

    @PatchMapping("/testRoute")
    public void patchProduct(@RequestBody final TestProductID product){
        System.out.println(product);
    }
    @DeleteMapping("/testRoute/{id}")
    public void deleteProduct(@PathVariable final String id){
        System.out.println(id);
    }
}
