package com.tianyisoft.showdoc.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tianyisoft.showdoc.entity.User;
import com.tianyisoft.showdoc.mapper.UserMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {
    private final UserMapper mapper;

    public UserService(UserMapper mapper) {
        this.mapper = mapper;
    }

    public List<User> list() {
        return mapper.list();
    }

    public Page<User> page(Integer page) {
        return mapper.selectPage(new Page<>(Optional.ofNullable(page).orElse(1), User.PER_PAGE), null);
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

    public User findByUsername(String username, Integer exclude) {
        QueryWrapper<User> wrapper = new QueryWrapper<User>().eq("username", username);
        if (exclude != null) {
            wrapper.ne("id", exclude);
        }
        return mapper.selectOne(wrapper);
    }

    public int countByUsername(String username, Integer exclude) {
        QueryWrapper<User> wrapper = new QueryWrapper<User>().eq("username", username);
        if (exclude != null) {
            wrapper.ne("id", exclude);
        }
        return mapper.selectCount(wrapper);
    }
}
