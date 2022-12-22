package com.pmis.user.service.controllers;

import com.pmis.user.service.UserService;
import com.pmis.user.service.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public User save(@RequestBody User user) {
        return userService.saveUser(user);
    }

    @GetMapping("/get")
    public List<User> getUsers() {
        return userService.getUsers();
    }

    @GetMapping("/get/{id}")
    public User getUser(@PathVariable String id) {
        return userService.getUser(id);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteUserById(@PathVariable String id) {
        return userService.deleteUser(id) ;
    }
}
