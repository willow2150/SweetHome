package com.ssafy.house.model.mapper;

import com.ssafy.house.model.House;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface HouseMapper {
    List<House> selectHouseList(House house) throws SQLException;
}
