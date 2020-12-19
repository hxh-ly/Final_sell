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
import org.springframework.web.bind.annotation.RequestParam;
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
    public String toShowPopClient(Model model,@RequestParam(value = "flag") int flag,@RequestParam(value = "id",required = false) Integer id){


        if(flag==1){
            Client nullClient = new Client();
            model.addAttribute("client",nullClient);
            return "client/addClientForm";
        }

        else {
            List<Client> clientsForQuery = clientService.queryClientCondition(new Client(id, null, null, null));
            model.addAttribute("clientsForQuery",clientsForQuery.get(0));
            return "client/updateClientForm";
        }
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

    @RequestMapping("updateClient")
    public String updateClient(@ModelAttribute("clientsForQuery") Client client){
        Integer i = clientService.updateClient(client);
            if(i==1){
                return  "redirect:/client/showClient";
            }
            else{
                return "redirect:/client/updateClient";
            }
    }
}
