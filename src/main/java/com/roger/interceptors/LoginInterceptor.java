//
//package com.roger.interceptors;
//
//import com.roger.pojo.Result;
//import com.roger.utils.JwtUtil;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.HandlerInterceptor;
//
//import java.util.Map;
//
//@Component
//public class LoginInterceptor implements HandlerInterceptor {
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        // 令牌驗證
//        String token = request.getHeader("Authorization");
//        // 驗證 token
//        try {
//            Map<String, Object> claims = JwtUtil.verifyToken(token);
//            return true; // 放行
//        } catch (Exception e) {
//            // http 回應頭狀態碼為 401
//            response.setStatus(401);
//            return false; // 不放行
//        }
//    }
//}
