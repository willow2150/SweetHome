package com.ssafy.sweethome.house.model.mapper;

import com.ssafy.sweethome.house.model.Dongcode;
import com.ssafy.sweethome.house.model.House;
import com.ssafy.sweethome.house.model.Housedeal;
import com.ssafy.sweethome.house.model.Houseinfo;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Mapper
public interface HouseMapper {
//    List<House> selectHouseList(Map<String, String> map) throws SQLException;
//
//    List<House> selectHouseListByDongCode(String dongCode) throws SQLException;
//
//    House selectHouseByDealNo(String dealNo) throws SQLException;
//
//    House selectHouseByPoint(String lat, String lng) throws SQLException;

    // 주소 혹은 아파트로 조회
    List<House> selectHouseListByAddressOrHouseName(Map<String, String> map) throws SQLException;

    // 지역 고유 번호로 조회
    List<House> selectHouseListByDongCode(String dongCode) throws SQLException;

    // 거래 고유 번호로 조회
    Housedeal selectHousedealByNo(long no) throws SQLException;
    Houseinfo selectHouseinfoByCode(long houseCode) throws SQLException;
    Dongcode selectRegionByDongCode(String dongCode) throws SQLException;
    
    // 위도 및 경도로 조회
//    List<House> selectHouseListByLatAndLng(String lat, String lng) throws SQLException;

}
