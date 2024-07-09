package com.roger.user.pojo;

import com.roger.department.pojo.Department;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Schema(description = "The id of the user", example = "1")
    private Integer id;

    @Schema(description = "The username of the user", example = "Roger")
    private String username;

    @Schema(description = "The password of the user", example = "123456")
    private String password;

    @Schema(description = "The nickname of the user", example = "roger1")
    private String nickname;

    @Schema(description = "The email of the user", example = "123@gmail.com")
    private String email;

    @Schema(description = "The picture of the user")
    private String userPic;

    @Schema(description = "The createTime of the user")
    private LocalDateTime createTime;

    @Schema(description = "The updateTime of the user")
    private LocalDateTime updateTime;

    @Schema(description = "多對一關聯的部門屬性")
    private Department department; // 多對一關聯的部門屬性

    public User(String username, String password, Department department) {
        this.username = username;
        this.password = password;
        this.department = department;
    }

    public User(String username, String nickname, String email) {
        this.username = username;
        this.nickname = nickname;
        this.email = email;
    }
}
