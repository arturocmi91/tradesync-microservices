package com.microservice.catalog.util;

public class Operations {
    public static String trimBrackets(String message){
        return  message.replaceAll("[\\[\\]]","");
    }


}
