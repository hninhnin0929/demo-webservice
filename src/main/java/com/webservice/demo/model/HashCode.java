package com.webservice.demo.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonView;
import com.webservice.demo.entity.Views;
import com.webservice.demo.util.CommonUtil;
import com.webservice.demo.util.SyskeyUtil;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.poi.ss.usermodel.Row;

import jakarta.persistence.*;

@Entity
@Table(name = "hashcode")
@Getter
@Setter
public class HashCode extends AbstractModel {

    @JsonView(Views.Thin.class)
    @Column(name = "hashcodeno")
    private String hashCodeNo;

    @JsonView(Views.Thin.class)
    @Column(name = "description")
    private String description;

    @JsonView(Views.Thin.class)
    @Column(name = "parent")
    private String parent;

    @JsonView(Views.Thin.class)
    @Column(name = "level")
    private String level;

//    @JsonIgnore
//    @OneToOne(mappedBy = "hashcode")
//    private Product product;

    public HashCode() {

    }
    public HashCode(Row row) {
        setHashCodeNo(row.getCell(1) + "");
        setDescription(row.getCell(2) + "");
        setParent(row.getCell(3) + "");
        setLevel(row.getCell(4) + "");
    }

}
