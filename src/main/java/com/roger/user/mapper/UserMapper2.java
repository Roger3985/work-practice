package com.roger.user.mapper;

import com.roger.user.mapper.dynamicSQL.UserSqlProviderIntersection;
import com.roger.user.mapper.dynamicSQL.UserSqlProviderUnion;
import com.roger.user.pojo.User;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Tag(name = "mapper block")
@Mapper
public interface UserMapper2 {

    /**
     * 註冊
     */
    @Insert("INSERT INTO \"user\"(username, password, create_time, update_time)" +
            " values(#{username}, #{password}, now(), now())")
    void addUser(String username, String password);

    /**
     * 刪除會員
     */
    @Delete(("DELETE FROM \"user\" WHERE id = #{id}"))
    void deleteUser(User user);

    /**
     * 修改會員資料
     */
    @Update("UPDATE \"user\" SET " +
            "username = #{username}, " +
            "password = #{password}, " +
            "nickname = #{nickname}, " +
            "email = #{email}, " +
            "update_time = now()" +
            "WHERE id = #{id}")
    void updateUser(User user);

    /**
     * 透過送入的會員資料查詢 (交集)
     */
    @SelectProvider(type = UserSqlProviderIntersection.class, method = "findUsersByQueryIntersection")
    List<User> findByUserQueryIntersection(User user);

    /**
     * 透過送入的會員資料查詢 (聯集)
     */
    @SelectProvider(type = UserSqlProviderUnion.class, method = "findUsersByQueryUnion")
    List<User> findByUserQueryUnion(User user);

    /**
     * 根據會員名稱查詢會員
     */
    @Select("SELECT * FROM \"user\" WHERE username=#{username}")
    User findByUserName(String username);
}
