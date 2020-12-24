package com.dgut.ssm.controller;

import com.dgut.ssm.common.APIResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/statistics")
public class statisticController {

    @RequestMapping("/goodsType")
    public String showGraphGoods(){

        return "statistics/goodsType";
    }

    //
    @RequestMapping("getGoodsSell")
    public APIResult getGoodsSell(){


        return  APIResult.createNg("");
    }
}
