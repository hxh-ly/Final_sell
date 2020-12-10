package com.dgut.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/clientOrder")


public class clientOrderController {
    @GetMapping("/clientOrderList")
    public String getAllClientOrder(){
        return "/clientOrder/clientOrderList";
    }
}
