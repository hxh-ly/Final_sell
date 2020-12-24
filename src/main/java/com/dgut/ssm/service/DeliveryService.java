package com.dgut.ssm.service;

import com.dgut.ssm.bean.Delivery;
import com.dgut.ssm.bean.WaitingList;

import java.util.List;

public interface DeliveryService {
    public List<Delivery> getDeliveryInfo();

    public int updateQuantity(Integer eid);
    //根据eid修改数量
    public int addGoodsQuantity(Integer amount,Integer eid);
    //根据gid修改数量
    public int updateQuantityByGid(Integer amount,Integer gid);
    //根据O_gid 更新未发货进货表的发货状态
    public  int updateWaitStock(Integer O_gid);

}
