package com.dgut.ssm.controller;

import com.dgut.ssm.bean.Delivery;
import com.dgut.ssm.bean.Goods;
import com.dgut.ssm.bean.Stock;
import com.dgut.ssm.service.DeliveryService;
import com.dgut.ssm.service.GoodsService;
import com.dgut.ssm.service.StockService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping("/delivery")
public class DeliveryController {
    @Autowired
    DeliveryService deliveryService;
    @Autowired
    GoodsService goodsService;
    @Autowired
    StockService stockService;
    int extra=(int) (Math.round((Math.random() + 1) * 100));
    @RequestMapping("/deliveryList")
    public String  getDeliveryInfo(Model model,@RequestParam(value = "msg",required = false) Integer msg){
        List<Delivery> deliveryInfo = deliveryService.getDeliveryInfo();
                model.addAttribute("deliveryInfo",deliveryInfo);
        model.addAttribute("msg",msg);
        //从index 点击${pageContext.request.contextPath}/delivery/deliveryList
        return "/delivery/deliveryList";
    }
    @RequestMapping("/doDelivery")
    public  String doDelivery(@Param("eid") Integer eid,Model model){
        int i=0;
        try{
             i = deliveryService.updateQuantity(eid);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
            model.addAttribute("msg",i);
            return "redirect:/delivery/deliveryList";
    }
    @RequestMapping("/doStock")
    public  String doStock(@Param("eid") Integer eid,Model model){
        int amount=12;
        int i = deliveryService.addGoodsQuantity(amount,eid);
        model.addAttribute("msg",i);
        return "redirect:/delivery/deliveryList";
    }
    @RequestMapping("/addStock")
    public  String addStock(@Param("eid") Integer eid,Model model){
        //通过eid查询goods 返回商品号 名称
        Goods goods = goodsService.getGoodsInEid(eid);
        //new 一个进货单然后发给页面
        //去eid -23
        Integer StockId = eid-extra;
        Stock stock = new Stock(StockId, goods.getGoodsName(), 0, null, null);
        model.addAttribute("stock",stock);
        model.addAttribute("eid",eid);
        return "/stock/addStock";
    }
    @RequestMapping("goodsStock")
    @ResponseBody
    public String formStock(@ModelAttribute("stock") Stock stock){
        try {
            //加入进货记录里

            System.out.println(stock);
            stockService.addStock(stock);
           deliveryService.addGoodsQuantity(stock.getNum(),stock.getId()+extra);
            return "ok";
        } catch (Exception e) {
            e.printStackTrace();
        }
       return "fail";
    }
}
