package com.webservice.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.webservice.demo.entity.Views;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import com.webservice.demo.model.Product;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "category")
@Getter
@Setter
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Category extends AbstractModel {

    @JsonView(Views.Thin.class)
    @Column(name = "name")
    private String name;

    @JsonBackReference("product-category")
    @JsonView(Views.Thin.class)
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "category_product", joinColumns = @JoinColumn(name = "categoryId"), inverseJoinColumns = @JoinColumn(name = "productId"))
    private List<Product> productList = new ArrayList<Product>();


    public Category(){}

    public Category(String name){
        setName(name);
    }
}