package com.tianyisoft.showdoc.entity;


import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class Directory implements Serializable {
    private Integer id;
    private String name;
    private Integer pid;
    private Directory parent;
    private List<Directory> children;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
