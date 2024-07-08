package com.roger.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.context.DelegatingSecurityContextRepository;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.RequestAttributeSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityContextRepository securityContextRepository() {
        return new HttpSessionSecurityContextRepository();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .securityContext((securityContext) -> securityContext
                    .securityContextRepository(new DelegatingSecurityContextRepository(
                            new RequestAttributeSecurityContextRepository(),
                            new HttpSessionSecurityContextRepository()
                    ))
            );

        return http
            // 設定 Session 的創建機制
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
            )
            // 設定 api 的權限控制
            .authorizeHttpRequests((requests) -> requests
                .requestMatchers(
                        "/**",
                        // swagger 相關文件
                        "/api/v1/auth/**",
                        "/v2/api-docs/",
                        "/v3/api-docs",
                        "/v3/api-docs/**",
                        "/swagger-resources",
                        "/swagger-resources/**",
                        "/configuration/ui",
                        "/configuration/security",
                        "/swagger-ui/**",
                        "/webjars/**",
                        "/swagger-ui.html",
                        "/swagger-ui/index.html",
                        "/api-docs/swagger-config", // 新增這行
                        "/api-docs/**", // 確保 api-docs 被允許訪問
                        // 註冊功能
                        "/user/register",
                        // 登入功能
                        "/user/userLogin").permitAll()
                // 除了註冊跟登入功能外其他的都需要
                .anyRequest().authenticated() // 其他認證過後就可以登入
            )
            // 禁用 httpBasic
            .httpBasic(httpBasic -> httpBasic.disable())
            // 禁用 CSRF
            .csrf(csrfConfigurer -> csrfConfigurer.disable())
            // 允許 CORS
            .cors(Customizer.withDefaults())
            .build();
    }
}
