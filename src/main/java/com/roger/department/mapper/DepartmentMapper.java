package com.roger.department.mapper;

import com.roger.department.pojo.Department;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.ibatis.annotations.Mapper;

@Tag(name = "mapper block")
@Mapper
public interface DepartmentMapper {

    /**
     * 透過部門名稱查找部門
     */
    Department findByDepartmentName(String name);

    /**
     * 新增部門
     */
     void addDepartment(String name);
}
