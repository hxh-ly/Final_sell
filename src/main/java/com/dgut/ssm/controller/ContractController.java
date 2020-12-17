package com.dgut.ssm.controller;

import com.dgut.ssm.bean.*;
import com.dgut.ssm.service.ContractService;
import com.dgut.ssm.service.GoodsService;
import com.dgut.ssm.service.OrderService;
import com.dgut.ssm.service.ReceiptService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/contract")
public class ContractController {
        @Autowired
        private ContractService contractService;
        @Autowired
        private OrderService orderService;
        @Autowired
        private GoodsService goodsService;
        @Autowired
        private ReceiptService receiptService;
    @GetMapping("/contractList")
    public String getAllContract(Model model,@Param("msg") Integer msg){

        List<Contract> allContract = contractService.getAllContract();
        model.addAttribute("allContract",allContract);
        model.addAttribute("msg",msg);
        return "/contract/contractList";
    }
    @GetMapping("/showOrder")
    public String showOrder(@Param("cid")Integer cid, Model model){
        //查询合同下的清单   哪个订单对应哪些商品
        Integer contractId=cid;
        System.out.println(contractId);
        List<Orders> ContainOrder = orderService.getOrderByConId(contractId);
        //通过good_order 表 获取
        System.out.println(ContainOrder);
        model.addAttribute("ContainOrder",ContainOrder);
        model.addAttribute("contractId",contractId);
        return "/contract/showOrder";
    }
    @GetMapping("showGoods")
    public  String showGoods(@Param("orderId")Integer orderId,Model model,@Param("contractId")Integer contractId,@Param("msg") Integer msg){
        //通过订单id查出goods
        List<Goods> goodsByOrderId = goodsService.getGoodsByOrderId(orderId);
        Contract contractById = contractService.getContractById(contractId);
        model.addAttribute("GoodsInOrder",goodsByOrderId);
        model.addAttribute("back",contractId);
        model.addAttribute("contract",contractById);
        model.addAttribute("orderId",orderId);
        model.addAttribute("msg",msg);
        return  "/contract/goodsIn";
    }
    @GetMapping("/newReceipt")
    public  String  newReceipt(@Param("goodId")Integer goodId, @Param("orderId") Integer orderId, @Param("contractId") Integer contractId, Model model) throws IOException {
        //如果库存够 1.增加一张发货单，
        //          2.将合同状态修改 同时（清单，合同）不可修改
        //          3.修改仓库管理员的发货单有记录,能发货
        //如果库存不够<=0, 仓库那边就没有发货单记录
        // 1.增加一个进货单（发货数量+30） 仓库那边可以去发货
        // 2.
        //如果发货后库存为0，仓库管理员就去进货。

        //ps:有一个事务 一个订单的全部商品全都能发出去 才有发货单
        //one:根据goodsid判断添加一个发货单
        model.addAttribute("orderId",orderId);
        model.addAttribute("contractId",contractId);
        Goods goodsById = goodsService.getGoodsById(goodId);
        //总数
        int total=goodsById.getAmount();
        //订单需要的数量
        Goods needNum = goodsService.getNeedNum(goodId,orderId);
        int need=needNum.getAmount();
        System.out.println(total+" "+need);
        if(need<=total){
            if(goodsService.checkIsEid(goodId,orderId)!=0) {//如果发货单存在
                //1.通过有orderId 和 goodId 查找合同里的客户地址
                //2.生成发货单(订单，状态，客户地址)
                //提示失败
                Integer msg=0;
                model.addAttribute("msg",msg);
            }
            else {
                Integer cid = orderService.getOrder(orderId);
                Client client = contractService.GetClient(cid);
                System.out.println(client);
                String location = client.getLocation();
                Integer expressId = (int) (Math.round((Math.random() + 1) * 1000));
                Receipt receipt = new Receipt(expressId, 0, location, null);
                //添加发货单
                receiptService.addReceipt(receipt);
                //录到中间表里
                receiptService.addInMerge(receipt.getExpressId(), orderId, goodId);
                //改合同状态
                contractService.ChangeStatus(cid);
                //不允许修改（2个）
                Integer msg=1;
                model.addAttribute("msg",msg);
            }

        }
        else{
            //回到页面去生成进货单
            Integer msg=2;
            model.addAttribute("msg",msg);

        }

        return "redirect:/contract/showGoods";
    }
}

