package com.tianyisoft.showdoc.exception;

public class UsernameExistsException extends CustomException {
    public UsernameExistsException() {
        super("username", "用户名已存在");
    }

    public UsernameExistsException(String field) {
        super(field, "用户名已存在");
    }

    public UsernameExistsException(String field, String message) {
        super(field, message);
    }

    @Override
    public String toString() {
        return "UsernameExistsException{" +
                "field='" + this.getField() + '\'' +
                "message='" + this.getMessage() + '\'' +
                '}';
    }
}
