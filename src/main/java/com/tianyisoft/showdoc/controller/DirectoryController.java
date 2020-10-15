package com.tianyisoft.showdoc.controller;

import com.tianyisoft.showdoc.dto.DirectoryDto;
import com.tianyisoft.showdoc.entity.Directory;
import com.tianyisoft.showdoc.group.Create;
import com.tianyisoft.showdoc.group.Update;
import com.tianyisoft.showdoc.service.DirectoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api")
public class DirectoryController {
    private final DirectoryService directoryService;

    public DirectoryController(DirectoryService directoryService) {
        this.directoryService = directoryService;
    }

    @GetMapping("/directories")
    @ApiOperation(value = "获取目录树")
    public List<Directory> tree(@RequestParam(value = "pid", required = false, defaultValue = "0") Integer pid) {
        return directoryService.findByPid(pid);
    }

    @PostMapping("/directories")
    @ApiOperation(value = "添加目录")
    public Directory store(@RequestBody @Validated(Create.class) DirectoryDto directory) {
        Integer id = directoryService.create(directory);
        return directoryService.findById(id);
    }

    @GetMapping("/directories/{id}")
    @ApiOperation(value = "获取目录详情")
    public Directory show(@PathVariable("id")Integer id) {
        return directoryService.findById(id);
    }

    @PutMapping("/directories/{id}")
    @ApiOperation(value = "更新目录")
    public Directory update(@PathVariable("id") Integer id, @RequestBody @Validated(Update.class) DirectoryDto directory) {
        directoryService.update(id, directory);
        return directoryService.findById(id);
    }

    @DeleteMapping("/directories/{id}")
    @ApiOperation(value = "删除目录")
    public int delete(@PathVariable("id") Integer id) {
        return directoryService.delete(id);
    }
}
