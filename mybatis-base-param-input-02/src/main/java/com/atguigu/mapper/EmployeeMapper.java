package com.atguigu.mapper;

import com.atguigu.pojo.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * ClassName: EmployeeMapper
 * Package: com.atguigu.mapper
 * Description:
 *
 * @Author: fenghao
 * @Create 2023/12/11 20:03
 * @Version 1.0
 */
public interface EmployeeMapper {
    // 根据ID查询员工信息
    public Employee queryById(Integer id);

    // 根据ID删除员工信息
    public int deleteById(Integer id);

    // 根据工资查询员工列表
    public List<Employee> queryBySalary(Double salary);

    //  插入一条新数据
    public int insertEmployee(Employee employee);

    // 根据员工姓名和工资查询信息,使用简单参数传递
    public List<Employee> queryByNameAndSalary(@Param("a") String name, @Param("b") Double salary);

    // 根据员工姓名和工资查询信息，使用map传值
    public String queryByNameAndSalary2(Map map);

    // 根据员工姓名和工资查询信息，使用map传值
    public List<Map<String,Object>> queryByNameAndSalary3(Map map);
}
