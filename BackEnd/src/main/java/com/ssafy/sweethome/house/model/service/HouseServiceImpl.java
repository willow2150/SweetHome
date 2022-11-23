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
    public List<House> searchHouseListByCondition(Map<String, String> map) throws Exception {
        return houseMapper.selectHouseByCondition(map);
    }

    @Override
    public List<House> selectHouseListByDongCode(Dongcode dongCode) throws Exception {
        return houseMapper.selectHouseListByDongCode(dongCode);
    }

    @Override
    public Housedeal searchHousedealByNo(long no) throws Exception {
        return houseMapper.selectHousedealByNo(no);
    }

    @Override
    public Houseinfo searchHouseinfoByCode(long houseCode) throws Exception {
        return houseMapper.selectHouseinfoByCode(houseCode);
    }

    @Override
    public Dongcode searchRegionByDongCode(String dongCode) throws Exception {
        return houseMapper.selectRegionByDongCode(dongCode);
    }

    @Override
    public List<Houseinfo> searchHouseinfoListBySidoGugunCode(String sidoGugunCode) throws Exception {
        return houseMapper.selectHouseinfoListBySidoGugunCode(sidoGugunCode);
    }

    @Override
    public List<Housedeal> searchHousedealListByHouseCode(long houseCode) throws Exception {
        return houseMapper.selectHousedealListByHouseCode(houseCode);
    }
}
