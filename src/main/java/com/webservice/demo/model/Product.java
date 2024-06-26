package com.webservice.demo.model;

import com.fasterxml.jackson.annotation.*;
import com.webservice.demo.entity.Views;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.poi.ss.usermodel.Row;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "product")
@Getter
@Setter
public class Product  extends AbstractModel {

    @JsonView(Views.Thin.class)
    @Column(name = "uniqueId")
    private String uniqueId;

    @JsonView(Views.Thin.class)
    @Column(name = "name")
    private String name;

    @JsonView(Views.Thin.class)
    @Column(name = "sellingPrice")
    private String sellingPrice;

    @JsonView(Views.Thin.class)
    @Column(name = "manufacturer")
    private String manufacturer;

    @JsonView(Views.Thin.class)
    @Column(name = "noOfAvailableStock")
    private String noOfAvailableStock;

    @JsonView(Views.Thin.class)
    @Column(name = "modelNumber")
    private String modelNumber;

    @JsonView(Views.Thin.class)
    @Column(name = "dimension")
    private String dimension;

    @JsonView(Views.Thin.class)
    @Column(name = "shippingWeight")
    private String shippingWeight;

    @JsonView(Views.Thin.class)
    @Column(name = "productUrl")
    private String productUrl;

    @Column(name = "description", length = 1000)
    private String description;

//    @JsonIgnore
//    @JsonManagedReference("product-hashcode")
    @JsonView(Views.Thin.class)
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "hashCode", referencedColumnName = "id")
    private HashCode hashcode;

//    @JsonIgnore
    @JsonManagedReference("product-category")
    @JsonView(Views.Thin.class)
    @ManyToMany(mappedBy = "productList")
    private List<Category> categoryList = new ArrayList<Category>();

//    @JsonIgnore
    @JsonManagedReference("product-review")
    @JsonView(Views.Thin.class)
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "product_review", joinColumns = @JoinColumn(name = "productId"), inverseJoinColumns = @JoinColumn(name = "reviewId"))
    private List<Review> reviewList = new ArrayList<Review>();

    public Product() {
    }

    public Product(Row row) {
        setUniqueId(row.getCell(0) + "");
        setName(row.getCell(1) + "");
        setSellingPrice(row.getCell(2) + "");
        setManufacturer(row.getCell(3) + "");
        setNoOfAvailableStock(row.getCell(4) + "");
        setModelNumber(row.getCell(5) == null ? "" : row.getCell(5) + "");
        setDimension(row.getCell(6) == null ? "" : row.getCell(6) + "");
        setShippingWeight(row.getCell(7) == null ? "" : row.getCell(7) + "");
        setProductUrl(row.getCell(8) + "");
        setDescription(row.getCell(9) == null ? "" : row.getCell(9) + "");
    }

}
