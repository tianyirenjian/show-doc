package com.tianyisoft.showdoc.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tianyisoft.showdoc.dto.UserDto;
import com.tianyisoft.showdoc.entity.User;
import com.tianyisoft.showdoc.exception.UsernameExistsException;
import com.tianyisoft.showdoc.mapper.UserMapper;
import com.tianyisoft.showdoc.utils.Util;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
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
        User user = mapper.find(id);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "用户不存在");
        }
        return user;
    }

    public int create(UserDto user) throws UsernameExistsException {
        if (countByUsername(user.getUsername(), null) > 0) {
            throw new UsernameExistsException();
        }
        User newUser = new User();
        BeanUtils.copyProperties(user, newUser);
        newUser.setPassword(Util.hashPassword(newUser.getPassword()));
        mapper.create(newUser);
        return newUser.getId();
    }

    public int update(Integer id, UserDto user) throws UsernameExistsException {
        User old = find(id);
        if (countByUsername(user.getUsername(), id) > 0) {
            throw new UsernameExistsException();
        }
        if (user.getPassword() != null) {
            user.setPassword(Util.hashPassword(user.getPassword()));
        }
        BeanUtils.copyProperties(user, old, Util.getNullPropertyNames(user));
        return mapper.update(old);
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
