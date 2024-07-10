package com.roger.master.service.impl;

import com.roger.department.pojo.Department;
import com.roger.department.service.DepartmentService;
import com.roger.master.service.MasterService;
import com.roger.user.dto.UserDto;
import com.roger.user.mapper.UserMapper;
import com.roger.user.pojo.Result;
import com.roger.user.pojo.User;
import com.roger.user.service.UserService;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.Chlid;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
//@Log4j2
public class MasterServiceImpl implements MasterService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /*
    * @Autowired
    * private Logger logger;
    */

    private static final Logger logger = LogManager.getLogger();

    /**
     * 交互 userService and DepartmentService
     */
    @Override
    // @Transactional(propagation = Propagation.REQUIRED) 有交易的情況下，還能各自獨立交易 作法一
    @Transactional
    public Result UserWithDepartment(UserDto userDto) {
        // 添加部門的相關邏輯
        Department department = departmentService.addDepartment(userDto);

        userDto.setDepartment(department);
        // 添加使用者的相關邏輯
        User user = userService.register(userDto);
        // 返回錯誤結果
        if (user != null) {
            logger.debug("添加會員失敗");
            return Result.error("該會員名稱已經被註冊");
        }

        logger.info("新增會員成功");
        return Result.success("新增會員成功，加入" + department.getName() + "部門");
    }

    /**
     * 查詢員工與所在部門相關訊息
     */
    @Override
    public UserDto findByUserNameWithDepartment(UserDto userDto) {
        User user = userService.findByUserName(userDto.getUsername());
        userDto.setUsername(user.getUsername());
        userDto.setDepartment(user.getDepartment());
        return userDto;
    }
}

