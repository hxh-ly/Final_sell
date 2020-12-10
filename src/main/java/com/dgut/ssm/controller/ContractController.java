package com.dgut.ssm.controller;

import com.dgut.ssm.bean.Contract;
import com.dgut.ssm.bean.Orders;
import com.dgut.ssm.service.ContractService;
import com.dgut.ssm.service.OrderService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/contract")
public class ContractController {
        @Autowired
        private ContractService contractService;
        @Autowired
        private OrderService orderService;
    @GetMapping("/contractList")
    public String getAllContract(Model model){
        List<Contract> allContract = contractService.getAllContract();
        model.addAttribute("allContract",allContract);
        return "/contract/contractList";
    }
    @GetMapping("/showOrder")
    public String showOrder(@Param("id")Integer id, Model model){
        //查询合同下的清单
        List<Orders> ContainOrder = orderService.getOrderByConId(id);
        model.addAttribute("ContainOrder",ContainOrder);
        return "/contract/showOrder";
    }
}
