package com.tj.boardExample.controller;

import com.tj.boardExample.dto.BoardDto;
import com.tj.boardExample.dto.UserDto;
import com.tj.boardExample.service.BoardService;
import com.tj.boardExample.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/userInsertPage", method = RequestMethod.GET)
    public String userPage() {
        return "user/userInsert";
    }

    @RequestMapping(value = "/userInsertLogic", method = RequestMethod.GET)
    public String userInsert(UserDto userDto) {
        userService.registerUser(userDto);
        return "user/userInsert";
    }

}
