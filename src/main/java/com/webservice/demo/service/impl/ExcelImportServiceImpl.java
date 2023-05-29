package com.webservice.demo.service.impl;


import com.webservice.demo.model.*;
import com.webservice.demo.repository.CategoryRepository;
import com.webservice.demo.repository.HashCodeRepository;
import com.webservice.demo.service.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelImportServiceImpl implements ExcelImportService {

    private HashCodeService hashCodeService;

    private CategoryService categoryService;

    private CategoryRepository categoryRepository;

    private ReviewService reviewService;


    private HashCodeRepository hashCodeRepository;

    private ProductService productService;

    public ExcelImportServiceImpl(HashCodeService hashCodeService,
                                  CategoryService categoryService,
                                  CategoryRepository categoryRepository,
                                  ReviewService reviewService,
                                  ProductService productService,
                                  HashCodeRepository hashCodeRepository) {
        this.hashCodeService = hashCodeService;
        this.categoryService = categoryService;
        this.categoryRepository = categoryRepository;
        this.reviewService = reviewService;
        this.productService = productService;
        this.hashCodeRepository = hashCodeRepository;
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
        XSSFSheet sheet = getSheetFromFile("E:\\test-data\\hashcode.xlsx");

        if (sheet == null)
            return new Response(false, "File not found.");

        for (int row = 1; row < 500; row++) {
            HashCode hashCode = new HashCode(sheet.getRow(row));
            hashCodeService.save(hashCode);
        }
        return new Response(true, "Success");
    }

    @Override
    public Response importCategory() {
        XSSFSheet sheet = getSheetFromFile("E:\\test-data\\category.xlsx");

        if (sheet == null)
            return new Response(false, "File not found.");

        for (int row = 1; row < 500; row++) {

            String rawCategory = sheet.getRow(row).getCell(4) + "";
            String[] categories = rawCategory.split("\\|");

            for(String eachCategory: categories){
                Category existingCategory = categoryRepository.findByNameAndEntityStatus(eachCategory.trim(), EntityStatus.ACTIVE);
                if(existingCategory != null)
                    continue;
                Category category = new Category(eachCategory.trim());
                categoryService.save(category);
            }
        }
        return new Response(true, "Success");
    }

    @Override
    public Response importProduct() {

        XSSFSheet sheet = getSheetFromFile("E:\\test-data\\product.xlsx");
        if(sheet == null)
            return new Response(false, "File not found");

        for(int rowCount = 1; rowCount < 500; rowCount++){
            Row row = sheet.getRow(rowCount);
            Product product = new Product(row);
            HashCode hashCode = hashCodeRepository.findByHashCodeNoAndEntityStatus(String.valueOf(row.getCell(11)), EntityStatus.ACTIVE);
            if(hashCode != null)
                product.setHashcode(hashCode);

            product.getCategoryList().addAll(getCategoryList(row, product));
            product.getReviewList().addAll(getReviewList(row));
            productService.save(product);
            System.out.println("Count: ............" + rowCount);
        }
        return new Response(true, "Success");
    }

    private List<Category> getCategoryList(Row row, Product product){
        List<Category> categoryList = new ArrayList<>();
        String rawCategory = row.getCell(10) + "";
        String[] categories = rawCategory.split("\\|");
        for(String eachCategory: categories){
            Category category = categoryRepository.findByNameAndEntityStatus(eachCategory, EntityStatus.ACTIVE);
            if(category != null){
                category.getProductList().add(product);
                categoryList.add(category);
            }
        }
        return categoryList;
    }

    private List<Review> getReviewList(Row row){
        List<Review> reviewList = new ArrayList<>();
        String[] reviewRaws = row.getCell(12).toString().split("\\|");
        for(String reviewRaw: reviewRaws){
            if(reviewRaw.length() < 1)
                continue;
            Review review = new Review(reviewRaw);
            reviewService.save(review);
            reviewList.add(review);
        }
        return reviewList;
    }
}
