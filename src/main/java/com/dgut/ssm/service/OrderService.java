package com.dgut.ssm.service;

import com.dgut.ssm.bean.Orders;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderService {
    public List<Orders>getOrderByConId(int id);
    public Integer getOrder(int id);
    public  Integer addOrder(Integer cid);
    public Integer insertGoods( Integer gid, Integer oid, Integer amount);
}
