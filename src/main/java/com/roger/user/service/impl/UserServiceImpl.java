package com.roger.user.service.impl;

import com.roger.department.pojo.Department;
import com.roger.department.service.DepartmentService;
import com.roger.user.dto.UserDto;
import com.roger.user.mapper.UserMapper;
import com.roger.user.pojo.Result;
import com.roger.user.pojo.User;
import com.roger.user.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "service block")
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private SecurityContextRepository securityContextRepository;

    @Autowired
    private TransactionTemplate transactionTemplate;

    /**
     * 查看當前登入會員
     */
    @Override
    public UserDetails getCurrentUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            return (UserDetails) authentication.getPrincipal();
        }
        return null;
    }

    /**
     * 自定義登入會員
     */
    @Override
    public Result userLogin(UserDto userDto, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        // 判斷會員名稱是否存在
        User loginUser = userMapper.findByUserName(userDto.getUsername());

        // 判斷該會員是否存在
        if (loginUser == null) {
            return Result.error("會員名稱錯誤");
        }

        // 判斷密碼是否正確 loginUser 物件中的 password 是密文
        if (passwordEncoder.matches(userDto.getPassword(), loginUser.getPassword())) {
            // 登入成功，創建認證物件並設置
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(userDto.getUsername());
            // 使用 UsernamePasswordAuthenticationToken 進行認證
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, passwordEncoder.encode(userDto.getPassword()), userDetails.getAuthorities());
            //設置到上下文中
            SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
            securityContext.setAuthentication(authentication);
            SecurityContextHolder.setContext(securityContext);
            securityContextRepository.saveContext(securityContext, httpServletRequest, httpServletResponse);
            return Result.success("登入成功");
        }
        return Result.error("密碼錯誤");
    }

    /**
     * 註冊會員
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW) // 有交易的情況下，還能各自獨立交易 作法二
//    @Transactional
    public User register(UserDto userDto) {
        // 檢查會員是否存在
        User user = userMapper.findByUserName(userDto.getUsername());
        if (user != null) {
            return user;
        }
        // Passwordencoder 加密
        String hashPassword = passwordEncoder.encode(userDto.getPassword());
        // 添加新會員
        userMapper.addUser(userDto.getUsername(), hashPassword, userDto.getId());
        // int i = 1 / 0; // simulate an exception
        return user;
    }

    /* @Override
    public User register(UserDto userDto) {
        return transactionTemplate.execute(status -> {
            try {
                // MyBatis 操作，這裡示範使用 UserMapper
                User user = userMapper.findByUserName(userDto.getUsername());
                if (user != null) {
                    return user; // 已存在的用戶返回
                }
                String hashedPassword = passwordEncoder.encode(userDto.getPassword());
                userMapper.addUser(userDto.getUsername(), hashedPassword, userDto.getId());
                int i = 1 / 0; // simulate an exception
                return userMapper.findByUserName(userDto.getUsername()); // 返回新創建的用戶
            } catch (Exception e) {
                status.setRollbackOnly(); // 發生異常時回滾事務
                throw new RuntimeException("Failed to register user", e);
            }
        });
    } */

    /**
     * 刪除 user
     */
    @Override
    @Transactional
    public Result deleteUser(UserDto userDto) {
        // 檢查該會員是否存在
        User user = userMapper.findByUserName(userDto.getUsername());

        if (user != null && passwordEncoder.matches(userDto.getPassword(), user.getPassword())) {
            userMapper.deleteUser(user);
            return Result.success("該會員已經被刪除");
        } else {
            return Result.error("該會員不符合相關資格刪除此會員");
        }
    }

    /**
     * 修改會員資料
     */
    @Override
    @Transactional
    public Result upateUser(UserDto userDto) {
        // 檢查該會員是否存在
        User user = userMapper.findByUserName(userDto.getUsername());

        if (user != null) {
            // 修改會員資料
            user.setUsername(userDto.getUsername());
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            user.setNickname(userDto.getNickname());
            user.setEmail(userDto.getEmail());
            userMapper.updateUser(user);
            return Result.success("修改成功");
        } else {
            // 沒有該會員資料返回錯誤訊息
            return Result.error("沒有此會員訊息，修改失敗");
        }
    }

    /**
     * 透過送入的會員資料查詢
     */
    @Override
    @Transactional
    public Result findUserIntersection(User user) {
        List<User> users = userMapper.findByUserQueryIntersection(user);
        if (!users.isEmpty()) {
            List<String> usernames = users.stream()
                    .map(User::getUsername)
                    .collect(Collectors.toList());
            return Result.success(usernames);
        } else {
            return Result.error("找不到會員資料");
        }
    }

    /**
     * 透過送入的會員資料查詢 (聯集)
     */
    @Override
    @Transactional
    public Result findUserUnion(User user) {
        List<User> users = userMapper.findByUserQueryUnion(user);
        if (!users.isEmpty()) {
            List<String> usernames = users.stream()
                    .map(User::getUsername)
                    .collect(Collectors.toList());
            return Result.success(usernames);
        }
        return Result.error("找不到會員資料");
    }

    /**
     * 透過 username 透過會員名稱查找會員
     */
    @Override
    @Transactional
    public User findByUserName(String username) {
        User user = userMapper.findByUserName(username);
        return user;
    }
}
