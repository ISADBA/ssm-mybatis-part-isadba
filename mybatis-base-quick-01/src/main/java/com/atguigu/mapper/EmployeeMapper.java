package com.atguigu.mapper;

import com.atguigu.pojo.Employee;

/**
 * ClassName: EmployeeMapper
 * Package: com.atguigu.mapper
 * Description:
 *
 * @Author: fenghao
 * @Create 2023/12/11 18:39
 * @Version 1.0
 */
public interface EmployeeMapper {
    Employee queryById(Integer id);
}
