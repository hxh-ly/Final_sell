package com.dgut.ssm.dao;

import com.dgut.ssm.bean.Delivery;
import com.dgut.ssm.bean.WaitingList;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DeliveryDao {
    public List<Delivery> getDeliveryInfo();
    public  int getQuantity(Integer gid);
    public int  updateStatus(Integer eid);
    //修改中间表是否发货状态
    public  int updateGom(Integer eid);
    //修改待进货表是否进货状态
    public  int updateWaitStock(Integer O_gid);
    public  int getDeliveryAmount(Integer eid);
    public  int getDeliveryGid(Integer eid);
    public Integer updateQuantity(@Param("amount") Integer amount, @Param("gid") Integer gid);
    public  int addGoodsQuantity(@Param("amount") Integer amount,@Param("gid")Integer gid);


}
