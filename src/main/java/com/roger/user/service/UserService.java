package com.roger.user.service;

import com.roger.user.dto.UserDto;
import com.roger.user.pojo.Result;
import com.roger.user.pojo.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

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
    User register(UserDto userDto);

    /**
     * 註冊會員(ZK)
     */
    Result register_zk(UserDto userDto);

    /**
     * 刪除會員資料
     */
    Result deleteUser(UserDto userDto);

    /**
     * 修改會員資料
     */
    Result upateUser(UserDto userDto);

    /**
     * 透過送入的會員資料查詢 (交集)
     */
    Result findUserIntersection(User user);

    /**
     * 透過送入的會員資料查詢 (聯集)
     */
    Result findUserUnion(User user);

    /**
     * 透過 username 透過會員名稱查找會員
     * @param username 會員名稱
     * @return 返回找到的會員，沒有則返回 null
     */
    User findByUserName(String username);

    /**
     * 全部的會員
     */
    List<UserDto> findAllUsers();
}
