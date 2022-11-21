package com.ssafy.sweethome.house.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Houseinfo {

    private long houseCode;

    private int buildYear;

    private String roadName;

    private String roadNameBonbun;

    private String roadNameBubun;

    private String dongCode;

    private String houseName;

    private String jibun;

    private String lng;

    private String lat;
}
