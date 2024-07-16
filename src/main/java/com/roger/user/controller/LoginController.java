package com.roger.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    /**
     * 登入頁面
     */
    @GetMapping("/login")
    public String login() {
        return "login.html";
    }
}