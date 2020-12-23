package com.dgut.ssm.controller;

import com.dgut.ssm.bean.Receipt;
import com.dgut.ssm.bean.Stock;
import com.dgut.ssm.bean.WaitingList;
import com.dgut.ssm.common.APIResult;
import com.dgut.ssm.service.GoodsService;
import com.dgut.ssm.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;
import java.util.Random;

@RequestMapping("stock")
@Controller
public class StockController {
    //从index点击到controller展示列表
    @Autowired
    private   StockService stockService;
    @Autowired
    private GoodsService goodsService;
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

    @RequestMapping("goWaitToStock")
    public String goWaitToStock(){
        return "stock/waitToStock/waitToStockList";
    }

    @RequestMapping("showWaitToStock")
    @ResponseBody
    public APIResult showWaitToStock(){
        //获取未生成进货的发货单
        List<WaitingList> waitingList = stockService.getWaitingList();
        if(waitingList.isEmpty())
            return  APIResult.createNg("not Fail WaitingList");
        return  APIResult.createOk(waitingList);
    }

    @RequestMapping("ToPopStock")
    public String ToPopStock(@RequestParam("id") Integer id, Model model){
        //这个id是货单id，通过id查询名字和数量
        //发一个 new stock过去
        Map<String, Object> Condition = stockService.getNumNameByOgid(id);
        if(!Condition.isEmpty()){
            String goodsName= (String) Condition.get("goods_name");
            Integer amount = (Integer) Condition.get("amount");
            int waitStockId = (int) (Math.round((Math.random() + 1) * 10000));
            Stock waitToStock = new Stock(waitStockId, goodsName, amount, null, null);
            model.addAttribute("waitToStock",waitToStock);
            return "/stock/waitToStock/addWaitingStock";
        }
        return "";
    }
}
