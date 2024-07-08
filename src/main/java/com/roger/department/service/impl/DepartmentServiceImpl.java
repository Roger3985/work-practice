package com.roger.department.service.impl;

import com.roger.department.mapper.DepartmentMapper;
import com.roger.department.pojo.Department;
import com.roger.department.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    /**
     * 透過部門名稱查找部門
     */
    @Override
    public Department findByDepartmentName(String name) {
        return departmentMapper.findByDepartmentName(name);
    }

    /**
     * 新增部門
     */
    @Override
    public Integer addDepartment(String name) {
        Department department = departmentMapper.addDepartment(name);
        return department.getId();
    }
}
