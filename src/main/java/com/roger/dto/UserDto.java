package com.roger.dto;

import jakarta.validation.constraints.Pattern;

public class UserRegisterRequest {

    @Pattern(regexp = "^\\w{5,16}$")
    private String username;

    @Pattern(regexp = "^\\w{5,16}$")
    private String password;

    public @Pattern(regexp = "^\\w{5,16}$") String getUsername() {
        return username;
    }

    public void setUsername(@Pattern(regexp = "^\\w{5,16}$") String username) {
        this.username = username;
    }

    public @Pattern(regexp = "^\\w{5,16}$") String getPassword() {
        return password;
    }

    public void setPassword(@Pattern(regexp = "^\\w{5,16}$") String password) {
        this.password = password;
    }
}
