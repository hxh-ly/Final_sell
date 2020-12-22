package com.dgut.ssm.controller;

import com.dgut.ssm.bean.Delivery;
import com.dgut.ssm.bean.Goods;
import com.dgut.ssm.bean.Stock;
import com.dgut.ssm.service.*;
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
    @Autowired
    ContractService contractService;
    @Autowired
    ReceiptService receiptService;
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
             //发货合同状态才为1 通过进货单应该记载合同
            //通过eid找到合同
            Integer cid = receiptService.getCidByEid(eid);
            contractService.ChangeStatus(cid,1);
            //判断该合同含的发货单的状态是否都为1，1就修改合同状态 //只在发货单里找 no
            List<Integer> allStatus = receiptService.isAllSend(cid);
            //合同货单数量
            Integer total=contractService.sumAllGoods(cid);
        if(allStatus!=null&&!allStatus.contains(0)&&allStatus.size()==total){
                contractService.ChangeStatus(cid,2);
            }
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
