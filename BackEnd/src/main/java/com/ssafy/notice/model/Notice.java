package com.ssafy.notice.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@ApiModel(value = "Notice: 공지사항 정보", description = "공지사항의 상세 정보를 조회한다.")
public class Notice {
    @ApiModelProperty(value = "공지사항 고유 번호")
    private int articleNo;

    @ApiModelProperty(value = "공지사항 작성자 아이디")
    private String userId;

    @ApiModelProperty(value = "공지사항 제목")
    private String subject;

    @ApiModelProperty(value = "공지사항 내용")
    private String content;

    @ApiModelProperty(value = "조회수")
    private int hit;

    @ApiModelProperty(value = "공지사항 작성일")
    private String regTime;
}

// clear
