package com.ssafy.board.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@ApiModel(value = "Board: 게시글 정보", description = "게시글의 상세 정보를 조회한다.")
public class Board {
    @ApiModelProperty(value = "게시글 고유 번호")
    private int articleNo;

    @ApiModelProperty(value = "작성자 아이디")
    private String userId;

    @ApiModelProperty(value = "게시글 제목")
    private String subject;

    @ApiModelProperty(value = "게시글 내용")
    private String content;

    @ApiModelProperty(value = "조회수")
    private int hit;

    @ApiModelProperty(value = "게시글 작성일")
    private String regTime;
}

// clear
