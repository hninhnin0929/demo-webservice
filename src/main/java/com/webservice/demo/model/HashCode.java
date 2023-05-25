package com.webservice.demo.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.webservice.demo.util.CommonUtil;
import com.webservice.demo.util.SyskeyUtil;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.poi.ss.usermodel.Row;

import jakarta.persistence.*;

@Entity
@Table(name = "hashcode")
@Data
public class HashCode extends AbstractModel {

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

    @Column(name = "parent")
    private String parent;

    @Column(name = "level")
    private String level;


    public HashCode() {
        super();
    }
    public HashCode(Row row) {
        setId(SyskeyUtil.getSyskey());
        setCreateddate(CommonUtil.getCurrentDate());
        setModifieddate(CommonUtil.getCurrentDate());
        setRecordstatus(1);
        setStatus(1);

        setCode(row.getCell(1) + "");
        setDescription(row.getCell(2) + "");
        setParent(row.getCell(3) + "");
        setLevel(row.getCell(4) + "");
    }

}
