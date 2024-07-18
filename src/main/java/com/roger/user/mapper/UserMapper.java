package com.roger.user.mapper;

import com.roger.user.dto.UserDto;
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
    void addUser(User user);

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

    /**
     * 查詢全部的會員
     */
    List<User> findAll();

    /**
     * 透過限制傳入顯示每頁資料
     */
    List<User> findUsersByPage(int offset, int pageSize);

    /**
     * 獲取總資料數量
     */
    int countAllUsers();
}
