package com.tianyisoft.showdoc.controller;

import com.tianyisoft.showdoc.exception.CustomException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ExceptionHandler {
    @org.springframework.web.bind.annotation.ExceptionHandler(CustomException.class)
    public Map<String, Object> customerException(CustomException e) {
        Map<String, Object> map = new HashMap<>();
        map.put("field", e.getField());
        map.put("message", e.getMessage());
        return map;
    }
}
