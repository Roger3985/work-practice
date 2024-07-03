package com.roger.mapper;

import com.roger.pojo.User;
import org.apache.ibatis.jdbc.SQL;


public class UserSqlProvider {

    public String findUsersByQuery(User query) {
        return new SQL() {{
            SELECT("*");
            FROM("user");
            if (query.getId() != null) {
                WHERE("id = #{id}");
            }
            if (query.getUsername() != null && !query.getUsername().isEmpty()) {
                WHERE("username = #{username}");
            }
            if (query.getPassword() != null && !query.getPassword().isEmpty()) {
                WHERE("password = #{password}");
            }
            if (query.getNickname() != null && !query.getNickname().isEmpty()) {
                WHERE("nickname = #{nickname}");
            }
            if (query.getEmail() != null && !query.getEmail().isEmpty()) {
                WHERE("email = #{email}");
            }
        }}.toString();
    }
}
