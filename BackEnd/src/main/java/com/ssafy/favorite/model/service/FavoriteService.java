package com.ssafy.favorite.model.service;

import com.ssafy.favorite.model.Favorite;

import java.sql.SQLException;
import java.util.List;

public interface FavoriteService {
    // 관심지역 다 뽑아보기

    List<Favorite> selectAll(String userId) throws SQLException;

    // 관심지역 더하기
    void insert(Favorite favorite) throws SQLException;

    // 관심지역 제거하기

    void delete(Favorite favorite) throws SQLException;
    
    Favorite search(Favorite favorite) throws SQLException;
}
