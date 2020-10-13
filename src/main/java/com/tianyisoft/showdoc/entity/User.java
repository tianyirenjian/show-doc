package com.tianyisoft.showdoc.entity;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("users")
public class User {
    public static Integer PER_PAGE = 15;

    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private Boolean enabled;
    private LocalDateTime activeUntil;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public void setPassword(String password) {
        this.password = BCrypt.withDefaults().hashToString(12, password.toCharArray());
    }
}
