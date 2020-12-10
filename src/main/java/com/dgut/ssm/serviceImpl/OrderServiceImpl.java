package com.dgut.ssm.serviceImpl;

import com.dgut.ssm.bean.Orders;
import com.dgut.ssm.dao.OrderDao;
import com.dgut.ssm.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    public List<Orders> getOrderByConId(int id) {
        return  orderDao.getOrderByContractId(id);

    }
}
