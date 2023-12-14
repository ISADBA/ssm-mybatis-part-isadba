package com.atguigu.mapper;

import com.atguigu.pojo.Customer;

import java.util.List;

/**
 * ClassName: CustomerMapper
 * Package: com.atguigu.mapper
 * Description:
 *
 * @Author: fenghao
 * @Create 2023/12/14 17:17
 * @Version 1.0
 */
public interface CustomerMapper {
    //查询所有客户信息以及客户对应的订单信息
    List<Customer> queryList();

    //查询所有客户信息以及客户对应的订单信息,通过autoMappingBehavior自动映射返回值
    List<Customer> queryListAuto();
}
