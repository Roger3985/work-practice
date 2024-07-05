package com.roger.service;

import com.roger.dto.UserDto;
import com.roger.pojo.Result;
import com.roger.pojo.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {

    /**
     * 查看當前登入會員
     */
    UserDetails getCurrentUserDetails();

    /**
     * 自定義登入會員
     */
    Result userLogin(UserDto userDto, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse);

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
}
