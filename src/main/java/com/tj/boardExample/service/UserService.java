package com.tj.boardExample.service;

import com.tj.boardExample.dto.LoginDto;
import com.tj.boardExample.dto.UserDto;
import com.tj.boardExample.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired // 의존성 주입 (연동)
    private UserMapper userMapper;

    public void registerUser(UserDto userDto) {
        userMapper.insertUser(userDto);
    }

    public int login(LoginDto loginDto) {
        UserDto userDto = userMapper.loginUser(loginDto);
        if (userDto.getUserPw().equals(loginDto.getUserPw())) {
            return userDto.getUserKey();
        } else {
            return 0;
        }
    }

}
