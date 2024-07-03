package com.roger.controller;

import com.roger.pojo.Result;
import com.roger.pojo.User;
import com.roger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    /**
     * 會員註冊 (增)
     */
    @PostMapping
    public Result register(String username, String password) {
        // 參數較驗
        User user = userService.findByUserName(username);

        if (user == null) {
            // 沒被占用
            userService.register(username, password);
            return Result.success("註冊成功");
        } else {
            // 沒被占用返回錯誤訊息
            return Result.error("此會員姓名已被註冊");
        }
    }

    @DeleteMapping("/deleteUser")
    public Result deleteUser(String username, String password) {
        // 參數較驗
        User user = userService.findByUserNameAndPassword(username, password);

        if (user != null) {
            // 刪除 user
            userService.deleteUser(user);
            return Result.success("刪除成功");
        } else {
            // 沒有該會員資料返回錯誤訊息
            return Result.error("沒有此會員訊息，刪除失敗");
        }

    }


}
