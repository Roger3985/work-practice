package com.roger.service;

import com.roger.pojo.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface UserService {

    public UserDetails getCurrentUserDetails();

    /**
     * 註冊會員
     * @param username 會員名稱
     * @param password 會員密碼
     */
    void register(String username, String password);

    /**
     * 刪除 user
     */
    void deleteUser(User user);

    /**
     * 修改會員資料
     */
    void upateUser(User user);

    /**
     * 透過送入的會員資料查詢
     */
    User findUsersByQuery(User query);

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
