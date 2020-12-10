package com.dgut.ssm.controller;

import com.dgut.ssm.bean.Goods;
import com.dgut.ssm.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("goods")
public class GoodsController {
@Autowired
    private GoodsService goodsService;
    @GetMapping("/goodsList")
    @RolesAllowed({"ROLE_CLIENT","ROLE_WAREHOUSE"})
    public String  getAllGoods(Model model){
        List<Goods> allGoods = goodsService.getAllGoods();
        model.addAttribute("allGoods",allGoods);
        model.addAttribute("addGood",new Goods());
        return "goods/goodsList";

    }

    @GetMapping("/updatePop")
    @RolesAllowed({"ROLE_CLIENT","ROLE_WAREHOUSE"})
    public String  popUpdateGoods(@RequestParam("id") int id,Model model){
                //根据id查询要更改的信息
        Goods goods = goodsService.getGoodsById(id);
        model.addAttribute("goods",goods);
        return "goods/updateGoods";
    }

    @PutMapping("/update")
    @RolesAllowed({"ROLE_CLIENT","ROLE_WAREHOUSE"})
    public String updateGoods(@ModelAttribute Goods goods){
        System.out.println(goods);
            goodsService.updateGoods(goods);
            return "redirect:/goods/goodsList";
    }
    @GetMapping("/delete")
    @RolesAllowed({"ROLE_CLIENT","ROLE_WAREHOUSE"})
    public String delGoods(@RequestParam("id")int id){
          if(goodsService.delGoods(id)==1){
              return "redirect:/goods/goodsList";
          }
            else
                return "goods/updateGoods";
    }
    @PutMapping("/add")
    @RolesAllowed({"ROLE_CLIENT","ROLE_WAREHOUSE"})
    public String addGoods(@ModelAttribute(name = "addGood") Goods goods)
    {
        goodsService.addGoods(goods);
            return "redirect:/goods/goodsList";
    }

    @GetMapping("/NavToQuery")
    @RolesAllowed({"ROLE_CLIENT","ROLE_WAREHOUSE"})
    public String NavToQuery(Model model){
        model.addAttribute("addQuery",new Goods());
        return "/goods/queryGoods";
    }
    @PostMapping("/query")
    @RolesAllowed({"ROLE_CLIENT","ROLE_WAREHOUSE"})
    public String queryGoods(@ModelAttribute(name = "addQuery") Goods goods,Model model){
        System.out.println(goods);
        List<Goods> goodsByCondition = goodsService.getGoodsByCondition(goods);
        model.addAttribute("allGoods",goodsByCondition) ;
        model.addAttribute("addGood",new Goods());
        return "goods/goodsList";
    }
}
