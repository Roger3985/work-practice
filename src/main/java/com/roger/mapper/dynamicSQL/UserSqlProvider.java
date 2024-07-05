package com.roger.mapper.dynamicSQL;

import com.roger.pojo.User;
import org.apache.ibatis.jdbc.SQL;


public class UserSqlProvider {

    public String findUsersByQuery(User user) {
        return new SQL() {{
            SELECT("*");
            FROM("\"user\"");
            if (user.getUsername() != null && !user.getUsername().isEmpty()) {
                WHERE("username = #{username}");
            }
            if (user.getNickname() != null && !user.getNickname().isEmpty()) {
                WHERE("nickname = #{nickname}");
            }
            if (user.getEmail() != null && !user.getEmail().isEmpty()) {
                WHERE("email = #{email}");
            }
        }}.toString();
    }


}
