package com.dgut.ssm.dao;

import com.dgut.ssm.bean.Receipt;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReceiptDao {
    public void addReceipt(Receipt receipt);

    public void addInMerge(@Param("eid") Integer eid, @Param("orderId") Integer oid, @Param("goodId") Integer gid,@Param("need") Integer need);

    public  Integer getCidByEid(Integer eid);

    public List<Integer> isAllSend(Integer cid);

}
