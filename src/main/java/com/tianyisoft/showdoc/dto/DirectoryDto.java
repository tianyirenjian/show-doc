package com.tianyisoft.showdoc.dto;

import com.tianyisoft.showdoc.group.Create;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class DirectoryDto {
    @NotEmpty(groups = Create.class, message = "目录名不能为空")
    private String name;
    @NotNull(groups = Create.class, message = "上级目录不能为空")
    private Integer pid;
}
