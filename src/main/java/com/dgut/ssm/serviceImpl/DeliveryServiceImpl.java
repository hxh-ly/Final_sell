package com.dgut.ssm.serviceImpl;

import com.dgut.ssm.bean.Delivery;
import com.dgut.ssm.bean.WaitingList;
import com.dgut.ssm.dao.DeliveryDao;
import com.dgut.ssm.dao.GoodsDao;
import com.dgut.ssm.exception.QuantityException;
import com.dgut.ssm.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class DeliveryServiceImpl implements DeliveryService {
    @Autowired
    private  DeliveryDao deliveryDao;

    public List<Delivery> getDeliveryInfo() {
        return   deliveryDao.getDeliveryInfo();
    }
    @Transactional
    public int updateQuantity(Integer eid) {
        int deliveryAmount = deliveryDao.getDeliveryAmount(eid);
        int deliveryGid = deliveryDao.getDeliveryGid(eid);
        //修改数量
        Integer i = deliveryDao.updateQuantity(deliveryAmount, deliveryGid);
        //修改receipts（货单表）状态
        deliveryDao.updateStatus(eid);
        //修改gom(中间表)状态
        deliveryDao.updateGom(eid);
        //查询数量
        int quantity = deliveryDao.getQuantity(deliveryGid);
        if(i!=0&&quantity>0)
             return 1;
        else if(quantity==0){
            return 2;
        }
         else {
             throw new QuantityException("Quantity not enough");
        }

    }

    @Transactional
    public int addGoodsQuantity(Integer amount,Integer eid) {
        int gid = deliveryDao.getDeliveryGid(eid);
        int i=0;
        try {
            i = deliveryDao.addGoodsQuantity(amount,gid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i+3;
    }



}
