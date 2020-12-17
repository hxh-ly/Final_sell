package com.dgut.ssm.dao;

import com.dgut.ssm.bean.Receipt;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface ReceiptDao {
    public void addReceipt(Receipt receipt);

    public void addInMerge(@Param("eid") Integer eid, @Param("orderId") Integer oid, @Param("goodId") Integer gid);
    //查找货单中看有没有


}
