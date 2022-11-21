package com.ssafy.sweethome.house.model.service;

import com.ssafy.sweethome.house.model.Dongcode;
import com.ssafy.sweethome.house.model.House;
import com.ssafy.sweethome.house.model.Housedeal;
import com.ssafy.sweethome.house.model.Houseinfo;
import com.ssafy.sweethome.house.model.mapper.HouseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class HouseServiceImpl implements HouseService {
    private final HouseMapper houseMapper;

    @Autowired
    public HouseServiceImpl(HouseMapper houseMapper) {
        this.houseMapper = houseMapper;
    }

    @Override
    public List<House> selectHouseListByAddressOrHouseName(Map<String, String> map) throws Exception {
        return houseMapper.selectHouseListByAddressOrHouseName(map);
    }

    @Override
    public List<House> selectHouseListByDongCode(String dongCode) throws Exception {
        return houseMapper.selectHouseListByDongCode(dongCode);
    }

    @Override
    public Housedeal selectHousedealByNo(long no) throws Exception {
        return houseMapper.selectHousedealByNo(no);
    }

    @Override
    public Houseinfo selectHouseinfoByCode(long houseCode) throws Exception {
        return houseMapper.selectHouseinfoByCode(houseCode);
    }

    @Override
    public Dongcode selectRegionByDongCode(String dongCode) throws Exception {
        return houseMapper.selectRegionByDongCode(dongCode);
    }
}
