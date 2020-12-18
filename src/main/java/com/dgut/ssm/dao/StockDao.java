package com.dgut.ssm.dao;

import com.dgut.ssm.bean.Stock;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockDao {
    public int addStock(Stock stock);
    public List<Stock> getAllStock();
}
