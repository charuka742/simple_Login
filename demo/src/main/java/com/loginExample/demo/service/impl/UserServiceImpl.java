package com.loginExample.demo.service.impl;

import com.loginExample.demo.PayloadResponse.LoginMessage;
import com.loginExample.demo.dto.LoginDto;
import com.loginExample.demo.dto.UserDto;
import com.loginExample.demo.entity.User;
import com.loginExample.demo.repo.UserRepo;
import com.loginExample.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String addUSer(UserDto userDto) {

        User u = new User(

                userDto.getUserid(),
                userDto.getUsername(),
                userDto.getEmail(),
                this.passwordEncoder.encode(userDto.getPassword())
        );

        userRepo.save(u);
        return u.getUsername();
    }

    @Override
    public LoginMessage loginUser(LoginDto loginDto) {
        String msg = "";
        User user1 = userRepo.findByEmail(loginDto.getEmail());
        if(user1 != null){
            String password = loginDto.getPassword();
            String encodedPassword = user1.getPassword();
            Boolean isPwdRight = passwordEncoder.matches(password, encodedPassword);

            if (isPwdRight){
                Optional<User> user2 = userRepo.findOneByEmailAndPassword(loginDto.getEmail(),encodedPassword);
                if(user2.isPresent()){
                    return new LoginMessage("Login Success",true);
                }else{
                    return new LoginMessage("Login Failed", false);
                }
            }else {
                return new LoginMessage("Password Does not match", false);
            }
        }else{
            return new LoginMessage("Email Not Exist",false);
        }
    }


}
