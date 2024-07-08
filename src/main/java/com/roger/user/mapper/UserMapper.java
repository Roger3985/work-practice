package com.roger.user.mapper;

import com.roger.user.pojo.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Tag(name = "mapper block")
@Mapper
public interface UserMapper {

    /**
     * 註冊
     */
    void addUser(String username, String password, Integer department_id);

    /**
     * 刪除會員
     */
    void deleteUser(User user);

    /**
     * 修改會員資料
     */
    void updateUser(User user);

    /**
     * 透過送入的會員資料查詢 (交集)
     */
    List<User> findByUserQueryIntersection(User user);

    /**
     * 透過送入的會員資料查詢 (聯集)
     */
    List<User> findByUserQueryUnion(User user);

    /**
     * 根據會員名稱查詢會員
     */
    User findByUserName(String username);
}
