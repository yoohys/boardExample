package com.tj.boardExample.controller;

import com.tj.boardExample.dto.BoardDto;
import com.tj.boardExample.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Controller + ResponseBody
@RequestMapping("/board")
public class BoardRestController { // RESTFul API 구조
    @Autowired
    private BoardService boardService;

    @GetMapping
    public List<BoardDto> testApi() {
        return boardService.getAllBoard();
    }

    @GetMapping(value = "/{brdKey}")
    public BoardDto testApi(@PathVariable("brdKey") Integer brdKey) {
        return boardService.getBoard(brdKey);
    }

    @PostMapping
    public void insertBoard(BoardDto boardDto) {
        boardService.registerBoard(boardDto);
    }

    @PutMapping
    public void updateBoard(BoardDto boardDto) {
        boardService.modifyBoard(boardDto);
    }

    @DeleteMapping(value = "/{brdKey}")
    public void deleteBoard(@PathVariable("brdKey") Integer brdKey) {
        boardService.removeBoard(brdKey);
    }


}
