package com.ssafy.house.controller;

import com.ssafy.house.model.House;
import com.ssafy.house.model.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
public class HouseRestController {
    HouseService service;

    @Autowired
    public HouseRestController(HouseService service) {
        this.service = service;
    }

    // 아파트 목록 조회
    @GetMapping("/house")
    public ResponseEntity<?> showHouseList(@RequestBody House house) {
        try {
            List<House> houseList = service.selectHouseList(house);
            if (houseList != null && !houseList.isEmpty())
                return new ResponseEntity<>(houseList, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
            return exceptionHandling(e);
        }
    }

    //
    // 아파트 목록 상세 조회
//    @GetMapping("/house/detail/{houseInfo}")
//    public ResponseEntity<?> showHouseDetail(@PathVariable SearchHouse searchHouse) {
//        try {
//
//            return new ResponseEntity<Void>(HttpStatus.OK);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return exceptionHandling(e);
//        }
//    }

 
    @GetMapping("/house/dong")
    public ResponseEntity<?> searchHouseByDong(@RequestBody House house) {
        try {
            List<House> houseList = service.selectHouseList(house);
            if (houseList != null && !houseList.isEmpty())
                return new ResponseEntity<>(houseList, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
            return exceptionHandling(e);
        }
    }

    // 아파트 목록 검색(by Name)
    @GetMapping("/house/apt")
    public ResponseEntity<?> searchHouseByName(@RequestBody House house) {
        try {
            List<House> houseList = service.selectHouseList(house);
            if (houseList != null && !houseList.isEmpty())
                return new ResponseEntity<>(houseList, HttpStatus.OK);
            else
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();
            return exceptionHandling(e);
        }
    }

    private ResponseEntity<String> exceptionHandling(Exception e) {
        return new ResponseEntity<>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
