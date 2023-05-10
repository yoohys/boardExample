package com.tj.boardExample.controller;

import com.tj.boardExample.dto.CommentDto;
import com.tj.boardExample.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController // Controller + ResponseBody
@RequestMapping("/comment")
public class CommentRestController { // RESTFul API 구조
    @Autowired
    private CommentService commentService;

    @PostMapping
    public void insertComment(CommentDto commentDto) {
        commentService.insertComment(commentDto);
    }


    @DeleteMapping(value = "/{cmtKey}")
    public void deleteComment(@PathVariable("cmtKey") Integer cmtKey) {
        commentService.removeComment(cmtKey);
    }


}
