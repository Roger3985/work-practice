package com.roger.controller;

import com.roger.dto.UserDto;
import com.roger.pojo.Result;
import com.roger.pojo.User;
import com.roger.service.impl.CustomUserDetailsService;
import com.roger.service.UserService;
import jakarta.validation.Valid;
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
    public Result register(@Valid @RequestBody UserDto userDto) {
        return userService.register(userDto);
    }

    /**
     * 刪除會員
     */
    @DeleteMapping("/deleteUser")
    public Result deleteUser(@Valid @RequestBody UserDto userDto) {
        return userService.deleteUser(userDto);
    }

    /**
     * 修改會員資料
     */
    @PutMapping("/updateUser")
    public Result updateUser(@Valid @RequestBody UserDto userDto) {
        return userService.upateUser(userDto);
    }

    @GetMapping("/search")
    public Result search(@RequestParam(value = "username", required = false) String username,
                         @RequestParam(value = "nickname", required = false) String nickname,
                         @RequestParam(value = "email",required = false) String email) {
        // 將傳入參數都放入 userDto
        User user = new User(username, nickname, email);
        return userService.findUser(user);
    }


}
