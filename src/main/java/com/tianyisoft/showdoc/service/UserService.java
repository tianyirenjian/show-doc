package com.tianyisoft.showdoc.service;

import com.tianyisoft.showdoc.entity.User;
import com.tianyisoft.showdoc.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserService {
    private final UserMapper mapper;

    public UserService(UserMapper mapper) {
        this.mapper = mapper;
    }

    public List<User> list() {
        return mapper.list();
    }

    public User find(Integer id) {
        return mapper.find(id);
    }

    public int create(User user) {
        return mapper.create(user);
    }

    public int update(Map<String, Object> map) {
        return mapper.update(map);
    }

    public int delete(Integer id) {
        return mapper.delete(id);
    }

    public User findByUsername(String username) {
        return mapper.findByUsername(username);
    }
}
