package com.loginExample.demo.service;

import com.loginExample.demo.PayloadResponse.LoginMessage;
import com.loginExample.demo.dto.LoginDto;
import com.loginExample.demo.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    String addUSer(UserDto userDto);

    LoginMessage loginUser(LoginDto loginDto);

}
