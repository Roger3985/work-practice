package com.roger.service;

import com.roger.dto.UserDto;
import com.roger.pojo.Result;
import com.roger.pojo.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {

    UserDetails getCurrentUserDetails();

    /**
     * 註冊會員
     */
    Result register(UserDto userDto);

    /**
     * 刪除會員資料
     */
    Result deleteUser(UserDto userDto);

    /**
     * 修改會員資料
     */
    Result upateUser(UserDto userDto);

    /**
     * 透過送入的會員資料查詢
     */
    Result findUser(User user);

    /**
     * 透過 username 透過會員名稱查找會員
     * @param username 會員名稱
     * @return 返回找到的會員，沒有則返回 null
     */
    User findByUserName(String username);

    /**
     * 利用 username and password 查找 user
     */
    User findByUserNameAndPassword(String username, String password);

}
