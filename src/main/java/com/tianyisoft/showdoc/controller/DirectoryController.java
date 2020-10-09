package com.tianyisoft.showdoc.controller;

import com.tianyisoft.showdoc.entity.Directory;
import com.tianyisoft.showdoc.service.DirectoryService;
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
    public List<Directory> tree(@PathParam("pid")Integer pid) {
        return directoryService.findByPid(pid);
    }

    @GetMapping("/directories/{id}")
    public Directory show(@PathVariable("id")Integer id) {
        System.out.println(directoryService.findById(id));
        return directoryService.findById(id);
    }
}
