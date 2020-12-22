package com.dgut.ssm.controller;

import com.dgut.ssm.bean.Goods;
import com.dgut.ssm.common.APIResult;
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

    @RequestMapping("toShowList")
    public String toShowGoods(){
        return "/goods/goodsList";
    }

    @RequestMapping("showGoods")
    @ResponseBody
    @RolesAllowed({"ROLE_CLIENT","ROLE_WAREHOUSE"})
    public APIResult showGoods(){
        List<Goods> allGoods = goodsService.getAllGoods();
        return APIResult.createOk(allGoods);
    }
    @RequestMapping("PopGoods")
    public String toShowPopGoods(Model model, @RequestParam(value = "flag") int flag, @RequestParam(value = "id",required = false) Integer id){
        if(flag==1){
           Goods nullGoods = new Goods();
            model.addAttribute("goods",nullGoods);
            return "goods/addGoodsForm";
        }

        else {
            List<Goods> goodsForQuery = goodsService.getGoodsByCondition(new Goods(id,null,null,null,null));
            model.addAttribute("goodsForQuery",goodsForQuery.get(0));
            return "goods/updateGoodsForm";
        }
    }

    @RequestMapping("updatePop")
    @RolesAllowed({"ROLE_CLIENT","ROLE_WAREHOUSE"})
    public String  popUpdateGoods(@RequestParam("id") int id,Model model){
                //根据id查询要更改的信息
        Goods goods = goodsService.getGoodsById(id);
        model.addAttribute("goods",goods);
        return "";
    }

    @PutMapping("addGoods")
    @RolesAllowed({"ROLE_CLIENT","ROLE_WAREHOUSE"})
    public APIResult addGoods(@ModelAttribute(name = "goods") Goods goods)
    {
        goodsService.addGoods(goods);
        return APIResult.createNg("ok");
    }

    @RequestMapping("SearchGoods")
    @ResponseBody
    @RolesAllowed({"ROLE_CLIENT","ROLE_WAREHOUSE"})
    public APIResult SearchGoods(@ModelAttribute(name = "SearchGoods") Goods goods){
        List<Goods> goodsByCondition = goodsService.getGoodsByCondition(goods);
        if(!goodsByCondition.isEmpty())
        {
            return APIResult.createOk(goodsByCondition);
        }
        else
            return APIResult.createNg("empty");
    }

    @RequestMapping("/updateGoods")
    @RolesAllowed({"ROLE_CLIENT","ROLE_WAREHOUSE"})
    public String updateGoods(@ModelAttribute("goodsForQuery") Goods goods){
                goodsService.updateGoods(goods);
        return  "redirect:/goods/showGoods";
    }





    @RequestMapping("deleteGoods")
    @RolesAllowed({"ROLE_CLIENT","ROLE_WAREHOUSE"})
    public String delGoods(@RequestParam("id")int id){
          if(goodsService.delGoods(id)==1){
              return "redirect:/goods/showGoods";
          }
            else
                return "";
    }


    @GetMapping("/NavToQuery")
    @RolesAllowed({"ROLE_CLIENT","ROLE_WAREHOUSE"})
    public String del(Model model){
        model.addAttribute("addQuery",new Goods());
        return "";
    }

}
