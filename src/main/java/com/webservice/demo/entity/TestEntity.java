package com.webservice.demo.entity;


import lombok.Data;

import javax.persistence.*;


//@Entity
//@Table(name = "test")
//@Getter
//@Setter
@Data
@Entity
@Table(name="test")
public class TestEntity {
//
//    @JsonView(Views.Thin.class)
//    private  String id;
//
//    @JsonView(Views.Thin.class)
//    private String description;
//
//    @JsonView(Views.Thin.class)
//    private BigDecimal price;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private String price;

}
