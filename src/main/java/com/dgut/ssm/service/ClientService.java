package com.dgut.ssm.service;

import com.dgut.ssm.bean.Client;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ClientService {
    public List<Client> showAllClient();
    public int InsertClient(Client client);
    public List<Client> queryClientCondition(Client client);
}
