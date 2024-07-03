package com.roger.mapper;

import com.roger.pojo.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    /**
     * 註冊
     */
    @Insert("insert into \"user\"(username, password, create_time, update_time)" +
            " values(#{username}, #{password}, now(), now())")
    void add(String username, String password);

    /**
     * 刪除會員
     */
    @Delete(("DELETE FROM \"user\" WHERE id = #{id}"))
    void delete(User user);

    /**
     * 修改會員資料
     */
    @Update("UPDATE user SET " +
            "username = #{username}, " +
            "password = #{password}, " +
            "nickname = #{nickname}, " +
            "email = #{email}, " +
            "user_pic = #{userPic}, " +
            "update_time = CURRENT_TIMESTAMP " +
            "WHERE id = #{id}")
    void update(User user);

    /**
     * 透過送入的會員資料查詢
     */
    @SelectProvider(type = UserSqlProvider.class, method = "findUsersByQuery")
    User findByUserQuery(User query);

    /**
     * 根據會員名稱查詢會員
     */
    @Select("select * from \"user\" where username=#{username}")
    User findByUserName(String username);

    /**
     * 利用 username and password 查找 user
     */
    @Select("select * from \"user\" where username=#{username} AND password=#{password}")
    User findByUserNameAndPassword(String username, String password);
}
