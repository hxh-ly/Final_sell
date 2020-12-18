package com.dgut.ssm.controller;

import com.dgut.ssm.bean.Client;
import com.dgut.ssm.common.APIResult;
import com.dgut.ssm.service.ClientService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("client")
public class clientController {
    @Autowired
    private ClientService clientService;

    @RequestMapping("toShowList")
    public String toShowClient(){
        return "/client/clientList";
    }
    @RequestMapping("showClient")
    @ResponseBody
    public APIResult showClient(){
        List<Client> clients = clientService.showAllClient();
        return APIResult.createOk(clients);
    }
    @RequestMapping("PopClient")
    public String toShowPopClient(Model model,@Param("flag") int flag){
        Client nullClient = new Client();
        model.addAttribute("client",nullClient);
        if(flag==1)
        return "client/addClientForm";
        else
            return "client/updateClientForm";
    }
    @RequestMapping("addClient")
    @ResponseBody
    public   APIResult  addShow(@ModelAttribute("client") Client client){
        clientService.InsertClient(client);
        return APIResult.createNg("OK");
    }

    @RequestMapping("SearchClient")
    @ResponseBody
    public APIResult SearchClient(@ModelAttribute("SearchClient") Client client){
        List<Client> clients = clientService.queryClientCondition(client);
        if(!clients.isEmpty())
        return APIResult.createOk(clients);
        else
            return APIResult.createNg("empty");
    }
}
