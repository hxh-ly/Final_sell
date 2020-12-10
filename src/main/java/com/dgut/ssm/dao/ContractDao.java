package com.dgut.ssm.dao;

import com.dgut.ssm.bean.Contract;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ContractDao {
    public List<Contract> getAllContract();

}
