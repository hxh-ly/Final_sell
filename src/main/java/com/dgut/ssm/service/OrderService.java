package com.dgut.ssm.service;

import com.dgut.ssm.bean.Orders;

import java.util.List;

public interface OrderService {
    public List<Orders>getOrderByConId(int id);
    public Integer getOrder(int id);
}
