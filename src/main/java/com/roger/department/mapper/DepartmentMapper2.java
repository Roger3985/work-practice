package com.roger.department.mapper;

import com.roger.department.pojo.Department;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Tag(name = "mapper block")
@Mapper
public interface DepartmentMapper2 {

    /**
     * 透過部門名稱查找部門
     */
    @Select("SELECT * FROM department " +
            "WHERE name=#{name}")
    Department findByDepartmentName(String name);

    /**
     * 新增部門
     */
    @Insert("INSERT INTO department(name) " +
            "VALUES (#{name})")
    void addDepartment(String name);
}
