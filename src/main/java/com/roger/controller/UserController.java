package com.roger.controller;

import com.roger.pojo.Result;
import com.roger.pojo.User;
import com.roger.service.UserService;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    /**
     * 註冊會員
     * @param username 會員名稱
     * @param password 會員密碼
     * @return 返回結果
     */
    @PostMapping("/register")
    public Result register(String username, String password) {
        User user = userService.findByUserName(username);
        if (user == null) {
            // 沒被占用 -> 可以註冊新的會員
            userService.register(username, password);
            return Result.success();
        } else {
            // 被占用返回錯誤訊息
            return Result.error("會員名已被佔用");
        }
    }

    /**
     * 利用會員姓名查找會員 ID
     * @param username 會員姓名
     * @return 返回會員 ID
     */
    @GetMapping("/findByUserName")
    public int findByUserName(String username) {
        User user = userService.findByUserName(username);
        return user.getId();
    }
}
