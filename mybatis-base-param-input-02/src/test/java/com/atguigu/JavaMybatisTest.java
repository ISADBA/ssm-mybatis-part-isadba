package com.atguigu;

import com.atguigu.mapper.CustomerMapper;
import com.atguigu.mapper.EmployeeMapper;
import com.atguigu.mapper.OrderMapper;
import com.atguigu.pojo.Customer;
import com.atguigu.pojo.Employee;
import com.atguigu.pojo.Order;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ClassName: JavaMybatisTest
 * Package: com.atguigu
 * Description:
 *
 * @Author: fenghao
 * @Create 2023/12/11 20:29
 * @Version 1.0
 */
public class JavaMybatisTest {
    private SqlSessionFactory sqlSessionFactory = null;

    {
        try {
            // 1. 获取外部配置文件
            InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");

            // 2. 创建SqlSessionFactory
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    // 完整流程
    @Test
    public void test01() throws IOException {
        // 1. 获取外部配置文件
        InputStream resourceAsStream = Resources.getResourceAsStream("mybatis-config.xml");

        // 2. 创建SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(resourceAsStream);

        // 3. 获取SqlSession【自动开启事务】
        SqlSession sqlSession = sqlSessionFactory.openSession();

        // 4. 获取代理的Mapper对象
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);

        // 5. 执行查询
        Employee employee = employeeMapper.queryById(2);
        System.out.println("employee = " + employee);

        // 6. 提交事务,关闭session
        sqlSession.commit();
        sqlSession.close();
    }

    // insert并获取自增长ID
    @Test
    public void test02(){
        // 3. 获取SqlSession【自动开启事务】
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 4. 获取代理的Mapper对象
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        // 5. 执行插入
        Employee employee = new Employee();
        employee.setEmpName("二狗子");
        employee.setEmpSalary(8888.0);
        int effectRows = employeeMapper.insertEmployee(employee);
        System.out.println("empId = " + effectRows);
        System.out.println("empId: " + employee.getEmpId());
        // 6. 提交事务,关闭session
        sqlSession.commit();
        sqlSession.close();
    }
    // 按主键查询一条数据
    @Test
    public void test03(){
        // 3. 获取SqlSession【自动开启事务】
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 4. 获取代理的Mapper对象
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        // 5. 执行插入
        Employee employee = employeeMapper.queryById(1);
        System.out.println("employee = " + employee);
        // 6. 提交事务,关闭session
        sqlSession.commit();
        sqlSession.close();
    }

    // 按多个条件查询多条数据
    @Test
    public void test04(){
        // 3. 获取SqlSession【自动开启事务】
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 4. 获取代理的Mapper对象
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        // 5. 执行查询
        List<Employee> employeeList = employeeMapper.queryByNameAndSalary("二狗子", 8888.0);
        System.out.println("employee = " + employeeList);
        // 6. 提交事务,关闭session
        sqlSession.commit();
        sqlSession.close();
    }
    // 按多个条件(Map)查询一个单值
    @Test
    public void test05(){
        // 3. 获取SqlSession【自动开启事务】
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 4. 获取代理的Mapper对象
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        // 5. 执行查询
        // 5.1 准备一个map,包含name和salary
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        stringObjectHashMap.put("name","二狗子");
        stringObjectHashMap.put("salary",8888.0);
        // 5.2 调用方法
        String empName = employeeMapper.queryByNameAndSalary2(stringObjectHashMap);
        System.out.println("empName = " + empName);
        // 6. 提交事务,关闭session
        sqlSession.commit();
        sqlSession.close();
    }
    // 使用mapUnderscoreToCamelCase自动转换字段名字,返回列表
    @Test
    public void test06(){
        // 3. 获取SqlSession【自动开启事务】
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 4. 获取代理的Mapper对象
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        // 5. 执行查询
        List<Employee> employee = employeeMapper.queryBySalary(8888.0);
        System.out.println("employee = " + employee);

        // 6. 提交事务,关闭session
        sqlSession.commit();
        sqlSession.close();
    }
    // 传入一个Map类型，返回一个List<Map>类型,传入的map类型可能有字段是空的
    @Test
    public void test07(){
        // 3. 获取SqlSession【自动开启事务】
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 4. 获取代理的Mapper对象
        EmployeeMapper employeeMapper = sqlSession.getMapper(EmployeeMapper.class);
        // 5. 执行查询
        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
        // 参数可传可不传
        stringObjectHashMap.put("name","二狗子");
        //  stringObjectHashMap.put("salary",8888.0);
        List<Map<String,Object>> employee = employeeMapper.queryByNameAndSalary3(stringObjectHashMap);
        System.out.println("employee = " + employee);

        // 6. 提交事务,关闭session
        sqlSession.commit();
        sqlSession.close();
    }
    // 传入一个单值ID，进行连表查询，返回两个表一对一的数据
    @Test
    public void test08(){
        // 3. 获取SqlSession【自动开启事务】
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 4. 获取代理的Mapper对象
        OrderMapper orderMapper = sqlSession.getMapper(OrderMapper.class);
        // 5. 执行查询
        Order order = orderMapper.queryOrderById(1);
        System.out.println("order = " + order);

        // 6. 提交事务,关闭session
        sqlSession.commit();
        sqlSession.close();
    }
    // 不传值，返回两个表一对多的结果
    @Test
    public void test09(){
        // 3. 获取SqlSession【自动开启事务】
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 4. 获取代理的Mapper对象
        CustomerMapper mapper = sqlSession.getMapper(CustomerMapper.class);
        // 5. 执行查询
        List<Customer> customers = mapper.queryList();
        System.out.println("customers = " + customers);

        // 6. 提交事务,关闭session
        sqlSession.commit();
        sqlSession.close();
    }

    // 不传值，返回两个表一对多的结果
    // 通过autoMappingBehavior自动映射返回值
    @Test
    public void test10(){
        // 3. 获取SqlSession【自动开启事务】
        SqlSession sqlSession = sqlSessionFactory.openSession();
        // 4. 获取代理的Mapper对象
        CustomerMapper mapper = sqlSession.getMapper(CustomerMapper.class);
        // 5. 执行查询
        List<Customer> customers = mapper.queryListAuto();
        System.out.println("customers = " + customers);

        // 6. 提交事务,关闭session
        sqlSession.commit();
        sqlSession.close();
    }
}
