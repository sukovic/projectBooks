package com.zadatak.shopservice.controller;

import com.zadatak.shopservice.dto.LoginDto;
import com.zadatak.shopservice.dto.UserDto;
import com.zadatak.shopservice.model.User;
import com.zadatak.shopservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    public final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<User> register(@RequestBody UserDto user) {
        return new ResponseEntity<>(userService.register(user), HttpStatus.CREATED);
    }

    @PostMapping(value = "/login")
    public ResponseEntity<Boolean> login(@RequestBody LoginDto loginDto) {
        boolean login = userService.login(loginDto.getUsername(), loginDto.getPassword());
        return new ResponseEntity<>(login, HttpStatus.OK);
    }

}
