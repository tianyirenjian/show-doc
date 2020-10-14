package com.tianyisoft.showdoc.controller;

import com.tianyisoft.showdoc.exception.CustomException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(CustomException.class)
    @ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
    public Map<String, Object> customerException(CustomException e) {
        Map<String, Object> map = new HashMap<>();
        map.put("field", e.getField());
        map.put("message", e.getMessage());
        return map;
    }

    @ExceptionHandler(ResponseStatusException.class)
    public HttpEntity<Map<String, Object>> httpError(ResponseStatusException e) {
        Map<String, Object> map = new HashMap<>();
        map.put("message", e.getReason());
        return new ResponseEntity<>(map, e.getStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
    public Map<String, Object> notValid(MethodArgumentNotValidException e) {
        ObjectError err = e.getBindingResult().getAllErrors().get(0);
        Map<String, Object> map = new HashMap<>();
        map.put("field", ((FieldError)err).getField());
        map.put("message", err.getDefaultMessage());
        return map;
    }
}
