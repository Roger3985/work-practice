//
//package com.roger.utils;
//
//import com.auth0.jwt.JWT;
//import com.auth0.jwt.algorithms.Algorithm;
//
//import java.util.Date;
//import java.util.Map;
//
//public class JwtUtil {
//
//    private static final String KEY = "roger";
//
//    // 接收業務資料，生成 token 並返回
//    public static String generateToken(Map<String, Object> claims) {
//        return JWT.create()
//                .withClaim("claims", claims)
//                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 12))
//                .sign(Algorithm.HMAC256(KEY));
//    }
//
//    // 接收 token，校驗 token ，並返回業務資料
//    public static Map<String, Object> verifyToken(String token) {
//        return JWT.require(Algorithm.HMAC256(KEY))
//                .build()
//                .verify(token)
//                .getClaim("claims")
//                .asMap();
//    }
//}
