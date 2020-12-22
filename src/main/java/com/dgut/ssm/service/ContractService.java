package com.dgut.ssm.service;

import com.dgut.ssm.bean.Client;
import com.dgut.ssm.bean.Contract;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ContractService {
    public List<Contract> getAllContract();
    public Client GetClient(int id);
    public void ChangeStatus(int contractId,int status);
    public Contract getContractById(int contractId);
    public int InsertContract(Contract contract);
    public List<Contract> queryContractCondition(Map map);
    public Integer sumAllGoods(int cid);
}
