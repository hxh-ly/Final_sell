package com.dgut.ssm.controller;

import com.dgut.ssm.bean.Delivery;
import com.dgut.ssm.bean.Goods;
import com.dgut.ssm.bean.Stock;
import com.dgut.ssm.common.APIResult;
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
    int extra = (int) (Math.round((Math.random() + 1) * 100));

    /**
     * 展示待发货列表数据
     *
     * @param model
     * @param msg
     * @return
     */
    @RequestMapping("/deliveryList")
    public String getDeliveryInfo(Model model, @RequestParam(value = "msg", required = false) Integer msg) {
        //获取发货单
        List<Delivery> deliveryInfo = deliveryService.getDeliveryInfo();
        model.addAttribute("deliveryInfo", deliveryInfo);
        model.addAttribute("msg", msg);
        //从index 点击${pageContext.request.contextPath}/delivery/deliveryList
        return "/delivery/deliveryList";
    }

    /**
     * 进行发货
     *
     * @param eid
     * @param model
     * @return
     */
    @RequestMapping("/doDelivery")
    public String doDelivery(@Param("eid") Integer eid, Model model) {
        int i = 0;
        try {
            i = deliveryService.updateQuantity(eid);
            //发货合同状态才为1 通过进货单应该记载合同
            //通过eid找到合同
            Integer cid = receiptService.getCidByEid(eid);
            //改变合同状态（1--正执行  2---履行完毕）
            contractService.ChangeStatus(cid, 1);
            //判断该合同含的发货单的状态是否都为1，1就修改合同状态为2
            List<Integer> allStatus = receiptService.isAllSend(cid);
            //合同货单数量
            Integer total = contractService.sumAllGoods(cid);
            if (allStatus != null && !allStatus.contains(0) && allStatus.size() == total) {
                contractService.ChangeStatus(cid, 2);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        model.addAttribute("msg", i);
        return "redirect:/delivery/deliveryList";
    }

    /**
     * 进货弹框
     *
     * @param eid
     * @param model
     * @return
     */
    @RequestMapping("/addStock")
    public String addStock(@Param("eid") Integer eid, Model model) {
        //通过eid查询goods 返回商品号 名称  进货id藏在货单id里
        Goods goods = goodsService.getGoodsInEid(eid);
        //new 一个进货单然后发给页面
        //去eid -23
        Integer StockId = eid - extra;
        Stock stock = new Stock(StockId, goods.getGoodsName(), 0, null, null);
        model.addAttribute("stock", stock);
        model.addAttribute("eid", eid);
        return "/stock/addStock";
    }

    /**
     * 在销售管理员生成进货单时
     *
     * @param stock
     * @return
     */
    @RequestMapping("goodsStock")
    @ResponseBody
    public APIResult formStock(@ModelAttribute("stock") Stock stock) {
        //提取stockId中的O_gid
        int O_gid = (stock.getId() - 1024) / 10000;
        System.out.println(O_gid + "   -======================================================");
        try {
            //加入进货记录里
            //判断来自销售管理员生成进货单的  还是成功发货单
            if (stock.getId() > 10000) {
                int goodId = goodsService.getGoodIdByName(stock.getGoodsName());
                deliveryService.updateQuantityByGid(stock.getNum(), goodId);
                //修改未成功生成发货单而产生的进货单的状态(已进货)
                deliveryService.updateWaitStock(O_gid);
            } else {
                deliveryService.addGoodsQuantity(stock.getNum(), stock.getId() + extra);
            }
            //添加到进货记录
            stockService.addStock(stock);
            return APIResult.createOKMessage("success stock");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return APIResult.createNg("fail stock");
    }
}
