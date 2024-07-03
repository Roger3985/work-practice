//package com.roger.controller.backup;
//
//import com.roger.pojo.Result;
//import com.roger.pojo.User;
//import com.roger.service.UserService;
//import jakarta.validation.constraints.Pattern;
//import lombok.extern.slf4j.Slf4j;
//import org.mindrot.jbcrypt.BCrypt;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.web.bind.annotation.*;
//
//@Slf4j
//@RestController
//@RequestMapping("/user")
//public class UserController {
//
//    @Autowired
//    UserService userService;
//
//    /**
//     * 註冊會員
//     * @param username 會員名稱
//     * @param password 會員密碼
//     * @return 返回結果
//     */
//    @PostMapping("/register")
//    public Result register(String username, String password) {
//        User user = userService.findByUserName(username);
//        if (user == null) {
//            // 沒被占用 -> 可以註冊新的會員
//            userService.register(username, password);
//            return Result.success();
//        } else {
//            // 被占用返回錯誤訊息
//            return Result.error("會員名已被佔用");
//        }
//    }
//
//    /**
//     * 利用會員姓名查找會員 ID
//     * @param username 會員姓名
//     * @return 返回會員 ID
//     */
//    @GetMapping("/findByUserName")
//    public int findByUserName(String username) {
//        User user = userService.findByUserName(username);
//        return user.getId();
//    }
//
//    // 定義 BCryptPasswordEncoder 物件
//    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//
//    /**
//     * 會員登入
//     * @param username 會員名稱
//     * @param password 會員密碼
//     * @return
//     */
//    @PostMapping("/login")
//    public Result<String> login(@Pattern(regexp = "^\\w{5,16}$") String username, @Pattern(regexp = "^\\w{5,16}$") String password) {
//        // 判斷會員名稱是否存在
//        User loginUser = userService.findByUserName(username);
//
//        // 判斷該會員是否存在
//        if (loginUser == null) {
//            return Result.error("會員名稱錯誤");
//        }
//
//        System.out.println(BCrypt.hashpw(password, BCrypt.gensalt()));
//        // 判斷密碼是否正確 loginUser 物件中的 password 是密文
//        if (passwordEncoder.matches(password, loginUser.getPassword())) {
//            // 登入成功
//            return Result.success("jwt token 令牌...");
//        }
//
//        return Result.error("密碼錯誤");
//    }
//}
