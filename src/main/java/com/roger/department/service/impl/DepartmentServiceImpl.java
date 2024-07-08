package com.roger.department.service.impl;

import com.roger.department.mapper.DepartmentMapper;
import com.roger.department.pojo.Department;
import com.roger.department.service.DepartmentService;
import com.roger.user.dto.UserDto;
import com.roger.user.pojo.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
    // @Transactional(propagation = Propagation.REQUIRES_NEW) // 有交易的情況下，還能各自獨立交易 作法二
    @Transactional
    public Department addDepartment(UserDto userDto) {
        // 檢查部門是否存在
        Department department = departmentMapper.findByDepartmentName(userDto.getName());
        if (department != null) {
            return department;
        }
        departmentMapper.addDepartment(userDto.getName());
        return department;
    }
}
