package com.ssafy.house.model.service;

import com.ssafy.house.model.House;

import java.sql.SQLException;
import java.util.List;

public interface HouseService {
    List<House> selectHouseList(House house) throws SQLException;
}
