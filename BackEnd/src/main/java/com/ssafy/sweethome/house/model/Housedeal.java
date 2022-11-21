package com.ssafy.sweethome.house.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Housedeal {

    private long no;

    private String dealAmount;

    private int dealYear;

    private int dealMonth;

    private int dealDay;

    private String area;

    private String floor;

    private long houseCode;
}
