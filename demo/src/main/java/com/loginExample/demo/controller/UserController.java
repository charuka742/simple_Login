package com.loginExample.demo.controller;

import com.loginExample.demo.PayloadResponse.LoginMessage;
import com.loginExample.demo.dto.LoginDto;
import com.loginExample.demo.dto.UserDto;
import com.loginExample.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/save")
    public String save(@RequestBody UserDto userDto){

        String id = userService.addUSer(userDto);
        return  id;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDto loginDto){
        LoginMessage message = userService.loginUser(loginDto);
        return ResponseEntity.ok(message);
    }




}
