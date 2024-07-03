package com.roger.service.impl;

import com.roger.mapper.UserMapper;
import com.roger.pojo.User;
import com.roger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    private final UserDetailsService userDetailsService;

    @Autowired
    public UserServiceImpl(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    public UserDetails getCurrentUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            return (UserDetails) authentication.getPrincipal();
        }
        return null;
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
     * 刪除 user
     */
    @Override
    @Transactional
    public void deleteUser(User user) {
        userMapper.delete(user);
    }

    /**
     * 修改會員資料
     */
    @Override
    public void upateUser(User user) {
        user.setId(user.getId());
        user.setUsername(user.getUsername());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setNickname(user.getNickname());
        user.setEmail(user.getEmail());
        user.setUserPic(user.getUserPic());
        user.setCreateTime(user.getCreateTime());
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
    }

    /**
     * 透過送入的會員資料查詢
     */
    @Override
    public User findUsersByQuery(User query) {
        User user = userMapper.findByUserQuery(query);
        return user;
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
     * 透過 username 透過會員名稱查找會員
     */
    @Override
    public User findByUserName(String username) {
        User user = userMapper.findByUserName(username);
        return user;
    }

}
