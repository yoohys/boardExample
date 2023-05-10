package com.tj.boardExample.mapper;

import com.tj.boardExample.dto.CommentDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper {

    void insertComment(CommentDto commentDto);

    void deleteComment(Integer cmtKey);

}