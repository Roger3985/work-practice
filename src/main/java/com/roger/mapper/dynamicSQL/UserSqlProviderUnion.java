package com.roger.mapper.dynamicSQL;

import com.roger.pojo.User;
import org.apache.ibatis.jdbc.SQL;

public class UserSqlProviderUnion {

    public String findUsersByQueryUnion(User user) {
        return new SQL() {{
            SELECT("*");
            FROM("\"user\"");
            if (user.getUsername() != null && !user.getUsername().isEmpty()) {
                WHERE("username = #{username}");
            }
            if (user.getNickname() != null && !user.getNickname().isEmpty()) {
                OR().WHERE("nickname = #{nickname}");
            }
            if (user.getEmail() != null && !user.getEmail().isEmpty()) {
                OR().WHERE("email = #{email}");
            }
        }}.toString();
    }
}
