package com.dgut.ssm.serviceImpl;

import com.dgut.ssm.bean.Client;
import com.dgut.ssm.bean.Contract;
import com.dgut.ssm.dao.ClientDao;
import com.dgut.ssm.dao.ContractDao;
import com.dgut.ssm.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public void ChangeStatus(int contractId) {
        contractDao.ChangeStatus(contractId);
    }

    public Contract getContractById(int contractId) {
      return   contractDao.getContractById(contractId);
    }
}
