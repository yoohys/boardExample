package com.tj.boardExample.controller;

import com.tj.boardExample.dto.BoardDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.http.HttpResponse;
import java.util.ArrayList;

@Controller // 이 클래스파일이 실제 돌아갈떄 객체가 되면, Controller 의 기능을 하게끔 Spring 에게 알려줌
public class TestController {

    // 데이터 로직   제어 화면
    //   M          C   V
    //   C <----- V
    //   C -----> V
    @RequestMapping("/test") // 이 경로로 들어오면 내가 받겠다. Spring 에게 알려준것
    public String testPage(Model model) {
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

}
