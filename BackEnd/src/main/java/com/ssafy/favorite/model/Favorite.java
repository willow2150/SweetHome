package com.ssafy.favorite.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
@ApiModel(value = "Favorite: 관심 매물 정보", description = "관심 매물 정보를 조회한다.")
public class Favorite {
	@ApiModelProperty(value = "관심 지역 고유 번호")
	private int favId;

	@ApiModelProperty(value = "게시글 고유 번호")
	private String userId;

	@ApiModelProperty(value = "게시글 고유 번호")
	private String sido;

	@ApiModelProperty(value = "게시글 고유 번호")
	private String gugun;

	@ApiModelProperty(value = "게시글 고유 번호")
	private String dong;
}

// 고민고민해보자