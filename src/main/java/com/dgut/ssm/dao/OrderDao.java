package com.dgut.ssm.dao;

import com.dgut.ssm.bean.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface OrderDao {
    public List<Orders> getOrderByContractId(int id);
    @Select("select contractId from orders where id=#{orderId}")
    public  Integer getOrder(@Param("orderId")int id);

    public  Integer addOrder(Integer cid);
    public Integer insertGoods(@Param("gid") Integer gid,@Param("oid")Integer oid,@Param("amount")Integer amount);
}
