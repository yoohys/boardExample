package com.tj.boardExample.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDto { // Data Transfer Object 데이터 가방
    private Integer brdKey;
    private String brdTitle;
    private String brdContent;
    private Integer userKey;
}
