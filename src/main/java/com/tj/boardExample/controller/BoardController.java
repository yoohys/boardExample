package com.tj.boardExample.controller;

import com.tj.boardExample.dto.BoardDto;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BoardController {

    @RequestMapping(value = "/boardPage", method = RequestMethod.GET)
    public String boardPage() {
        //   C <----- V
        //   C -----> V

        return "board.html";
    }

    @RequestMapping(value = "/boardInsert", method = RequestMethod.GET)
    public String boardInsert(BoardDto boardDto) {
        System.out.println(boardDto.getBrdTitle());
        System.out.println(boardDto.getBrdContent());

        return "board.html";
    }
}
