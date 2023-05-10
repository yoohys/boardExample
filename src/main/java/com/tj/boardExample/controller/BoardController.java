package com.tj.boardExample.controller;

import com.tj.boardExample.dto.BoardDto;
import com.tj.boardExample.service.BoardService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    @RequestMapping(value = "/boardPage", method = RequestMethod.GET)
    public String boardPage() {
        return "board/board";
    }

    @RequestMapping(value = "/boardInsert", method = RequestMethod.GET)
    public String boardInsert(BoardDto boardDto, HttpSession session) {
        if (session.getAttribute("userKey") == null) {
            return "redirect:/loginPage";
        } else {
            boardDto.setUserKey((Integer) session.getAttribute("userKey"));
        }

        boardService.registerBoard(boardDto);
        return "redirect:/";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String boardSelectAll(Model model) {
        List<BoardDto> boardDtoList = boardService.getAllBoard();
        model.addAttribute("boardList", boardDtoList);
        return "board/boardSelectAll.html";
    }

    @RequestMapping("/boardSelect/{brdKey}") // Default = GET
    public String boardSelect(Model model, @PathVariable("brdKey") Integer brdKey2) {
        BoardDto boardDto = boardService.getBoard(brdKey2);
        model.addAttribute("board", boardDto);
        model.addAttribute("comments", boardService.getCommentList());
        return "board/boardSelect";
        // boardSelect html 에서 데이터가 표현되게끔
    }

    // 수정 전 정보를 조회하고 수정을 할 수 있는 페이지 Return API
    @RequestMapping("/boardUpdate/{brdKey}") // Default = GET
    public String boardUpdate1(Model model, @PathVariable("brdKey") Integer brdKey2, HttpSession session) {
        if (session.getAttribute("userKey") == null) {
            return "redirect:/loginPage";
        }
        Integer sessionKey = (Integer) session.getAttribute("userKey");
        BoardDto boardDto = boardService.getBoard(brdKey2);
        if (boardDto.getUserKey().equals(sessionKey)) {
            model.addAttribute("board", boardDto);
            model.addAttribute("error", "");
            return "board/boardUpdate";
        } else {
            BoardDto boardDto2 = boardService.getBoard(brdKey2);
            model.addAttribute("board", boardDto2);
            model.addAttribute("error", "권한이 없습니다.");
            return "board/boardSelect";
        }
    }

    // 수정 버튼을 눌렀을때 데이터들을 통하여 실제 수정이 진행될 API
    @RequestMapping("/boardUpdate2") // Default = GET
    public String boardUpdate2(BoardDto boardDto, HttpSession session, Model model) {
        if (session.getAttribute("userKey") == null) {
            return "redirect:/loginPage";
        } else {
            boardDto.setUserKey((Integer) session.getAttribute("userKey"));
        }
        if (boardService.modifyBoard(boardDto) == 1) {
            return "redirect:/boardSelect/" + boardDto.getBrdKey();
        } else {
            model.addAttribute("board", boardDto);
            model.addAttribute("error", "수정이 완료되지 못했습니다.");
            return "board/boardUpdate";
        }

    }

    @RequestMapping("/boardDelete/{brdKey}") // Default = GET
    public String boardDelete(@PathVariable("brdKey") Integer brdKey, HttpSession session, Model model) {
        if (session.getAttribute("userKey") == null) {
            return "redirect:/loginPage";
        }
        Integer sessionKey = (Integer) session.getAttribute("userKey");
        BoardDto boardDto = boardService.getBoard(brdKey);
        if (boardDto.getUserKey().equals(sessionKey)) {
            boardService.removeBoard(brdKey);
            return "redirect:/";
        } else {
            model.addAttribute("board", boardDto);
            model.addAttribute("error", "권한이 없습니다.");
            return "board/boardSelect";
        }

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
