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

    // 주소 혹은 아파트로 조회
    List<House> selectHouseByCondition(Map<String, String> map) throws SQLException;

    // 지역 고유 번호로 조회
    List<House> selectHouseListByDongCode(Dongcode dongCode) throws SQLException;

    // 거래 고유 번호로 조회
    Housedeal selectHousedealByNo(long no) throws SQLException;

    Houseinfo selectHouseinfoByCode(long houseCode) throws SQLException;

    Dongcode selectRegionByDongCode(String dongCode) throws SQLException;

    List<Houseinfo> selectHouseinfoListBySidoGugunCode(String sidoGugunCode) throws SQLException;

    List<Housedeal> selectHousedealListByHouseCode(long houseCode) throws SQLException;
}
