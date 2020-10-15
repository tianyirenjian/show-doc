package com.tianyisoft.showdoc.service;

import com.tianyisoft.showdoc.dto.DirectoryDto;
import com.tianyisoft.showdoc.entity.Directory;
import com.tianyisoft.showdoc.mapper.DirectoryMapper;
import com.tianyisoft.showdoc.utils.Util;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DirectoryService {
    private final DirectoryMapper mapper;

    public DirectoryService(DirectoryMapper mapper) {
        this.mapper = mapper;
    }

    public List<Directory> findByPid(Integer pid) {
        return mapper.findByPid(pid);
    }

    public int create(DirectoryDto directory) {
        Directory dir = new Directory();
        BeanUtils.copyProperties(directory, dir);
        mapper.create(dir);
        return dir.getId();
    }

    public Directory findById(Integer id) {
        Directory dir = mapper.findById(id);
        if (dir == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "目录不存在");
        }
        return dir;
    }

    public int update(Integer id, DirectoryDto directoryDto) {
        Directory old = findById(id);
        BeanUtils.copyProperties(directoryDto, old, Util.getNullPropertyNames(directoryDto));
        return mapper.update(old);
    }

    public int delete(Integer id) {
        List<Directory> children = mapper.findByPid(id);
        List<Integer> ids = flattenTree(children).stream().mapToInt(Directory::getId).boxed().collect(Collectors.toList());
        ids.add(id);
        return mapper.delete(ids);
    }

    private List<Directory> flattenTree(List<Directory> tree) {
        List<Directory> list = new ArrayList<>();
        for (Directory dir: tree) {
            list.add(dir);
            if (dir.getChildren().size() > 0) {
                list.addAll(flattenTree(dir.getChildren()));
            }
        }
        return list;
    }
}
