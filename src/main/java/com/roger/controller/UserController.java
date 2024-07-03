package com.roger.controller;

import com.roger.pojo.Result;
import com.roger.pojo.User;
import com.roger.service.CustomUserDetailsService;
import com.roger.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    PasswordEncoder passwordEncoder;

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

    /**
     * 刪除會員
     */
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

    /**
     * 修改會員資料
     */
    @PutMapping("/updateUser")
    public Result updateUser(User user) {
        // 參數較驗
        user = userService.findByUserName(user.getUsername());

        if (user != null) {
            // 修改 user
            userService.upateUser(user);
            return Result.success("修改成功");
        } else {
            // 沒有該會員資料返回錯誤訊息
            return Result.error("沒有此會員訊息，修改失敗");
        }
    }

    @GetMapping("/search")
    public Result search(@RequestParam(value = "id", required = false) String id,
                         @RequestParam(value = "username", required = false) String username,
                         @RequestParam(value = "password", required = false) String password,
                         @RequestParam(value = "nickname", required = false) String nickname,
                         @RequestParam(value = "email", required = false) String email) {
        // 建構查詢條件物件
        User query = new User();

        // 設置查詢條件
        if (id != null) {
            query.setId(Integer.parseInt(id));
        }
        if (username != null && !username.isEmpty()) {
            query.setUsername(username);
        }
        if (password != null && !password.isEmpty()) {
            query.setPassword(password);
        }
        if (nickname != null && !nickname.isEmpty()) {
            query.setNickname(nickname);
        }
        if (email != null && !email.isEmpty()) {
            query.setEmail(email);
        }

        User user = userService.findUsersByQuery(query);

        if (user != null) {
            return Result.success(user.getUsername());
        } else {
            return Result.error("找到到會員資料");
        }
    }


}
