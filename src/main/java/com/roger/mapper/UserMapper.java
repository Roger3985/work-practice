package com.roger.mapper;

import com.roger.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    /**
     * 註冊
     */
    void addUser(String username, String password);

    /**
     * 刪除會員
     */
    void deleteUser(User user);

    /**
     * 修改會員資料
     */
    void updateUser(User user);

    /**
     * 透過送入的會員資料查詢
     */
    List<User> findByUserQueryIntersection(User user);

    /**
     * 根據會員名稱查詢會員
     */
    User findByUserName(String username);
}
