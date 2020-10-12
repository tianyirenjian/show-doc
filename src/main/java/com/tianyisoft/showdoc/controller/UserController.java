package com.tianyisoft.showdoc.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tianyisoft.showdoc.entity.User;
import com.tianyisoft.showdoc.exception.UsernameExistsException;
import com.tianyisoft.showdoc.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    @ApiOperation("用户列表")
    public Page<User> index(@PathParam("page") Integer page) {
        return userService.page(page);
    }

    @PostMapping("/users")
    @ApiOperation("添加用户")
    public User create(@RequestBody User user) throws UsernameExistsException {
        if (userService.countByUsername(user.getUsername(), null) > 0) {
            throw new UsernameExistsException();
        }
        userService.create(user);
        return userService.find(user.getId());
    }

    @GetMapping("/users/{id}")
    @ApiOperation("用户详情")
    public User show(@PathVariable("id") Integer id) {
        return userService.find(id);
    }

    @PutMapping("/users/{id}")
    @ApiOperation("修改用户")
    public User update(@PathVariable("id") Integer id, @RequestBody Map<String, Object> changes) throws UsernameExistsException {
        if (changes.containsKey("username")) {
            if (userService.countByUsername((String) changes.get("username"), id) > 0) {
                throw new UsernameExistsException();
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("changes", changes);
        map.put("id", id);
        userService.update(map);
        return userService.find(id);
    }

    @DeleteMapping("/users/{id}")
    @ApiOperation("删除用户")
    public int delete(@PathVariable("id") Integer id) {
        return userService.delete(id);
    }
}
