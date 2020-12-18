package com.dgut.ssm.controller;

import com.dgut.ssm.bean.Stock;
import com.dgut.ssm.common.APIResult;
import com.dgut.ssm.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequestMapping("stock")
@Controller
public class StockController {
    //从index点击到controller展示列表
    @Autowired
    private   StockService stockService;
    @RequestMapping("/toShowStockList")
    public String toShowStockList(){
        return "/stock/stockList";
    }


    @ResponseBody
    @RequestMapping("/showStock")
    public APIResult showStockList(){
        List<Stock> allStock = stockService.getAllStock();
        return  APIResult.createOk(allStock);
    }
}
