package com.roger.user.mapper.dynamicSQL;

import com.roger.user.pojo.User;
import org.apache.ibatis.jdbc.SQL;


public class UserSqlProviderIntersection {

    public String findUsersByQueryIntersection(User user) {
        return new SQL() {{
            SELECT("*");
            FROM("\"user\"");
            if (user.getUsername() != null && !user.getUsername().isEmpty()) {
                WHERE("username = #{username}");
            }
            if (user.getNickname() != null && !user.getNickname().isEmpty()) {
                AND().WHERE("nickname = #{nickname}");
            }
            if (user.getEmail() != null && !user.getEmail().isEmpty()) {
                AND().WHERE("email = #{email}");
            }
        }}.toString();
    }


}
