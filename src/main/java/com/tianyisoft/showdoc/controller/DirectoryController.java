package com.tianyisoft.showdoc.controller;

import com.tianyisoft.showdoc.entity.Directory;
import com.tianyisoft.showdoc.service.DirectoryService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Directory dir = directoryService.findById(id);
        if (dir == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "目录不存在");
        }
        return dir;
    }

    @PutMapping("/directories/{id}")
    @ApiOperation(value = "更新目录")
    public Directory update(@PathVariable("id") Integer id, @RequestBody Map<String, Object> changes) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", id);
        map.put("changes", changes);
        directoryService.update(map);
        return directoryService.findById(id);
    }

    @DeleteMapping("/directories/{id}")
    @ApiOperation(value = "删除目录")
    public int delete(@PathVariable("id") Integer id) {
        return directoryService.delete(id);
    }
}
