package com.ssafy.sweethome.house.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@ApiModel(value = "House: 아파트 거래 상세 정보", description = "아파트 거래 상세 정보를 담은 Class")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class House {
    @ApiModelProperty(value = "거래 고유 번호")
    private long dealNo;

    @ApiModelProperty(value = "행정 구역 고유 번호")
    private String dongCode;

    @ApiModelProperty(value = "아파트 이름")
    private String houseName;

    // sidoName + " " + gugunName + " " +  dongName + " " + jibun
    @ApiModelProperty(value = "지번 주소")
    private String address;

    @ApiModelProperty(value = "아파트 건축 연도")
    private int buildYear;

    @ApiModelProperty(value = "거래 가격")
    private String dealAmount;

    @ApiModelProperty(value = "경도")
    private String lng;

    @ApiModelProperty(value = "위도")
    private String lat;

    @ApiModelProperty(value = "거래 연도")
    private int dealYear;

    @ApiModelProperty(value = "거래 월")
    private int dealMonth;

    @ApiModelProperty(value = "아파트 면적")
    private String area;

    @ApiModelProperty(value = "아파트 층")
    private String floor;
}
