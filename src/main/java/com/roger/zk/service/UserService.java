package com.roger.zk.service;

import com.roger.user.mapper.UserMapper;
import com.roger.user.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public User findByUsername(String username) {
        return userMapper.findByUserName(username);
    }

    public void register(User user) {
        userMapper.addUser(user);
    }
}