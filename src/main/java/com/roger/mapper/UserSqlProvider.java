package com.roger.mapper;

import com.roger.dto.UserDto;
import com.roger.pojo.User;
import org.apache.ibatis.jdbc.SQL;


public class UserSqlProvider {

    public String findUsersByQuery(UserDto userDto) {
        return new SQL() {{
            SELECT("*");
            FROM("\"user\"");
            if (userDto.getUsername() != null && !userDto.getUsername().isEmpty()) {
                WHERE("username = #{username}");
            }
            if (userDto.getNickname() != null && !userDto.getNickname().isEmpty()) {
                WHERE("nickname = #{nickname}");
            }
            if (userDto.getEmail() != null && !userDto.getEmail().isEmpty()) {
                WHERE("email = #{email}");
            }
        }}.toString();
    }


}
