package com.dgut.ssm.dao;

import com.dgut.ssm.bean.Contract;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
@Mapper
public interface ContractDao {
    public List<Contract> getAllContract();
    public void ChangeStatus(int contractId);
    @Select("select id,sid,cid,status,signdate from contract where id=#{contractId}")
    public Contract getContractById(@Param("contractId") int contractId);
}
