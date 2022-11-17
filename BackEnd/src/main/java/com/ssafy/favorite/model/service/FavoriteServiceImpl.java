package com.ssafy.favorite.model.service;

import com.ssafy.favorite.model.Favorite;
import com.ssafy.favorite.model.mapper.FavoriteMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class FavoriteServiceImpl implements FavoriteService {
    FavoriteMapper mapper;

    @Autowired
    public FavoriteServiceImpl(FavoriteMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<Favorite> selectAll(String userId) throws SQLException {
        return mapper.selectAll(userId);
    }

    @Override
    public void insert(Favorite favorite) throws SQLException {
        mapper.insert(favorite);
    }

    @Override
    public void delete(Favorite favorite) throws SQLException {
        mapper.delete(favorite);
    }

	@Override
	public Favorite search(Favorite favorite) throws SQLException {
		return mapper.search(favorite);
	}
}
