package com.dgut.ssm.dao;

import com.dgut.ssm.bean.Goods;
import com.dgut.ssm.bean.Staff;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface GoodsDao {

    @Select("SELECT id,goods_name goodsName,price,amount from goods where id=#{id}")
    public  Goods getGoodsById(@Param(value = "id") Integer id);

    @Delete("delete from goods where id=#{id}")
    public int deleteGoods(@Param(value = "id") Integer id);


    public List<Goods> getGoodsByContractId(int id);
    public List<Goods> getGoodsByOrderId(int id);

    public Goods getNeedNum(@Param(value = "goodId")int goodId,@Param(value = "orderId")int orderId);

    public int checkIsEid(@Param("gid")int gid, @Param("oid")int oid,@Param("need") int need);
    public Goods getGoodsInEid(Integer eid);
    //-------------------
    public List<Goods> getAllGoods();
    @Insert("insert into goods values(#{id},#{goodsName},#{price},#{amount},#{category}) ")
    public  int addGoods(Goods goods);
    List<Goods> getGoodsByCondition(Goods goods);
    @Update("update goods set goods_name=#{goodsName},price=#{price},amount=#{amount},category=#{category} where id=#{id}")
    public int updateGoods(Goods goods);
    @Delete("delete from goods where id=#{id}")
    public int delGoods(Integer id);
    Integer getGoodIdByName(String name);
}
