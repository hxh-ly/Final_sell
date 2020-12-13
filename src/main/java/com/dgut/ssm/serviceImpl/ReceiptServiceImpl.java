package com.dgut.ssm.serviceImpl;

import com.dgut.ssm.bean.Receipt;
import com.dgut.ssm.dao.ReceiptDao;
import com.dgut.ssm.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReceiptServiceImpl implements ReceiptService {
    @Autowired
    private ReceiptDao receiptDao;
    public void addReceipt(Receipt receipt) {
        receiptDao.addReceipt(receipt);
    }

    public void addInMerge(int eid, int oid, int gid) {
        receiptDao.addInMerge(eid,oid,gid);
    }
}
