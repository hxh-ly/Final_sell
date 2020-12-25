package com.dgut.ssm.controller;

import com.dgut.ssm.common.APIResult;
import com.dgut.ssm.service.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/statistics")
public class statisticController {
        @Autowired
    StatisticService statisticService;
    @RequestMapping("/goodsType")
    public String showGraphGoods(){

        return "statistics/goodsType";
    }

    //
    @RequestMapping("getGoodsSell")
    @ResponseBody
    public APIResult getGoodsSell(){
         /*x轴 y轴 */
        List<String> xGoodsType = statisticService.GetXGoodsType();
        List<Integer> yGoodsType = statisticService.getYGoodsType();
        Map<String,Object> map= new HashMap<String,Object>();
        map.put("xGoodsType",xGoodsType);
        map.put("yGoodsType",yGoodsType);
        System.out.println(map.toString());
        return  APIResult.createOk(map);
    }
}
