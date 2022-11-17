package com.ssafy.favorite.controller;

import com.ssafy.favorite.model.Favorite;
import com.ssafy.favorite.model.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class FavoriteRestController {
    FavoriteService service;

    @Autowired
    public FavoriteRestController(FavoriteService service) {
        this.service = service;
    }

    // 관심지역 다 뽑아보기
    @GetMapping("/fav")
    public ResponseEntity<?> searchFavList(@RequestBody Map<String, String> map) {
        try {
        	String userId = map.get("userId");
            List<Favorite> favoriteList = service.selectAll(userId);
            if (favoriteList != null && !favoriteList.isEmpty()) {
                return new ResponseEntity<>(favoriteList, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return exceptionHandling(e);
        }
    }

    // 관심지역 더하기
    @PostMapping("/fav")
    public ResponseEntity<?> addFav(Favorite favorite) {
        try {
            service.insert(favorite);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return exceptionHandling(e);
        }
    }

    // 관심지역 제거하기
    @DeleteMapping("/fav")
    public ResponseEntity<?> deleteFav(Favorite favorite) {
        try {
        	if (service.search(favorite) == null) {
        		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        	} else {
        		service.delete(favorite);
                return new ResponseEntity<>(HttpStatus.OK);        		
        	}
        } catch (Exception e) {
            e.printStackTrace();
            return exceptionHandling(e);
        }
    }

    private ResponseEntity<String> exceptionHandling(Exception e) {
        return new ResponseEntity<>("Error : " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
