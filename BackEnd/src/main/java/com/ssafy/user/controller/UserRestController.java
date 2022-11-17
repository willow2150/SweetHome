package com.ssafy.user.controller;

import com.ssafy.user.model.User;
import com.ssafy.user.model.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
public class UserRestController {
    UserService service;

    @Autowired
    public UserRestController(UserService service) {
        this.service = service;
    }

    // 회원가입
    @PostMapping("/users")
    public ResponseEntity<?> join(@RequestBody User user) {
        try {
            User searchedUser = service.searchById(user.getUserId());
            if (searchedUser == null) {
                service.insert(user);
                return new ResponseEntity<Void>(HttpStatus.OK);
            } else {
                return new ResponseEntity<Void>(HttpStatus.FORBIDDEN);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return exceptionHandling(e);
        }
    }

    // 로그인
    @GetMapping("/users/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> map) {
        try {
        	String userId = map.get("userId");
        	String userPwd = map.get("userPwd");
            // select id로만 해야됨.
            User searchedUser = service.searchUser(userId, userPwd);
            if (searchedUser == null) {
                // 아이디 없음
                return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
            } else if (!userPwd.equals(searchedUser.getUserPwd())) {
                // 비밀번호 틀림
                return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
            } else {
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return exceptionHandling(e);
        }
    }

    // 회원 정보 조회
    @GetMapping("/users/detail")
    public ResponseEntity<?> showUserInfo(@RequestBody Map<String, String> map) {
        try {
        	String userId = map.get("userId");
            User searchedUser = service.searchById(userId);
            return new ResponseEntity<>(searchedUser, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return exceptionHandling(e);
        }
    }

    // 회원 정보 수정
    @PutMapping("/users")
    public ResponseEntity<?> editUserInfo(@RequestBody User user) {
        try {
            service.update(user);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return exceptionHandling(e);
        }
    }

    // 회원 탈퇴
    @DeleteMapping("/users")
    public ResponseEntity<?> deleteUserInfo(@RequestBody Map<String, String> map) {
        try {
        	String userId = map.get("userId");
            service.delete(userId);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return exceptionHandling(e);
        }
    }

    // 회원 목록 조회
    @GetMapping("/users/admin")
    public ResponseEntity<?> showUserInfoList() {
        try {
            List<User> userList = service.selectAll();
            if (userList != null && !userList.isEmpty()) {
                return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
            } else {
                return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return exceptionHandling(e);
        }
    }

    // 회원 (정보) 검색
    @GetMapping("/users/admin/{word}")
    public ResponseEntity<?> searchUserInfo(@PathVariable Map<String, String> map) {
        try {
        	User user = new User();
        	if (map.containsKey("userId")) user.setUserId(map.get("userId"));
        	if (map.containsKey("userName")) user.setUserName(map.get("userName"));
        	if (map.containsKey("userAddress")) user.setUserAddress(map.get("userAddress"));
        	if (map.containsKey("userEmail")) user.setUserEmail(map.get("userEmail"));
            List<User> userList = service.search(user);
            if (userList != null && !userList.isEmpty()) {
                return new ResponseEntity<>(userList, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
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
