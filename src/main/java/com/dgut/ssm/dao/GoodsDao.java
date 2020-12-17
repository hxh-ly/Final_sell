package com.dgut.ssm.dao;

import com.dgut.ssm.bean.Goods;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface GoodsDao {
    public List<Goods> getAllGoods();
    @Select("SELECT id,goods_name goodsName,price,amount from goods where id=#{id}")
    public  Goods getGoodsById(@Param(value = "id") Integer id);
    @Update("update goods set goods_name=#{goodsName},price=#{price},amount=#{amount} where id=#{id}")
    public int updateGoods(Goods goods);
    @Delete("delete from goods where id=#{id}")
    public int deleteGoods(@Param(value = "id") Integer id);
    @Insert("insert into goods (goods_name,price,amount) values(#{goodsName},#{price},#{amount}) ")
    public  int addGoods(Goods goods);
    List<Goods> getGoodsByCondition(Goods goods);
    public List<Goods> getGoodsByContractId(int id);
    public List<Goods> getGoodsByOrderId(int id);

    public Goods getNeedNum(@Param(value = "goodId")int goodId,@Param(value = "orderId")int orderId);

    public int checkIsEid(@Param("gid")int gid, @Param("oid")int oid);
    public Goods getGoodsInEid(Integer eid);
}
