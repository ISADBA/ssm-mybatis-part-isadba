package com.atguigu.mapper;

import com.atguigu.pojo.Order;

/**
 * ClassName: OrderMapper
 * Package: com.atguigu.mapper
 * Description:
 *
 * @Author: fenghao
 * @Create 2023/12/14 17:15
 * @Version 1.0
 */
public interface OrderMapper {
    //根据id查询订单信息和订单对应的客户
    Order queryOrderById(Integer id);
}
