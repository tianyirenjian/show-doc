package com.tianyisoft.showdoc.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@TableName("directories")
public class Directory {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer pid;
    private Directory parent;
    private List<Directory> children;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
