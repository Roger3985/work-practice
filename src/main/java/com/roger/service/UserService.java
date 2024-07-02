package com.roger.service;

import com.roger.pojo.User;

public interface UserService {

    /**
     * 透過 username 透過會員名稱查找會員
     * @param username 會員名稱
     * @return 返回找到的會員，沒有則返回 null
     */
    User findByUserName(String username);

    /**
     * 註冊會員
     * @param username 會員名稱
     * @param password 會員密碼
     */
    void register(String username, String password);
}
