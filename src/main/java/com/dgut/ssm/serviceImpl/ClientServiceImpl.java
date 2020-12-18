package com.dgut.ssm.serviceImpl;

import com.dgut.ssm.bean.Client;
import com.dgut.ssm.dao.ClientDao;
import com.dgut.ssm.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
   private ClientDao clientDao;
    public List<Client> showAllClient() {
        return clientDao.showAllClient();
    }

    public int InsertClient(Client client) {
     return    clientDao.InsertClient(client);
    }

    public List<Client> queryClientCondition(Client client) {
    return   clientDao.queryClientCondition(client);
    }
}
