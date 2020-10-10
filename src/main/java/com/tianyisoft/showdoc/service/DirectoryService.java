package com.tianyisoft.showdoc.service;

import com.tianyisoft.showdoc.entity.Directory;
import com.tianyisoft.showdoc.mapper.DirectoryMapper;
import org.springframework.stereotype.Service;

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

    public int create(Directory directory) {
        return mapper.create(directory);
    }

    public Directory findById(Integer id) {
        return mapper.findById(id);
    }

    public int update(Map<String, Object> map) {
        return mapper.update(map);
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
