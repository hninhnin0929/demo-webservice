package com.webservice.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonView;
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

    private String uniqueId;

    private String name;

    private String sellingPrice;

    private String manufacturer;

    private String noOfAvailableStock;

    private String modelNumber;

    private String dimension;

    private String shippingWeight;

    private String productUrl;

    private String description;

    @JsonProperty
    @JsonView(Views.Thin.class)
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "hashcodeno")
    private HashCode hashcode = new HashCode();

    @JsonView(Views.Thin.class)
    @ManyToMany(mappedBy = "productList")
    private List<Category> categoryList = new ArrayList<Category>();

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
