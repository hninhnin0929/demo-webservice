package com.webservice.demo.model;
//
//import com.webservice.demo.util.CommonUtil;
//import com.webservice.demo.util.SyskeyUtil;
//import jakarta.persistence.Column;
//import jakarta.persistence.Id;
//import lombok.Data;
//import lombok.Getter;
//import lombok.Setter;
//
//import javax.persistence.MappedSuperclass;
//
//
//@Data
//@MappedSuperclass
//public class AbstractModel {
//
//    @Id
//    private  String id;
//
//    @Column(name = "createddate")
//    private  String createddate;
//
//    @Column(name = "modifieddate")
//    private String modifieddate;
//
//    @Column(name = "recordstatus")
//    private  int recordstatus;
//
//    @Column(name = "status")
//    private int status;
//
//
//    public AbstractModel() {
//        setId(SyskeyUtil.getSyskey());
//        setStatus(1);
//        setCreateddate(CommonUtil.getCurrentDate());
//        setModifieddate(CommonUtil.getCurrentDate());
//        setRecordstatus(1);
//    }
//}


import com.fasterxml.jackson.annotation.JsonView;
import com.webservice.demo.util.DateUtils;
import com.webservice.demo.util.SyskeyUtil;
import lombok.Getter;
import lombok.Setter;

import jakarta.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@MappedSuperclass
public class AbstractModel implements Serializable {

    private static final long serialVersionUID = -4248379706483318919L;

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private long Id = 0;

    @Column(name = "code", nullable = false)
    private String boId = "";

    @Column(name = "entityStatus")
    @Enumerated(EnumType.STRING)
    private EntityStatus entityStatus;

    private String createdDate;

    private String modifiedDate;

    public AbstractModel() {
        setId(SyskeyUtil.getSyskey());
        setBoId(SystemConstant.BOID_REQUIRED);
        setEntityStatus(EntityStatus.ACTIVE);
        setCreatedDate(DateUtils.currentDateTime());
        setModifiedDate(DateUtils.currentDateTime());
    }

    public boolean isBoIdRequired(String boId) {
        return SystemConstant.BOID_REQUIRED.equals(boId);
    }

}
