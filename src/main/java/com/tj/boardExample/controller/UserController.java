package com.tj.boardExample.controller;

import com.tj.boardExample.dto.LoginDto;
import com.tj.boardExample.dto.UserDto;
import com.tj.boardExample.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;

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

    // loginPage 바인딩해주는 API
    @RequestMapping(value = "/loginPage", method = RequestMethod.GET)
    public String loginPage() {
        return "user/loginPage";
    }

    // loginPage 에서 올라온 데이터를 가지고 실제 로그인이 진행될 API
    @RequestMapping(value = "/loginLogic", method = RequestMethod.GET)
    public String loginLogic(LoginDto loginDto, HttpSession session, Model model) {
        int result = userService.login(loginDto);
        if (result != 0) {
            session.setAttribute("userKey", result);
            // 로그인 로직
            // 항상
        } else {
            model.addAttribute("error", "로그인이 실패하였습니다.");
            return "user/loginPage";
        }
        return "redirect:/";
    }

    @RequestMapping("/loginTest") // 이 경로로 들어오면 내가 받겠다. Spring 에게 알려준것
    public String testPage(Model model, HttpSession session) {
        System.out.println(session.getAttribute("userKey"));
        if (session.getAttribute("userKey") == null) {
            return "redirect:/loginPage";
        }

        model.addAttribute("test", "테스트");
        model.addAttribute("test2", "<a href=\"www.naver.com\">가</a>");
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(4);
        arrayList.add(5);
        arrayList.add(6);
        arrayList.add(7);
        model.addAttribute("list", arrayList);
        return "test";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutPage(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

}
