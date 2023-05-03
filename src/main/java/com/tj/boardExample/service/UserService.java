package com.tj.boardExample.service;

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

}
