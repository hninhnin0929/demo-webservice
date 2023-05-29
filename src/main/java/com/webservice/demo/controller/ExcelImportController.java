package com.webservice.demo.controller;

import com.webservice.demo.model.Response;
import com.webservice.demo.service.ExcelImportService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/import")
public class ExcelImportController {

    ExcelImportService excelImportService;

    public ExcelImportController(ExcelImportService excelImportService) {
        this.excelImportService = excelImportService;
    }

    @GetMapping("/importHashCodeFile")
    public Response importHashCodeFile() {
        return excelImportService.importHashCode();
    }

    @GetMapping("/importCategoryFile")
    public Response importCategoryFile(){
        return excelImportService.importCategory();
    }

    @GetMapping("/importProductFile")
    public  Response importProductFile(){
        return excelImportService.importProduct();
    }
}
