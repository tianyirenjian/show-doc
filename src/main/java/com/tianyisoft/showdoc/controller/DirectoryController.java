package com.tianyisoft.showdoc.controller;

import com.tianyisoft.showdoc.entity.Directory;
import com.tianyisoft.showdoc.service.DirectoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
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
    public List<Directory> tree(@PathParam("pid")Integer pid) {
        return directoryService.findByPid(pid);
    }

    @PostMapping("/directories")
    @ApiOperation(value = "添加目录")
    public Directory store(@RequestBody Directory directory) {
        directoryService.create(directory);
        return directoryService.findById(directory.getId());
    }

    @GetMapping("/directories/{id}")
    @ApiOperation(value = "获取目录详情")
    public Directory show(@PathVariable("id")Integer id) {
        return directoryService.findById(id);
    }
}
