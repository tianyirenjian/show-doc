package com.tianyisoft.showdoc.dto;

import com.tianyisoft.showdoc.group.Create;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class UserDto {
    @NotEmpty(groups = Create.class, message = "用户名不能为空")
    public String username;
    @NotEmpty(groups = Create.class, message = "密码不能为空")
    public String password;
}
