package com.roger.service.impl;

import com.roger.mapper.UserMapper;
import com.roger.pojo.User;
import com.roger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    /**
     * 透過 username 透過會員名稱查找會員
     */
    @Override
    public User findByUserName(String username) {
        User user = userMapper.findByUserName(username);
        return user;
    }

    /**
     * 註冊會員
     */
    @Override
    public void register(String username, String password) {
        // 加鹽
        password = "work" + password + "practice";

        // 簡單的 hashCode
        userMapper.add(username, String.valueOf(password.hashCode()));
    }
}
