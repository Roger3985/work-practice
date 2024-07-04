package com.roger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
            .csrf(csrfConfigurer -> csrfConfigurer.disable())// 禁用 CSRF
            // 設定 Session 的創建機制
            .sessionManagement(session -> session
                    .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
            )
            // 設定 Http Basic 認證和表單認證
            .formLogin(Customizer.withDefaults())
            .httpBasic(Customizer.withDefaults())
            // 設定 api 的權限控制
            .authorizeHttpRequests((requests) -> requests
                // 註冊功能
                .requestMatchers("/user/register").permitAll()
                // 登入功能
                .requestMatchers("/user/userLogin").permitAll()
                // 除了註冊跟登入功能外其他的都需要
                .anyRequest().authenticated() // 其他認證過後就可以登入
            )
            .build();
    }
}
