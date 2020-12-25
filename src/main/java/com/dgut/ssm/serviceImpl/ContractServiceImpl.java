package com.dgut.ssm.serviceImpl;

import com.dgut.ssm.bean.Client;
import com.dgut.ssm.bean.Contract;
import com.dgut.ssm.dao.ClientDao;
import com.dgut.ssm.dao.ContractDao;
import com.dgut.ssm.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
public class ContractServiceImpl implements ContractService {
    @Autowired
    private ContractDao contractDao;
    @Autowired
    private ClientDao clientDao;
    public List<Contract> getAllContract() {
        List<Contract> allContract = contractDao.getAllContract();
            return allContract;
    }
    public Client GetClient(int id){
        return clientDao.getClientById(id);
    }

    public void ChangeStatus(int contractId,int status) {
        contractDao.ChangeStatus(contractId,status);
    }

    public Contract getContractById(int contractId) {
      return   contractDao.getContractById(contractId);
    }

    @Transactional
    public int InsertContract(Contract contract) {
        Integer i=0;
        try {
            i=contractDao.InsertContract(contract);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  i;
    }

    public List<Contract> queryContractCondition(Map map) {
        return  contractDao.queryContractCondition(map);
    }

    public Integer sumAllGoods(int cid) {
       return contractDao.sumAllGoods(cid);
    }

    public Integer updateBaseInfo(Map map) {
        return contractDao.updateBaseInfo(map);
    }
}
