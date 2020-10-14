package com.tianyisoft.showdoc.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tianyisoft.showdoc.dto.UserDto;
import com.tianyisoft.showdoc.entity.User;
import com.tianyisoft.showdoc.exception.UsernameExistsException;
import com.tianyisoft.showdoc.group.Create;
import com.tianyisoft.showdoc.group.Update;
import com.tianyisoft.showdoc.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

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
    public User create(@RequestBody @Validated(Create.class) UserDto user) throws UsernameExistsException {
        Integer id = userService.create(user);
        return userService.find(id);
    }

    @GetMapping("/users/{id}")
    @ApiOperation("用户详情")
    public User show(@PathVariable("id") Integer id) {
        return userService.find(id);
    }

    @PutMapping("/users/{id}")
    @ApiOperation("修改用户")
    public User update(@PathVariable("id") Integer id, @RequestBody @Validated(Update.class) UserDto user) throws UsernameExistsException {
        userService.update(id, user);
        return userService.find(id);
    }

    @DeleteMapping("/users/{id}")
    @ApiOperation("删除用户")
    public int delete(@PathVariable("id") Integer id) {
        return userService.delete(id);
    }
}
