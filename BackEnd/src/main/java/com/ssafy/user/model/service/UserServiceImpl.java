package com.ssafy.user.model.service;

import com.ssafy.user.model.User;
import com.ssafy.user.model.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    UserMapper mapper;

    @Autowired
    public UserServiceImpl(UserMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void insert(User user) throws SQLException {
        mapper.insert(user);
    }

    @Override
    public User searchById(String userId) throws SQLException {
        return mapper.searchById(userId);
    }

    @Override
    public List<User> search(User user) throws SQLException {
        return mapper.search(user);
    }

    @Override
    public void update(User user) throws SQLException {
        mapper.update(user);
    }

    @Override
    public void delete(String userId) throws SQLException {
        mapper.delete(userId);
    }

    @Override
    public List<User> selectAll() throws SQLException {
        return mapper.selectAll();
    }

	@Override
	public User searchUser(String userId, String userPwd) throws SQLException {
		// TODO Auto-generated method stub
		return mapper.searchUser(userId, userPwd);
	}
}
