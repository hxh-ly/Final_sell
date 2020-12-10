package com.dgut.ssm.serviceImpl;

import com.dgut.ssm.bean.Contract;
import com.dgut.ssm.dao.ContractDao;
import com.dgut.ssm.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ContractServiceImpl implements ContractService {
    @Autowired
    private ContractDao contractDao;
    public List<Contract> getAllContract() {
        List<Contract> allContract = contractDao.getAllContract();
            return allContract;
    }
}
