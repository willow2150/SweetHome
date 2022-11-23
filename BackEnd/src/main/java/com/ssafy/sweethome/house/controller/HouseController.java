package com.ssafy.sweethome.house.controller;

import com.ssafy.sweethome.house.model.Dongcode;
import com.ssafy.sweethome.house.model.House;
import com.ssafy.sweethome.house.model.Housedeal;
import com.ssafy.sweethome.house.model.Houseinfo;
import com.ssafy.sweethome.house.model.service.HouseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/house")
@Api("House Controller API")
@Slf4j
public class HouseController {

    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
    private final HouseService houseService;

    @Autowired
    public HouseController(HouseService houseService) {
        this.houseService = houseService;
    }

    @ApiOperation(value = "아파트 거래 목록 조회(주소 혹은 아파트 이름)", notes = "주소 혹은 아파트 이름 검색하여 아파트 거래 목록을 조회한다.", response = Map.class)
    @PostMapping("/list")
    public ResponseEntity<Map<String, Object>> searchHouseListByCondition(
            @RequestBody @ApiParam(value = "주소 혹은 아파트 이름", required = true) Map<String, String> map) {
        log.debug("Apartment transaction listing lookup: by address or apartment name");
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status;
        try {
            if (map.containsKey("gugunName") || map.containsKey("houseName")) {
                List<House> houseList = houseService.searchHouseListByCondition(map);
                if (!houseList.isEmpty()) {
                    log.debug("Get hoseList success - Number of houses found: {}", houseList.size());
                    resultMap.put("houseList", houseList);
                    status = HttpStatus.OK;
                } else {
                    log.debug("There are no house that meet the criteria.");
                    status = HttpStatus.NO_CONTENT;
                }
                resultMap.put("message", SUCCESS);
            } else {
                log.debug("Failed: Insufficient input.\n");
                resultMap.put("message", FAIL);
                status = HttpStatus.BAD_REQUEST;
            }
        } catch (Exception e) {
            log.debug("Failed to look up apartment transaction list: {}", e.getMessage());
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(resultMap, status);
    }

    @ApiOperation(value = "아파트 거래 목록 조회(지역 고유 번호)", notes = "지역 고유 번호로 검색하여 아파트 거래 목록을 조회한다.", response = Map.class)
    @GetMapping("/list/{dongCode}")
    public ResponseEntity<Map<String, Object>> searchHouseListByDongCode(
            @PathVariable("dongCode") @ApiParam(value = "지역 고유 번호", required = true) String dongCode) {
        log.debug("Apartment transaction listing lookup: by local number");
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status;
        try {
            Dongcode region = houseService.searchRegionByDongCode(dongCode);
            List<House> houseList = houseService.selectHouseListByDongCode(region);
            log.debug("Get hoseList success - Number of houses found: {}", houseList.size());
            resultMap.put("houseList", houseList);
            resultMap.put("message", SUCCESS);
            status = houseList.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        } catch (Exception e) {
            log.debug("Failed to look up apartment transaction list: {}", e.getMessage());
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(resultMap, status);
    }

    @ApiOperation(value = "아파트 거래 조회(거래 고유 번호)", notes = "거래 고유 번호로 검색하여 아파트 거래를 조회한다.", response = Map.class)
    @GetMapping("/{dealNo}")
    public ResponseEntity<Map<String, Object>> searchHouseByHousedealNo(
            @PathVariable("dealNo") @ApiParam(value = "지역 고유 번호", required = true) long dealNo) {
        log.debug("Apartment transaction lookup: by transaction number");
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status;
        try {
            Housedeal housedeal = houseService.searchHousedealByNo(dealNo);
            Houseinfo houseinfo = houseService.searchHouseinfoByCode(housedeal.getHouseCode());
            Dongcode dongcode = houseService.searchRegionByDongCode(houseinfo.getDongCode());
            resultMap.put("house",
                    House.builder()
                            .dealNo(dealNo)
                            .dongCode(houseinfo.getDongCode())
                            .houseName(houseinfo.getHouseName())
                            .address(dongcode.getSidoName() + " "
                                    + dongcode.getGugunName() + " "
                                    + dongcode.getDongName() + " "
                                    + houseinfo.getJibun())
                            .buildYear(houseinfo.getBuildYear())
                            .lng(houseinfo.getLng())
                            .lat(houseinfo.getLat())
                            .dealYear(housedeal.getDealYear())
                            .dealMonth(housedeal.getDealMonth())
                            .area(housedeal.getArea())
                            .floor(housedeal.getFloor())
                            .dealAmount(housedeal.getDealAmount())
                            .build()
            );
            log.debug("Apartment Search Success");
            resultMap.put("message", SUCCESS);
            status = HttpStatus.OK;
        } catch (Exception e) {
            log.debug("Apartment Transaction Query Failed: {}", e.getMessage());
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(resultMap, status);
    }

    @ApiOperation(value = "아파트 목록 조회(시도구군 고유 번호)", notes = "시도구군 고유 번호가 일치하는 아파트를 조회한다.", response = Map.class)
    @GetMapping("/aptList/{sidoGugunCode}")
    public ResponseEntity<Map<String, Object>> searchHouseListBySidoGugunCode(
            @PathVariable("sidoGugunCode") @ApiParam(value = "시도구군 고유 번호", required = true) String sidoGugunCode) {
        log.debug("Apartment listing lookup: by sidoGugunCode");
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status;
        try {
            List<Houseinfo> houseinfoList = houseService.searchHouseinfoListBySidoGugunCode(sidoGugunCode);
            log.debug("Get hoseList success - Number of houses found: {}", houseinfoList.size());
            resultMap.put("houseList", houseinfoList);
            resultMap.put("message", SUCCESS);
            status = houseinfoList.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        } catch (Exception e) {
            log.debug("Failed to look up apartment transaction list: {}", e.getMessage());
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(resultMap, status);
    }

    @ApiOperation(value = "특정 아파트의 거래 목록 조회(아파트 고유 번호)", notes = "아파트 고유 번호를 이용하여 해당 아파트의 거래 목록을 조회한다", response = Map.class)
    @GetMapping("/aptDealList/{houseCode}")
    public ResponseEntity<Map<String, Object>> searchHousedealListByHouseCode(
            @PathVariable("houseCode") @ApiParam(value = "아파트 고유 번호", required = true) long houseCode) {
        log.debug("Retrieve a list of specific apartment deals - houseCode: {}", houseCode);
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status;
        try {
            List<Housedeal> housedealList = houseService.searchHousedealListByHouseCode(houseCode);
            log.debug("Succeeded in querying the specific apartment transaction list - number of apartment transactions: {}", housedealList.size());
            resultMap.put("housedealList", housedealList);
            resultMap.put("message", SUCCESS);
            status = housedealList.isEmpty() ? HttpStatus.NO_CONTENT : HttpStatus.OK;
        } catch (Exception e) {
            log.debug("Failed to retrieve specific apartment transaction list: {}", e.getMessage());
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(resultMap, status);
    }
}
