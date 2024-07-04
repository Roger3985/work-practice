package com.roger.controller;

import com.roger.pojo.Result;
import com.roger.pojo.User;
import com.roger.service.impl.CustomUserDetailsService;
import com.roger.service.UserService;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

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
     * 查詢登入中的會員
     * @return 會員的細節
     */
    @GetMapping("/current-user")
    public String getCurrentUser() {
        return userService.getCurrentUserDetails().getUsername();
    }

    /**
     * 會員登入
     * @param username 會員名稱
     * @param password 會員密碼
     * @return Result 結果(裡面會有正確內容或錯誤內容)
     */
    @PostMapping("/userLogin")
    public Result<String> login(@Pattern(regexp = "^\\w{5,16}$") @RequestParam("username") String username,
                                @Pattern(regexp = "^\\w{5,16}$") @RequestParam("password") String password) {
        // 判斷會員名稱是否存在
        User loginUser = userService.findByUserName(username);

        // 判斷該會員是否存在
        if (loginUser == null) {
            return Result.error("會員名稱錯誤");
        }

        // 判斷密碼是否正確 loginUser 物件中的 password 是密文
        if (passwordEncoder.matches(password, loginUser.getPassword())) {
            // 登入成功
            return Result.success("登入成功");
        }

        return Result.error("密碼錯誤");
    }

    /**
     * 會員註冊 (增)
     */
    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\w{5,16}$") @RequestParam("username") String username,
                           @Pattern(regexp = "^\\w{5,16}$") @RequestParam("password") String password) {
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
    @DeleteMapping("/deleteUser/{username}/{password}")
    public Result deleteUser(@PathVariable("username") String username,
                             @PathVariable("password") String password) {
        // 參數較驗
        User user = userService.findByUserName(username);

        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
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
            return Result.error("找不到會員資料");
        }
    }


}
