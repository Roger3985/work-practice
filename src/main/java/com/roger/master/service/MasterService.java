package com.roger.master.service;

import com.roger.user.dto.UserDto;
import com.roger.user.pojo.Result;
public interface MasterService {

    /**
     * 交互 userService and DepartmentService
     */
    Result UserWithDepartment(UserDto userDto);
}
