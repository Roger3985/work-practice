package com.roger.master.service;

import com.roger.user.dto.UserDto;
import com.roger.user.pojo.Result;

public interface MasterService {

    /**
     * 交互 userService and DepartmentService
     */
    Result UserWithDepartment(UserDto userDto);

    /**
     * 查詢員工與所在部門相關訊息
     */
    UserDto findByUserNameWithDepartment(UserDto userDto);
}
