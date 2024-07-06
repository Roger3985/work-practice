package com.roger.controller;

import com.roger.dto.UserDto;
import com.roger.pojo.Result;
import com.roger.pojo.User;
import com.roger.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Tag(name= "controller block")
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
    @Operation(
            summary = "獲得當前用戶名",
            description = "查詢登入中的會員"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "狀態碼正常"),
            @ApiResponse(responseCode = "401", description = "沒有權限"),
            @ApiResponse(responseCode = "404", description = "找不到路徑")
    })
    @GetMapping("/current-user")
    public String getCurrentUser() {
        return userService.getCurrentUserDetails().getUsername();
    }

    /**
     * 自定義會員登入
     */
    @Operation(
            summary = "自定義登入路徑",
            description = "透過此路徑呈現與預設登入同樣的效果"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "狀態碼正常", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Result.class))),
            @ApiResponse(responseCode = "401", description = "沒有權限", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Result.class))),
            @ApiResponse(responseCode = "404", description = "找不到路徑", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Result.class)))
    })
    @PostMapping("/userLogin")
    public Result<String> userLogin(@Valid @RequestBody UserDto userDto, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        return userService.userLogin(userDto, httpServletRequest, httpServletResponse);
    }

    /**
     * 會員註冊 (增)
     */
    @Operation(
            summary = "會員註冊",
            description = "透過此路徑註冊新的會員"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "狀態碼正常", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Result.class))),
            @ApiResponse(responseCode = "401", description = "沒有權限", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Result.class))),
            @ApiResponse(responseCode = "404", description = "找不到路徑", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Result.class)))
    })
    @PostMapping("/register")
    public Result register(@Valid @RequestBody UserDto userDto) {
        return userService.register(userDto);
    }

    /**
     * 刪除會員
     */
    @Operation(
            summary = "刪除會員",
            description = "透過此路徑刪除現存在的會員"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "狀態碼正常", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Result.class))),
            @ApiResponse(responseCode = "401", description = "沒有權限", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Result.class))),
            @ApiResponse(responseCode = "404", description = "找不到路徑", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Result.class)))
    })
    @DeleteMapping("/deleteUser")
    public Result deleteUser(@Valid @RequestBody UserDto userDto) {
        return userService.deleteUser(userDto);
    }

    /**
     * 修改會員資料
     */
    @Operation(
            summary = "更新會員資料",
            description = "透過此路徑更新相關的會員資料"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "狀態碼正常", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Result.class))),
            @ApiResponse(responseCode = "401", description = "沒有權限", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Result.class))),
            @ApiResponse(responseCode = "404", description = "找不到路徑", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Result.class)))
    })
    @PutMapping("/updateUser")
    public Result updateUser(@Valid @RequestBody UserDto userDto) {
        return userService.upateUser(userDto);
    }

    /**
     * 透過有傳入的參數去找到對應的會員，假如沒有傳入就不帶入到查詢 (交集)
     */
    @Operation(
            summary = "搜尋會員資料(交集)",
            description = "透過此路徑搜尋相關的會員資料(交集)"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "狀態碼正常", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Result.class))),
            @ApiResponse(responseCode = "401", description = "沒有權限", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Result.class))),
            @ApiResponse(responseCode = "404", description = "找不到路徑", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Result.class)))
    })
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
    @Operation(
            summary = "搜尋會員資料(聯集)",
            description = "透過此路徑搜尋相關的會員資料(聯集)"
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "狀態碼正常", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Result.class))),
            @ApiResponse(responseCode = "401", description = "沒有權限", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Result.class))),
            @ApiResponse(responseCode = "404", description = "找不到路徑", content = @Content(mediaType = "application/json", schema = @Schema(implementation = Result.class)))
    })
    @GetMapping("/searchByUnion")
    public Result searchByUnion(@RequestParam(value = "username", required = false) String username,
                                @RequestParam(value = "nickname", required = false) String nickname,
                                @RequestParam(value = "email",required = false) String email) {
        // 將傳入參數都放入 userDto
        User user = new User(username, nickname, email);
        return userService.findUserUnion(user);
    }
}
