package com.webservice.demo.service.impl;

import com.webservice.demo.model.HashCode;
import com.webservice.demo.model.Response;
import com.webservice.demo.service.ExcelImportService;
import com.webservice.demo.service.HashCodeService;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;

@Service
public class ExcelImportServiceImpl implements ExcelImportService {


    private HashCodeService hashCodeService;

    public ExcelImportServiceImpl(HashCodeService hashCodeService) {
        this.hashCodeService = hashCodeService;
    }

    private XSSFSheet getSheetFromFile(String filePath) {
        try {
            FileInputStream file = new FileInputStream(new File(filePath));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            return workbook.getSheetAt(0);
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            return null;
        }
    }
    @Override
    public Response importHashCode() {
        XSSFSheet sheet = getSheetFromFile("D:\\Job\\selectd-data\\hashcode.xlsx");

        if (sheet == null)
            return new Response(false, "File not found.");

        for (int row = 1; row < 500; row++) {
            HashCode hashCode = new HashCode(sheet.getRow(row));
            hashCodeService.save(hashCode);
        }
        return new Response(true, "Success");
    }
}
