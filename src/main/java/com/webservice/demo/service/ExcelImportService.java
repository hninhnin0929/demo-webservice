package com.webservice.demo.service;


import com.webservice.demo.model.Response;

public interface ExcelImportService {

    public Response importHashCode();

    public Response importCategory();

    public Response importProduct();
}
