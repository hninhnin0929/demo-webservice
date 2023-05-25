package com.webservice.demo.model;

import com.webservice.demo.util.CommonUtil;
import com.webservice.demo.util.SyskeyUtil;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.MappedSuperclass;


@Data
@MappedSuperclass
public class AbstractModel {

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


    public AbstractModel() {
        setId(SyskeyUtil.getSyskey());
        setStatus(1);
        setCreateddate(CommonUtil.getCurrentDate());
        setModifieddate(CommonUtil.getCurrentDate());
        setRecordstatus(1);
    }
}
