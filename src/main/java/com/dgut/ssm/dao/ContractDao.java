package com.dgut.ssm.dao;

import com.dgut.ssm.bean.Contract;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface ContractDao {
    public List<Contract> getAllContract();
    public void ChangeStatus(@Param("contractId") int contractId,@Param("status") int status);
    @Select("select id,sid,cid,status,signdate from contract where id=#{contractId}")
    public Contract getContractById(@Param("contractId") int contractId);

    public int InsertContract(Contract contract);

    public List<Contract> queryContractCondition(Map map);

    //统计合同下的所有清单货单数量
    public Integer sumAllGoods(Integer cid);
    //修改合同基本信息
    public  Integer updateBaseInfo(Map map);
}
