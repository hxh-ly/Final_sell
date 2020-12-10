package com.dgut.ssm.controller;

import com.dgut.ssm.bean.User;
import com.dgut.ssm.common.ResponseData;
import com.dgut.ssm.service.UserService;
import com.dgut.ssm.testResult.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.security.RolesAllowed;
import java.io.PrintWriter;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
private UserService userService;

    @GetMapping("/userList")
   /* @Secured()
    @PreAuthorize("hasAnyAuthority('','')")*/
    @RolesAllowed({"ROLE_CLIENT"})
  public String getAllUser(Model model){
      List<User> allUsers=userService.getAllUser();
      model.addAttribute("allUsers",allUsers);
        return "userList";
  }

}
