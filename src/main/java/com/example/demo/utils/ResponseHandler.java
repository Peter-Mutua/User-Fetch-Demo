package com.example.demo.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ResponseHandler {
    //    String timestamp = new Date().toString();
    public static ResponseEntity<Object> generateResponse(String message, HttpStatus status, Object responseObj) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("message", message);
        map.put("status", status.value());
        map.put("data", responseObj);
        map.put("timestamp", new Date().toString());

        return new ResponseEntity<Object>(map, status);
    }
}
