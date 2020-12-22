package com.dgut.ssm.serviceImpl;

import com.dgut.ssm.bean.Goods;
import com.dgut.ssm.dao.GoodsDao;
import com.dgut.ssm.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GoodsServiceImpl implements GoodsService {
            @Autowired
 private   GoodsDao goodsDao;
    public List<Goods> getAllGoods() {
        return goodsDao.getAllGoods();
    }

    public Goods getGoodsById(Integer id) {
        return  goodsDao.getGoodsById(id);
    }

    public void updateGoods(Goods goods) {
        goodsDao.updateGoods(goods);
    }

    public int delGoods(Integer id) {
      return   goodsDao.deleteGoods(id);

    }

    public int addGoods(Goods goods) {
        return goodsDao.addGoods(goods);
    }

    public List<Goods> getGoodsByCondition(Goods goods) {
       return goodsDao.getGoodsByCondition(goods);

    }

    public int getGoodIdByName(String name) {
        return goodsDao.getGoodIdByName(name);
    }

    public List<Goods> getGoodsByOrderId(Integer id) {
        return  goodsDao.getGoodsByOrderId(id);
    }

    public Goods getNeedNum(Integer goodId,Integer orderId) {

        return goodsDao.getNeedNum(goodId,orderId);
    }

    public int checkIsEid(Integer gid, Integer oid,Integer need) {
        return goodsDao.checkIsEid(gid, oid,need);
    }
    public Goods getGoodsInEid(Integer eid){
      return   goodsDao.getGoodsInEid(eid);
    }
}
