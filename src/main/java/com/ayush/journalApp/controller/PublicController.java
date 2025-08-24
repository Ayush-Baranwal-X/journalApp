package com.ayush.journalApp.controller;

import com.ayush.journalApp.entity.User;
import com.ayush.journalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    UserService userService;

    @PostMapping("/create-user")
    public void createUser(@RequestBody User user)
    {
        userService.saveNewUser(user);
    }

    @PostMapping("/create-admin")
    public void createAdmin(@RequestBody User user)
    {
        userService.saveNewAdmin(user);
    }
}
