package com.dgut.ssm.serviceImpl;

import com.dgut.ssm.bean.Stock;
import com.dgut.ssm.bean.WaitingList;
import com.dgut.ssm.dao.StockDao;
import com.dgut.ssm.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class StockServiceImpl implements StockService {
    @Autowired
   private   StockDao stockDao;
    @Transactional
    public int addStock(Stock stock) {
        int i=0;
        try {
            i= stockDao.addStock(stock);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return i;
    }

    public List<Stock> getAllStock() {
        List<Stock> allStock = stockDao.getAllStock();
        if(allStock!=null)
            return allStock;
        else return null;
    }
    public List<WaitingList> getWaitingList() {
        return stockDao.getWaitingList();
    }

    public Map<String, Object> getNumNameByOgid(Integer ogid) {
        return stockDao.getNumNameByOgid(ogid);
    }
}
