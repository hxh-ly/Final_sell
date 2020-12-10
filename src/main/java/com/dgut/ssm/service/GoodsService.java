package com.dgut.ssm.service;

import com.dgut.ssm.bean.Goods;

import java.util.List;

public interface GoodsService {
    public List<Goods> getAllGoods();
    public  Goods getGoodsById(Integer id);
    public void updateGoods(Goods goods);
    public  int delGoods(Integer id);
    public  int addGoods(Goods goods);
    List<Goods> getGoodsByCondition(Goods goods);

}
