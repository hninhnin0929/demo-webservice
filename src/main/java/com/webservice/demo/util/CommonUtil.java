package com.webservice.demo.util;

import java.text.SimpleDateFormat;
import java.util.Date;


public class CommonUtil {

    public static final String BOID_REQUIRED = -1 + "";
    public static String getCurrentDate() {
        return new SimpleDateFormat("yyyyMMdd").format(new Date());
    }
}
