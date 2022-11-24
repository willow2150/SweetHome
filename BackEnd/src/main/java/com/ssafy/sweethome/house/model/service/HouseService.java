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

    List<House> searchHouseListByCondition(Map<String, String> map) throws Exception;

    List<House> selectHouseListByDongCode(Dongcode dongCode) throws Exception;

    Housedeal searchHousedealByNo(long no) throws Exception;

    Houseinfo searchHouseinfoByCode(long houseCode) throws Exception;

    Dongcode searchRegionByDongCode(String dongCode) throws Exception;

    List<Houseinfo> searchHouseinfoListBySidoGugunCode(String sidoGugunCode) throws Exception;

    List<Housedeal> searchHousedealListByHouseCode(long houseCode) throws Exception;

    String searchRegionNameByDongCode(String dongCode) throws Exception;
}
