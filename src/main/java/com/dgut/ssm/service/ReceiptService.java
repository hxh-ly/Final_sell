package com.dgut.ssm.service;

import com.dgut.ssm.bean.Receipt;

import java.util.List;

public interface ReceiptService {
    public void addReceipt(Receipt receipt);
    public void addInMerge(int eid,int oid,int gid,int need);
    public  Integer getCidByEid(Integer eid);
    public List<Integer> isAllSend(Integer cid);
}
