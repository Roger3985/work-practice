package com.roger.master.service.impl;

import com.roger.department.pojo.Department;
import com.roger.department.service.DepartmentService;
import com.roger.master.service.MasterService;
import com.roger.user.dto.UserDto;
import com.roger.user.pojo.Result;
import com.roger.user.pojo.User;
import com.roger.user.service.UserService;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

@Service
public class MasterServiceImpl implements MasterService {

    @Autowired
    private UserService userService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private Logger logger;

    /**
     * 交互 userService and DepartmentService
     */
    @Override
    // @Transactional(propagation = Propagation.NOT_SUPPORTED) 有交易的情況下，還能各自獨立交易 作法一
    // @Transactional(propagation = Propagation.REQUIRED)
    @Transactional
    public Result UserWithDepartment(UserDto userDto) {
        // 添加部門的相關邏輯
        Department department = departmentService.addDepartment(userDto);
        department = departmentService.findByDepartmentName(userDto.getName());
        // 放入 id
        userDto.setId(department.getId());
        User user = userService.register(userDto);


        // 返回錯誤結果
        if (user != null) {
            logger.error("添加會員失敗");
            return Result.error("該會員名稱已經被註冊");
        }
        logger.info("新增會員成功");
        return Result.success("新增會員成功，加入" + department.getName() + "部門");
    }
}

