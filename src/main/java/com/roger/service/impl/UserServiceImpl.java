package com.roger.service.impl;

import com.roger.dto.UserDto;
import com.roger.mapper.UserMapper;
import com.roger.pojo.Result;
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
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

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
    @Transactional
    public Result register(UserDto userDto) {

        // 檢查會員是否存在
        User user = userMapper.findByUserName(userDto.getUsername());

        if (user != null) {
            return Result.error("該會員名稱已經被註冊");
        } else {
            // Passwordencoder 加密
            String hashPassword = passwordEncoder.encode(userDto.getPassword());
            // 添加新會員
            userMapper.addUser(userDto.getUsername(), hashPassword);
            return Result.success("會員創建成功");
        }
    }

    /**
     * 刪除 user
     */
    @Override
    @Transactional
    public Result deleteUser(UserDto userDto) {
        // 檢查該會員是否存在
        User user = userMapper.findByUserName(userDto.getUsername());

        if (user != null && passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
            userMapper.deleteUser(user);
            return Result.success("該會員已經被刪除");
        } else {
            return Result.error("該會員不符合相關資格刪除此會員");
        }
    }

    /**
     * 修改會員資料
     */
    @Override
    @Transactional
    public Result upateUser(UserDto userDto) {
        // 檢查該會員是否存在
        User user = userMapper.findByUserName(userDto.getUsername());

        if (user != null) {
            // 修改會員資料
            user.setUsername(userDto.getUsername());
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            user.setNickname(userDto.getNickname());
            user.setEmail(userDto.getEmail());
            userMapper.updateUser(user);
            return Result.success("修改成功");
        } else {
            // 沒有該會員資料返回錯誤訊息
            return Result.error("沒有此會員訊息，修改失敗");
        }
    }

    /**
     * 透過送入的會員資料查詢
     */
    @Override
    @Transactional
    public Result findUser(User user) {
        List<User> users = userMapper.findByUserQuery(user);
        if (!users.isEmpty()) {
            List<String> usernames = users.stream()
                    .map(User::getUsername)
                    .collect(Collectors.toList());
            return Result.success(usernames);
        } else {
            return Result.error("找不到會員資料");
        }
    }

    /**
     * 透過 username 透過會員名稱查找會員
     */
    @Override
    @Transactional
    public User findByUserName(String username) {
        User user = userMapper.findByUserName(username);
        return user;
    }

    /**
     * 利用 username and password 查找 user
     */
    @Override
    @Transactional
    public User findByUserNameAndPassword(String username, String password) {
        // Passwordencoder 加密
        String hashPassword = passwordEncoder.encode(password);
        User user = userMapper.findByUserNameAndPassword(username, hashPassword);
        return user;
    }

}
