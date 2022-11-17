package com.ssafy.house.model.service;

import com.ssafy.house.model.House;
import com.ssafy.house.model.mapper.HouseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class HouseServiceImpl implements HouseService {
    HouseMapper mapper;

    @Autowired
    public HouseServiceImpl(HouseMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<House> selectHouseList(House house) throws SQLException {
        return mapper.selectHouseList(house);
    }
}
