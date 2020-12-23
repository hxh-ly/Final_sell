package com.dgut.ssm.serviceImpl;

import com.dgut.ssm.bean.Receipt;
import com.dgut.ssm.dao.ReceiptDao;
import com.dgut.ssm.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class ReceiptServiceImpl implements ReceiptService {
    @Autowired
    private ReceiptDao receiptDao;
    public void addReceipt(Receipt receipt) {
        receiptDao.addReceipt(receipt);
    }

    public void addInMerge(int eid, int oid, int gid,int need) {
        receiptDao.addInMerge(eid,oid,gid,need);
    }

    public Integer getCidByEid(Integer eid) {
       return receiptDao.getCidByEid(eid);
    }

    public List<Integer> isAllSend(Integer cid) {
        return  receiptDao.isAllSend(cid);
    }

    public Integer UpdateAmountMid(Integer O_gid, Integer amount) {
        return receiptDao.UpdateAmountMid(O_gid, amount);
    }
}
