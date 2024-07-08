package com.roger.master.service.impl;

import com.roger.department.pojo.Department;
import com.roger.department.service.DepartmentService;
import com.roger.master.service.MasterService;
import com.roger.user.dto.UserDto;
import com.roger.user.pojo.Result;
import com.roger.user.pojo.User;
import com.roger.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MasterServiceImpl implements MasterService {

    @Autowired
    private UserService userService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 交互 userService and DepartmentService
     */
    @Override
    // @Transactional(propagation = Propagation.NOT_SUPPORTED) 有交易的情況下，還能各自獨立交易 作法一
    // @Transactional(propagation = Propagation.REQUIRED)
    @Transactional
    public Result UserWithDepartment(UserDto userDto) {
        // 添加部門的相關邏輯
        Department department = null;
        try {
            department = departmentService.addDepartment(userDto);
        } catch (RuntimeException e) {
            Result.error("添加部門失敗" + e.getMessage());
        }
        department = departmentService.findByDepartmentName(userDto.getName());
        // 放入 id
        userDto.setId(department.getId());

        User user = null;
        // 添加使用者的相關邏輯
        try {
            user = userService.register(userDto);
        } catch (RuntimeException e) {
            Result.error("添加使用者失敗" + e.getMessage());
        }

        // 返回錯誤結果
        if (user != null) {
            return Result.error("該會員名稱已經被註冊");
        }
        return Result.success("新增會員成功，加入" + department.getName() + "部門");
    }
}