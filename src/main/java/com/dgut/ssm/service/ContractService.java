package com.dgut.ssm.service;

import com.dgut.ssm.bean.Client;
import com.dgut.ssm.bean.Contract;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ContractService {
    public List<Contract> getAllContract();
    public Client GetClient(int id);
    public void ChangeStatus(int contractId);
    public Contract getContractById(int contractId);

}
