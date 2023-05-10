package com.tj.boardExample.mapper;

import com.tj.boardExample.dto.BoardDto;
import com.tj.boardExample.dto.CommentDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    void insertBoard(BoardDto boardDto);

    BoardDto selectBoard(Integer brdKey);

    List<BoardDto> selectBoardList();

    void updateBoard(BoardDto boardDto);

    void deleteBoard(Integer brdKey);

    List<CommentDto> selectComment();
}