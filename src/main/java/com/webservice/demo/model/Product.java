package com.webservice.demo.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Product")
@Data
public class Product {
    @Id
    private  String id;

    @Column(name = "createddate")
    private  String createddate;

    @Column(name = "modifieddate")
    private String modifieddate;

    @Column(name = "recordstatus")
    private  int recordstatus;

    @Column(name = "status")
    private int status;

    public Product() {
    }
}
