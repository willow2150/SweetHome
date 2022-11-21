package com.ssafy.sweethome.house.model;

import lombok.Data;

@Data
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
