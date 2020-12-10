package com.dgut.ssm.dao;

import com.dgut.ssm.bean.Orders;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OrderDao {
    public List<Orders> getOrderByContractId(int id);


}
