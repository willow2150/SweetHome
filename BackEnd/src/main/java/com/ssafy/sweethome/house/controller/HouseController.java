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

//    @ApiOperation(value = "아파트 거래 목록 조회(주소 혹은 아파트 이름)", notes = "주소 혹은 아파트 이름 검색하여 아파트 거래 목록을 조회한다.", response = Map.class)
//    @GetMapping("/list")
//    public ResponseEntity<Map<String, Object>> searchHouseListByAddressOrHouseName(
//            @RequestBody @ApiParam(value = "주소 혹은 아파트 이름", required = true) Map<String, String> map) {
//        log.debug("Apartment transaction listing lookup: by address or apartment name");
//        Map<String, Object> resultMap = new HashMap<>();
//        HttpStatus status;
//        try {
//
//            /**
//             *
//             */
//            // 주소로 dongCode 를 가져오고..
//            // map에서 시도 구군 동, 위에서 찾은 dongCode 넘겨서 (예외처리하고) houseinfo, housedeal 조인해서 검색 (아파트이름있는지도 확인해야)
//        } catch (Exception e) {
//            log.debug("Failed to look up apartment transaction list: {}", e.getMessage());
//            resultMap.put("message", e.getMessage());
//            status = HttpStatus.INTERNAL_SERVER_ERROR;
//        }
//        return new ResponseEntity<>(resultMap, status);
//    }
//
//    @ApiOperation(value = "아파트 거래 목록 조회(지역 고유 번호)", notes = "지역 고유 번호로 검색하여 아파트 거래 목록을 조회한다.", response = Map.class)
//    @GetMapping("/list/{dongCode}")
//    public ResponseEntity<Map<String, Object>> searchHouseListByDongCode(
//            @PathVariable("dongCode") @ApiParam(value = "지역 고유 번호", required = true) String dongCode) {
//        log.debug("Apartment transaction listing lookup: by local number");
//        Map<String, Object> resultMap = new HashMap<>();
//        HttpStatus status;
//        try {
//
//            // 동 코드로 시도 구군 동 찾고..
//            // 동코드 찾은거 넘기고  houseinfo, housedeal 조인해서 ㅇㅇ..
//        } catch (Exception e) {
//            log.debug("Failed to look up apartment transaction list: {}", e.getMessage());
//            resultMap.put("message", e.getMessage());
//            status = HttpStatus.INTERNAL_SERVER_ERROR;
//        }
//        return new ResponseEntity<>(resultMap, status);
//    }

    @ApiOperation(value = "아파트 거래 조회(거래 고유 번호)", notes = "거래 고유 번호로 검색하여 아파트 거래를 조회한다.", response = Map.class)
    @GetMapping("/{dealNo}")
    public ResponseEntity<Map<String, Object>> searchHouseByHousedealNo(
            @PathVariable("dealNo") @ApiParam(value = "지역 고유 번호", required = true) long dealNo) {
        log.debug("Apartment transaction lookup: by transaction number");
        Map<String, Object> resultMap = new HashMap<>();
        HttpStatus status;
        try {
            House house = new House();
            Housedeal housedeal = houseService.selectHousedealByNo(dealNo);
            Houseinfo houseinfo = houseService.selectHouseinfoByCode(housedeal.getHouseCode());
            Dongcode dongcode = houseService.selectRegionByDongCode(houseinfo.getDongCode());
            house.setDealNo(dealNo);
            house.setDongCode(houseinfo.getDongCode());
            house.setHouseName(houseinfo.getHouseName());
            house.setAddress(
                    dongcode.getSidoName() + " "
                            + dongcode.getGugunName() + " "
                            + dongcode.getDongName() + " "
                            + houseinfo.getJibun()
            );
            house.setBuildYear(houseinfo.getBuildYear());
            house.setLng(houseinfo.getLng());
            house.setLat(houseinfo.getLat());
            house.setDealYear(housedeal.getDealYear());
            house.setDealMonth(housedeal.getDealMonth());
            house.setArea(housedeal.getArea());
            house.setFloor(housedeal.getFloor());
            resultMap.put("message", SUCCESS);
            resultMap.put("house", house);
            status = HttpStatus.OK;
        } catch (Exception e) {
            log.debug("Apartment Transaction Query Failed: {}", e.getMessage());
            resultMap.put("message", e.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return new ResponseEntity<>(resultMap, status);
    }

//    @ApiOperation(value = "아파트 거래 목록 조회(위도 및 경도)", notes = "위도 및 경도로 검색하여 아파트 거래 목록을 조회한다.", response = Map.class)
//    @GetMapping("/{lat}/{lng}")
//    public ResponseEntity<Map<String, Object>> searchHouseListByLatAndLng(
//            @PathVariable("lat") @ApiParam(value = "위도", required = true) String lat,
//            @PathVariable("lng") @ApiParam(value = "경도", required = true) String lng) {
//        log.debug("Apartment transaction listing lookup: by latitude and longitude");
//        Map<String, Object> resultMap = new HashMap<>();
//        HttpStatus status;
//        try {
//            // 위도 경도로 housedeal 잡아오고
//            // houseCode
//        } catch (Exception e) {
//            log.debug("Failed to look up apartment transaction list: {}", e.getMessage());
//            resultMap.put("message", e.getMessage());
//            status = HttpStatus.INTERNAL_SERVER_ERROR;
//        }
//        return new ResponseEntity<>(resultMap, status);
//    }
}
