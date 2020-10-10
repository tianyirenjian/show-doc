package com.tianyisoft.showdoc.service;

import com.tianyisoft.showdoc.entity.Directory;
import com.tianyisoft.showdoc.mapper.DirectoryMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirectoryService {
    private final DirectoryMapper mapper;

    public DirectoryService(DirectoryMapper mapper) {
        this.mapper = mapper;
    }

    public Directory findById(Integer id) {
        return mapper.findById(id);
    }

    public int create(Directory directory) {
        return mapper.create(directory);
    }

    public List<Directory> findByPid(Integer pid) {
        return mapper.findByPid(pid);
    }
}
