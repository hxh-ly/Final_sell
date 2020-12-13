package com.dgut.ssm.service;

import com.dgut.ssm.bean.Receipt;

public interface ReceiptService {
    public void addReceipt(Receipt receipt);
    public void addInMerge(int eid,int oid,int gid);
}
