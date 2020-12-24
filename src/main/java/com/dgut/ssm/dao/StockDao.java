package com.dgut.ssm.dao;

import com.dgut.ssm.bean.Stock;
import com.dgut.ssm.bean.WaitingList;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface StockDao {
    public int addStock(Stock stock);
    public List<Stock> getAllStock();
    //获取未生成发货单的进货单
    public  List<WaitingList> getWaitingList();
    //根据货单id查询数量 商品名
    public Map<String,Object> getNumNameByOgid(Integer ogid);
    //生成没有发货单的进货单
    public Integer newAStock(WaitingList waitingList);
}
