package com.dgut.ssm.service;

import com.dgut.ssm.bean.Delivery;
import com.dgut.ssm.bean.WaitingList;

import java.util.List;

public interface DeliveryService {
    public List<Delivery> getDeliveryInfo();

    public int updateQuantity(Integer eid);

    public int addGoodsQuantity(Integer amount,Integer eid);

}
