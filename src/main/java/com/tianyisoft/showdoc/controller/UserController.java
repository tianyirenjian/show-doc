package com.tianyisoft.showdoc.controller;

import com.tianyisoft.showdoc.entity.User;
import com.tianyisoft.showdoc.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    @ApiOperation("用户列表")
    public List<User> index() {
        return userService.list();
    }

    @PostMapping("/users")
    @ApiOperation("添加用户")
    public User create(@RequestBody User user) {
        userService.create(user);
        return userService.find(user.getId());
    }
}
