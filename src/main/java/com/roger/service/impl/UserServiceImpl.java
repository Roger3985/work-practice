package com.roger.service.impl;

import com.roger.mapper.UserMapper;
import com.roger.pojo.User;
import com.roger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

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
        // Passwordencoder 加密
        String hashPassword = passwordEncoder.encode(password);
        userMapper.add(username, hashPassword);
    }

    /**
     * 利用 username and password 查找 user
     */
    @Override
    public User findByUserNameAndPassword(String username, String password) {
        // Passwordencoder 加密
        String hashPassword = passwordEncoder.encode(password);
        User user = userMapper.findByUserNameAndPassword(username, hashPassword);
        return user;
    }

    /**
     * 刪除 user
     */
    @Override
    public void deleteUser(User user) {
        userMapper.delete(user);
    }


}
