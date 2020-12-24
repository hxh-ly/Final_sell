package com.dgut.ssm.controller;

import com.dgut.ssm.bean.*;
import com.dgut.ssm.common.APIResult;
import com.dgut.ssm.service.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/contract")
public class ContractController {
    @Autowired
    private ClientService clientService;
    @Autowired
    private StaffService staffService;
    @Autowired
    private ContractService contractService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private GoodsService goodsService;
    @Autowired
    private ReceiptService receiptService;
    @Autowired
    private StockService stockService;

    @GetMapping("/toContractList")
    public String toContractList() {
        return "contract/contractssList";
    }

    @GetMapping("/contractList")
    @ResponseBody
    public APIResult getAllContract(Model model, @RequestParam(value = "msg", required = false) Integer msg) {

        List<Contract> allContract = contractService.getAllContract();
          /* model.addAttribute("allContract", allContract);
           model.addAttribute("msg", msg);*/
        return APIResult.createOk(allContract);
    }

    @GetMapping("ToaddContract")
    public String ToaddContract(Model model) {
        int contractId = (int) (Math.round((Math.random() + 1) * 10000));
        Contract nullContact = new Contract(contractId, null, 0, null, null, null);
        //model.addAttribute("contract",nullContact);
        return "contract/addContract";
    }

    @RequestMapping("addContract")
    @RolesAllowed({"ROLE_CLIENT", "ROLE_CONTRACT"})
    @ResponseBody
    public APIResult addContract(@RequestBody Contract contract) {
        //根据号码录入
        String cPhone = contract.getClient().getPhone();
        String sPhone = contract.getStaff().getPhone();
        Client client = clientService.GetByPhone(cPhone);
        Staff staff = staffService.GetByPhone(sPhone);
        if (client != null && staff != null) {
            contract.setStaff(staff);
            contract.setClient(client);
            contractService.InsertContract(contract);
            return APIResult.createOKMessage("success to add contract");
        } else return APIResult.createNg("fail to add contract");
    }

    @RequestMapping("queryContract")
    @ResponseBody
    public APIResult queryContract(@RequestBody Map map, Model model) {
        //从json获取到的 给到map
        System.out.println(map.toString());

        List<Contract> contracts = contractService.queryContractCondition(map);
        model.addAttribute("allContract", contracts);
        model.addAttribute("msg", 4);
        return APIResult.createOk(contracts);
    }

    @GetMapping("PopContract")
    public String PopContract(Model model, @RequestParam(value = "cid") Integer cid) {
        Contract contract = contractService.getContractById(cid);

        model.addAttribute("contract", contract);
        return "contract/updateContractForm";
    }

    @GetMapping("/showOrder")
    public String showOrder(@Param("cid") Integer cid, Model model) {
        //合同id
        Integer contractId = cid;
        System.out.println(contractId);
        //
        Contract contract = contractService.getContractById(contractId);

        //合同id对应的清单们
        List<Orders> ContainOrder = orderService.getOrderByConId(contractId);
        //通过good_order 表 获取
        System.out.println(ContainOrder);
        //发送给页面的清单
        model.addAttribute("ContainOrder", ContainOrder);
        //发送给页面的合同id
        model.addAttribute("contractId", contractId);
        model.addAttribute("contract", contract);
        return "/contract/showOrder";
    }

    @RequestMapping("addOrder")
    @ResponseBody
    public APIResult addOrder(@RequestParam("cid") Integer cid) {
        //添加合同
        if (orderService.addOrder(cid) == 1)
            return APIResult.createOKMessage("success");
        else return APIResult.createNg("fail");
    }

    @GetMapping("showGoods")
    public String showGoods(@Param("orderId") Integer orderId, Model model, @Param("contractId") Integer contractId, @Param("msg") Integer msg) {
        //通过订单id查出goods
        List<Goods> goodsByOrderId = goodsService.getGoodsByOrderId(orderId);
        //查出合同
        Contract contract = contractService.getContractById(contractId);
        //订单下的goods们
        model.addAttribute("GoodsInOrder", goodsByOrderId);
        //返回
        model.addAttribute("back", contractId);
        //合同
        model.addAttribute("contract", contract);
        //订单id
        model.addAttribute("orderId", orderId);
        //消息
        model.addAttribute("msg", msg);
        return "/contract/goodsIn";
    }

    @GetMapping("PopGoodsAdd")
    public String PopGoodsAdd(@RequestParam("oid") Integer oid, Model model) {
        model.addAttribute("oid", oid);
        return "/order/addGoodsInOrder";
    }

    @RequestMapping("GoodsAddInO")
    @ResponseBody
    public APIResult GoodsAdd(@RequestBody Map<String, Object> oidNeed) {
        System.out.println(oidNeed.toString());

        String goodsName = (String) oidNeed.get("goodsName");
        int oid = (Integer) oidNeed.get("oid");
        int amount = (Integer) oidNeed.get("amount");
        //通过货名找goodsId
        int gid = goodsService.getGoodIdByName(goodsName);
        //值存在能添加
        if (gid != 0 && oid != 0 && amount != 0 && orderService.insertGoods(gid, oid, amount) == 1)
        // 插入中间表gom(gid,oid,数量)
        {
            return APIResult.createOKMessage("OK");

        } else
            return APIResult.createNg("Fail");
    }

    @GetMapping("/newReceipt")
    public String newReceipt(@Param("goodsName") String goodsName, @Param("orderId") Integer orderId, @Param("contractId") Integer contractId,
                             @RequestParam("need") Integer needNum, Model model) throws IOException {

        //如果库存够 1.增加一张发货单，
        //          2.将合同状态修改 同时（清单，合同）不可修改
        //          3.修改仓库管理员的发货单有记录,能发货
        //如果库存不够<=0, 仓库那边就没有发货单记录
        // 1.增加一个进货单（发货数量+30） 仓库那边可以去发货
        // 2.
        //如果发货后库存为0，仓库管理员就去进货。

        //ps:有一个事务 一个订单的全部商品全都能发出去 才有发货单
        //one:根据goodsid判断添加一个发货单
        model.addAttribute("orderId", orderId);
        model.addAttribute("contractId", contractId);
        int goodId = goodsService.getGoodIdByName(goodsName);
        Goods goodsById = goodsService.getGoodsById(goodId);
        //总数
        int total = goodsById.getAmount();
        //订单需要的数量
        //Goods needNum = goodsService.getNeedNum(goodId, orderId);
        //int need = needNum.getAmount();
        int need = needNum;
        if (need <= total) {
            if (goodsService.checkIsEid(goodId, orderId, need) != 0) {//如果发货单存在
                //1.通过有orderId 和 goodId 查找合同里的客户地址
                //2.生成发货单(订单，状态，客户地址)
                //提示失败
                Integer msg = 0;
                model.addAttribute("msg", msg);
            } else {
                Integer cid = orderService.getOrder(orderId);
                Client client = contractService.GetClient(cid);
                String location = client.getLocation();
                Integer expressId = (int) (Math.round((Math.random() + 1) * 1000));
                Receipt receipt = new Receipt(expressId, 0, location, null, contractId);
                //添加发货单 发货单里有一个合同id？
                receiptService.addReceipt(receipt);
                //录到中间表里
                receiptService.addInMerge(receipt.getExpressId(), orderId, goodId, need);

                //判断现在所有货单是否发货完 完合同状态就是2了

                //不允许修改（2个）
                Integer msg = 1;
                model.addAttribute("msg", msg);
            }

        } else {
            //回到页面去生成进货单
            Integer msg = 2;
            model.addAttribute("msg", msg);

        }

        return "redirect:/contract/showGoods";
    }

    @RequestMapping("PopUpdateOgoods")
    public String PopUpdateOgoods(@RequestParam("O_gid") Integer O_gid, Model model) {
        model.addAttribute("O_gid", O_gid);
        return "/contract/updateContractForm";
    }

    @RequestMapping("/updateOGoods")
    @ResponseBody
    public APIResult updateOGoods(@RequestBody Map map) {
        //得到数据
        Integer o_gid = (Integer) map.get("oid");
        Integer amount = (Integer) map.get("amount");
        //通过O_gid在gom表修改数量
        if (receiptService.UpdateAmountMid(o_gid, amount) == 1)
            return APIResult.createOKMessage("success to update goods In orders");
        else return APIResult.createNg("fail to update goods In orders");
    }

    @RequestMapping("/newAStock")
    @ResponseBody
    public APIResult newAStock(@RequestParam("O_gid") Integer O_gid) {
        //在less_to_stock表插入数据，
        //通过O_gid 在中间表查
        System.out.println(O_gid+"            !!OID!!           ");
        Map<String, Object> MapHasId = stockService.getNumNameByOgid(O_gid);
        String goods_name = (String) MapHasId.get("goods_name");
        Integer amount = (Integer) MapHasId.get("amount");
        System.out.println(MapHasId.toString()+"                                     ------                    ");
        WaitingList waitingList = new WaitingList(null, goods_name,  amount,O_gid, false);
        if (stockService.newAStock(waitingList) == 1) {
            return APIResult.createOKMessage("success to make a stockOrder");
        } else
            return APIResult.createNg("fail to make a stockOrder");
    }
}

