package com.dgut.ssm.service;

import com.dgut.ssm.bean.Stock;

import java.util.List;

public interface StockService {
     public int addStock(Stock stock);
     public List<Stock> getAllStock();
}
