package com.dgut.ssm.service;

import com.dgut.ssm.bean.Stock;
import com.dgut.ssm.bean.WaitingList;

import java.util.List;
import java.util.Map;

public interface StockService {
     public int addStock(Stock stock);
     public List<Stock> getAllStock();

     public  List<WaitingList> getWaitingList();
     public Map<String,Object> getNumNameByOgid(Integer ogid);
     public Integer newAStock(WaitingList waitingList);
}
