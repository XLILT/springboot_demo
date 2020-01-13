package com.xl.mbgdemo1.web.controller;

import com.xl.mbgdemo1.model.Tb1;
import com.xl.mbgdemo1.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Slf4j
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users/{id}")
    public Tb1 getTb1(@PathVariable int id) {
        log.info("process=get-user, user_id={}", id);
        Tb1 user = userService.getTb1(id);

        return user;
    }
}
