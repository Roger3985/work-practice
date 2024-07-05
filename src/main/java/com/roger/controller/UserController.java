package com.roger.controller;

import com.roger.dto.UserDto;
import com.roger.pojo.Result;
import com.roger.pojo.User;
import com.roger.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
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
     * 自定義會員登入
     */
    @PostMapping("/userLogin")
    public Result<String> userLogin(@Valid @RequestBody UserDto userDto, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        return userService.userLogin(userDto, httpServletRequest, httpServletResponse);
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

    /**
     * 透過有傳入的參數去找到對應的會員，假如沒有傳入就不帶入到查詢 (交集)
     */
    @GetMapping("/searchByIntersection")
    public Result searchByIntersection(@RequestParam(value = "username", required = false) String username,
                                       @RequestParam(value = "nickname", required = false) String nickname,
                                       @RequestParam(value = "email",required = false) String email) {
        // 將傳入參數都放入 User
        User user = new User(username, nickname, email);
        return userService.findUserIntersection(user);
    }

    /**
     * 透過有傳入的參數去找到對應的會員，假如沒有傳入就不帶入到查詢 (聯集)
     */
    @GetMapping("/searchByUnion")
    public Result searchByUnion(@RequestParam(value = "username", required = false) String username,
                                @RequestParam(value = "nickname", required = false) String nickname,
                                @RequestParam(value = "email",required = false) String email) {
        // 將傳入參數都放入 userDto
        User user = new User(username, nickname, email);
        return userService.findUserUnion(user);
    }

}
