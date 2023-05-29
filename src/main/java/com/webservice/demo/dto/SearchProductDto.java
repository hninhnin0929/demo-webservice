package com.webservice.demo.dto;

import com.webservice.demo.model.SearchType;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchProductDto {

    @NotEmpty(message = "Please Insert Product Name!")
    private  String name;

    private SearchType type;
}
