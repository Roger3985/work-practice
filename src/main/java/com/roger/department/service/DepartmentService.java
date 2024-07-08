package com.roger.department.service;

import com.roger.department.pojo.Department;
import com.roger.user.dto.UserDto;
import com.roger.user.pojo.Result;

public interface DepartmentService {

    /**
     * 透過部門名稱查找部門
     */
    Department findByDepartmentName(String name);

    /**
     * 新增部門
     */
    Department addDepartment(UserDto userDto);
}
