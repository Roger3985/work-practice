
package com.roger.controller;

import com.roger.pojo.Result;
import com.roger.utils.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@RequestMapping("/article")
public class ArticleController {

    @GetMapping("/list")
    public Result<String> list(@RequestHeader(name = "Authorization") String token,
                               HttpServletResponse response) {
        // 驗證 token (以下資料放到 Interceptor 裡面判斷)
//        try {
//            Map<String, Object> claims = JwtUtil.verifyToken(token);
//            return Result.success("所有的文章資料");
//        } catch (Exception e) {
//            // http 回應頭狀態碼為 401
//            response.setStatus(401);
//            return Result.error("未登入");
//        }
        return Result.success("所有的文章資料");
    }
}
