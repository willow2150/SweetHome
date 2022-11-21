package com.ssafy.sweethome.favorite_housedeal.model.service;

import com.ssafy.sweethome.favorite_housedeal.model.FavoriteHousedeal;

import java.util.List;

public interface FavoriteHousedealService {
    List<FavoriteHousedeal> getFavoriteHousedealNoList(String userId) throws Exception;

    boolean deleteFavoriteHousedeal(FavoriteHousedeal favoriteHousedeal) throws Exception;

    boolean insertFavoriteHousedeal(FavoriteHousedeal favoriteHousedeal) throws Exception;
}
