package com.webservice.demo.model;

import com.webservice.demo.util.CommonUtil;
import com.webservice.demo.util.SyskeyUtil;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import org.apache.poi.ss.usermodel.Row;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Category")
@Data
public class Category implements Serializable {

    private static final long serialVersionUID = -4248379706483318919L;

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

    @Column(name = "code")
    private String code;

    @Column(name = "description")
    private String description;

//    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JoinTable(name = "Category_Product", joinColumns = @JoinColumn(name = "categoryId"), inverseJoinColumns = @JoinColumn(name = "productId"))
//    private List<Product> productList = new ArrayList<Product>();


    public  Category(){

    }
    public Category(Row row) {
        setId(SyskeyUtil.getSyskey());
        setCreateddate(CommonUtil.getCurrentDate());
        setModifieddate(CommonUtil.getCurrentDate());
        setRecordstatus(1);
        setStatus(1);

        setCode("CAT00"+ CommonUtil.BOID_REQUIRED);
        setDescription(row.getCell(1) + "");

    }
}
