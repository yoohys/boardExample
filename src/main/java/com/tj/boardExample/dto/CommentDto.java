package com.tj.boardExample.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentDto { // Data Transfer Object 데이터 가방
    private Integer cmtKey;
    private String cmtContent;
}
