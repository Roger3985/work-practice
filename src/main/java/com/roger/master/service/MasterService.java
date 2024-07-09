package com.roger.master.service;

import com.roger.user.dto.UserDto;
import com.roger.user.pojo.Result;
import com.roger.user.pojo.User;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

public interface MasterService {

    /**
     * 交互 userService and DepartmentService
     */
    Result UserWithDepartment(UserDto userDto);
}
