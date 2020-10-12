package com.tianyisoft.showdoc.exception;

public class CustomException extends Exception {
    private final String field;
    private String message = "";

    public CustomException(String field) {
        this.field = field;
    }

    public CustomException(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "CustomException{" +
                "field='" + field + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
