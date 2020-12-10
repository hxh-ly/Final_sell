package com.dgut.ssm.dao;

import com.dgut.ssm.bean.Client;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ClientDao {
    @Select("select id,name,phone from client where id=#{id}")
    public Client getClientById(@Param("id") int id);
}
