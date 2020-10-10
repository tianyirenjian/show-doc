package com.tianyisoft.showdoc.entity;

import at.favre.lib.crypto.bcrypt.BCrypt;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private Boolean enabled;
    private LocalDateTime activeUntil;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public void setPassword(String password) {
        this.password = BCrypt.withDefaults().hashToString(12, password.toCharArray());
    }
}
