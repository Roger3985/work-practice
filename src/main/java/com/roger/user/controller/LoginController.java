package com.roger.user.controller;

import groovy.util.logging.Log4j2;
import groovy.util.logging.Slf4j;
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