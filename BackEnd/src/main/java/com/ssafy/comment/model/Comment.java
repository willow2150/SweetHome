package com.ssafy.comment.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@ApiModel(value = "Comment: 댓글 정보", description = "댓글을 조회합니다..")
public class Comment {
    @ApiModelProperty(value = "댓글 고유 번호")
    private int commentId;

    @ApiModelProperty(value = "게시글 고유 번호")
    private int articleNo;

    @ApiModelProperty(value = "댓글 작성자 아이디")
    private String userId;

    @ApiModelProperty(value = "댓글 내용")
    private String content;

    @ApiModelProperty(value = "댓글 작성일")
    private String regTime;
}

// clear
