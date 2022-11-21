package com.ssafy.sweethome.house.model.service;

import com.ssafy.sweethome.house.model.Dongcode;
import com.ssafy.sweethome.house.model.House;
import com.ssafy.sweethome.house.model.Housedeal;
import com.ssafy.sweethome.house.model.Houseinfo;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;

@Service
public interface HouseService {

    List<House> selectHouseListByAddressOrHouseName(Map<String, String> map) throws Exception;

    List<House> selectHouseListByDongCode(String dongCode) throws Exception;

    Housedeal selectHousedealByNo(long no) throws Exception;

    Houseinfo selectHouseinfoByCode(long houseCode) throws Exception;

    Dongcode selectRegionByDongCode(String dongCode) throws Exception;
}
