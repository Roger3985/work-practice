package com.roger.mapper;

import com.roger.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

    // 根據會員名稱查詢會員
    @Select("select * from \"user\" where username=#{username}")
    User findByUserName(String username);

    // 註冊
    @Insert("insert into \"user\"(username, password, create_time, update_time)" +
            " values(#{username}, #{password}, now(), now())")
    void add(String username, String password);

    /**
     * 利用 username and password 查找 user
     */
    @Select("select * from \"user\" where username=#{username} AND password=#{password}")
    User findByUserNameAndPassword(String username, String password);

    /**
     * 刪除會員
     */
    @Delete(("DELETE FROM \"user\" WHERE id = #{id}"))
    void delete(User user);


}
