package com.dgut.ssm.service;

import com.dgut.ssm.bean.Goods;

import java.util.List;

public interface GoodsService {

    public  Goods getGoodsById(Integer id);

    public  int delGoods(Integer id);


    List<Goods> getGoodsByOrderId(Integer id);
    Goods getNeedNum(Integer goodId,Integer orderId);
    int checkIsEid(Integer gid,Integer oid,Integer need);
    public Goods getGoodsInEid(Integer eid);

    public List<Goods> getAllGoods();
    public void updateGoods(Goods goods);
    public  int addGoods(Goods goods);
    List<Goods> getGoodsByCondition(Goods goods);
    int getGoodIdByName(String name);
}
