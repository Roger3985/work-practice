package com.roger.user.dto;

import com.roger.department.pojo.Department;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

public class UserDto {

    @NotBlank
    @Pattern(regexp = "^\\w{5,16}$")
    @Schema(description = "The username of the user", example = "Roger")
    private String username;

    @NotBlank
    @Pattern(regexp = "^\\w{5,16}$")
    @Schema(description = "The password of the user", example = "123456")
    private String password;

    @Pattern(regexp = "^\\w{5,16}$")
    @Schema(description = "The nickname of the user", example = "roger1")
    private String nickname;

    @Setter
    @Getter
    @Schema(description = "The id of the department", example = "1")
    private Integer id;

    @Setter
    @Getter
    @Schema(description = "The name of the department", example = "software")
    private String name;

    @Setter
    @Getter
    @Schema(description = "The description of the department", example = "this is a software department")
    private String description;

    @Setter
    @Getter
    @Schema(description = "department")
    private Department department;

    /*
        (1) 必須以一個以上的文字&數字開頭
        (2) @ 之前可以出現 1 個以上的文字、數字與「-」的組合，例如 -abc-
        (3) @ 之前可以出現 1 個以上的文字、數字與「.」的組合，例如 .abc.
        (4) @ 之前以上兩項以 or 的關係出現，並且出現 0 次以上
        (5) 中間一定要出現一個 @
        (6) @ 之後出現一個以上的大小寫英文及數字的組合
        (7) @ 之後只能出現「.」或是「-」，但這兩個字元不能連續時出現
        (8) @ 之後出現 0 個以上的「.」或是「-」配上大小寫英文及數字的組合
        (9) @ 之後出現 1 個以上的「.」配上大小寫英文及數字的組合，結尾需為大小寫英文
     */
    @Pattern(regexp = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z]+$")
    @Schema(description = "The email of the user", example = "123@gmail.com")
    private String email;

    public UserDto() {
    }

    public UserDto(String username) {
        this.username = username;
    }

    public UserDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserDto(String username, String password, String nickname) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
    }

    public UserDto(String username, String password, String nickname, String email) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
    }

    public UserDto(String username, String password, String nickname, String name, String email) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.name = name;
        this.email = email;
    }

    public @NotBlank @Pattern(regexp = "^\\w{5,16}$") String getUsername() {
        return username;
    }

    public void setUsername(@NotBlank @Pattern(regexp = "^\\w{5,16}$") String username) {
        this.username = username;
    }

    public @NotBlank @Pattern(regexp = "^\\w{5,16}$") String getPassword() {
        return password;
    }

    public void setPassword(@NotBlank @Pattern(regexp = "^\\w{5,16}$") String password) {
        this.password = password;
    }

    public @Pattern(regexp = "^\\w{5,16}$") String getNickname() {
        return nickname;
    }

    public void setNickname(@Pattern(regexp = "^\\w{5,16}$") String nickname) {
        this.nickname = nickname;
    }

    public @Pattern(regexp = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z]+$") String getEmail() {
        return email;
    }

    public void setEmail(@Pattern(regexp = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z]+$") String email) {
        this.email = email;
    }


}
