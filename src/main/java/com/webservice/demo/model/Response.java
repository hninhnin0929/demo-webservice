package com.webservice.demo.model;

import com.fasterxml.jackson.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
//@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class Response<T>  implements Serializable{

    private static final long serialVersionUID = 8992463810222512826L;

//    @JsonIgnore
    private List<T> dataList = new ArrayList<T>();

    private boolean status;

    private String message;

    public Response() {
    }

    public Response(boolean status, String message) {
        setStatus(status);
        setMessage(message);
    }


}
