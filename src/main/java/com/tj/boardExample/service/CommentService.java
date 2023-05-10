package com.tj.boardExample.service;

import com.tj.boardExample.dto.CommentDto;
import com.tj.boardExample.mapper.CommentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired // 의존성 주입 (연동)
    private CommentMapper commentMapper;

    public void insertComment(CommentDto commentDto) {
        commentMapper.insertComment(commentDto);
    }

    public void removeComment(Integer cmtKey) {
        commentMapper.deleteComment(cmtKey);
    }

}
