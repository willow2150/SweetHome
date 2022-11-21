package com.ssafy.sweethome.house.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Dongcode {

    private String dongCode;

    private String sidoName;

    private String gugunName;

    private String dongName;
}
