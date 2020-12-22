package com.dgut.ssm.serviceImpl;

import com.dgut.ssm.bean.Orders;
import com.dgut.ssm.dao.OrderDao;
import com.dgut.ssm.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderDao orderDao;
    public List<Orders> getOrderByConId(int id) {
        return  orderDao.getOrderByContractId(id);

    }

    public Integer getOrder(int id) {
        return orderDao.getOrder(id);
    }

    @Transactional
    public Integer addOrder(Integer cid) {
      int i=0;
        try {
          i= orderDao.addOrder(cid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  i;
    }


    public Integer insertGoods(Integer gid, Integer oid, Integer amount) {
      return   orderDao.insertGoods(gid, oid, amount);
    }
}
