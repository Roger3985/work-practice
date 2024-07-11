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
        // int i = 1 / 0;
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
        Department existingDepartment = departmentMapper.findByDepartmentName(userDto.getName());
        if (existingDepartment != null) {
            return existingDepartment;
        }
        // 部門不存在，創建新部門
        Department newDepartment = new Department();
        newDepartment.setName(userDto.getName());
        departmentMapper.addDepartment(newDepartment.getName());

        // 檢查新部門的 id 是否已經被設置
        if (newDepartment.getId() != null) {
            System.out.println("New department ID: " + newDepartment.getId());
        } else {
            System.err.println("Failed to retrieve the generated key for the new department.");
        }
        return newDepartment;
    }
}
