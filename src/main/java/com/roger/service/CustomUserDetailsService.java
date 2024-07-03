package com.roger.service;

import com.roger.mapper.UserMapper;
import com.roger.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 從資料庫或其他來源加載會員資料
        // 這裡假設有一個 UserMapper 用於從數據庫中查找用戶
        User user = userMapper.findByUserName(username);

        if (user == null) {
            throw new UsernameNotFoundException("無法透過" + username + "找到會員");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), new ArrayList<>());
    }
}
