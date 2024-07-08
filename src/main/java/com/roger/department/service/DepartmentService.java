package com.roger.department.service;

import com.roger.department.pojo.Department;

public interface DepartmentService {

    /**
     * 透過部門名稱查找部門
     */
    Department findByDepartmentName(String name);

    /**
     * 新增部門
     */
    Integer addDepartment(String name);
}
