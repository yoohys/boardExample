package com.tj.boardExample.mapper;

import com.tj.boardExample.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;


@Mapper
public interface UserMapper {
    void insertUser(UserDto UserDto);

}