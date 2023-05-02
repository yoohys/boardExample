package com.tj.boardExample.controller;

import com.tj.boardExample.dto.BoardDto;
import com.tj.boardExample.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @RequestMapping(value = "/boardPage", method = RequestMethod.GET)
    public String boardPage() {
        return "board/board";
    }

    @RequestMapping(value = "/boardInsert", method = RequestMethod.GET)
    public String boardInsert(BoardDto boardDto) {
        boardService.registerBoard(boardDto);
        return "board/board.html";
    }

    @RequestMapping("/boardSelect/{brdKey}") // Default = GET
    public String boardSelect(Model model, @PathVariable("brdKey") Integer brdKey2) {
        BoardDto boardDto = boardService.getBoard(brdKey2);
        model.addAttribute("board", boardDto);
        System.out.println(boardDto);
        return "board/boardSelect";
        // boardSelect html 에서 데이터가 표현되게끔
    }

    // 수정 전 정보를 조회하고 수정을 할 수 있는 페이지 Return API
    @RequestMapping("/boardUpdate/{brdKey}") // Default = GET
    public String boardUpdate1(Model model, @PathVariable("brdKey") Integer brdKey2) {
        BoardDto boardDto = boardService.getBoard(brdKey2);
        model.addAttribute("board", boardDto);
        return "board/boardUpdate";
    }

    // 수정 버튼을 눌렀을때 데이터들을 통하여 실제 수정이 진행될 API
    @RequestMapping("/boardUpdate2") // Default = GET
    public String boardUpdate2(Model model, BoardDto boardDto) {
        boardService.modifyBoard(boardDto);
//        BoardDto boardDto2 = boardService.getBoard(boardDto.getBrdKey());
//        model.addAttribute("board", boardDto2);
//        return "board/boardUpdate";
        return "redirect:/boardUpdate/" + boardDto.getBrdKey();
    }



//        DTO          DTO       DTO
//    View   Controller   Service   Mapper =  Mapper.xml (DB)

//       ----->        ------>   ----->   =
//       <-----        <------   <-----

// Create, Read, Update (Read + Create), Delete

    // View 화면단위
    // Controller 제어단위
    // Service 로직단위
    // Mapper DB 연동
}
