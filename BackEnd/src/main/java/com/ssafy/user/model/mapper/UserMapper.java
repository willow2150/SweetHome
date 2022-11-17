package com.ssafy.user.model.mapper;

import com.ssafy.user.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface UserMapper {
    void insert(User user) throws SQLException;
    User searchById(String userId) throws SQLException;
    List<User> search(User user) throws SQLException;
    void update(User user) throws SQLException;
    void delete(String userId) throws SQLException;
    List<User> selectAll() throws SQLException;
    User searchUser(String userId, String userPwd) throws SQLException;
}
