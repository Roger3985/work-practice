package com.roger.mapper;

import com.roger.mapper.dynamicSQL.UserSqlProvider;
import com.roger.pojo.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper2 {

    /**
     * 註冊
     */
    @Insert("insert into \"user\"(username, password, create_time, update_time)" +
            " values(#{username}, #{password}, now(), now())")
    void addUser(String username, String password);

    /**
     * 刪除會員
     */
    @Delete(("delete from \"user\" where id = #{id}"))
    void deleteUser(User user);

    /**
     * 修改會員資料
     */
    @Update("update \"user\" set " +
            "username = #{username}, " +
            "password = #{password}, " +
            "nickname = #{nickname}, " +
            "email = #{email}, " +
            "update_time = now()" +
            "where id = #{id}")
    void updateUser(User user);

    /**
     * 透過送入的會員資料查詢
     */
    @SelectProvider(type = UserSqlProvider.class, method = "findUsersByQuery")
    List<User> findByUserQuery(User user);

    /**
     * 根據會員名稱查詢會員
     */
    @Select("select * from \"user\" where username=#{username}")
    User findByUserName(String username);
}
