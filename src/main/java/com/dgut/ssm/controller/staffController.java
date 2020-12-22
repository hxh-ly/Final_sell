package com.dgut.ssm.controller;

import com.dgut.ssm.bean.Client;
import com.dgut.ssm.bean.Staff;
import com.dgut.ssm.common.APIResult;
import com.dgut.ssm.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("staff")
public class staffController {
    @Autowired
    private StaffService staffService;
    @RequestMapping("toShowList")
    public String toShowStaff(){
        return "/staff/staffList";
    }
    @RequestMapping("showStaff")
    @ResponseBody
    public APIResult showStaff(){
        List<Staff> staffList = staffService.showAllStaff();
       return APIResult.createOk(staffList);
    }
    @RequestMapping("PopStaff")
    public String toShowPopStaff(Model model, @RequestParam(value = "flag") int flag, @RequestParam(value = "id",required = false) Integer id){
        if(flag==1){
            Staff nullStaff = new Staff();
            model.addAttribute("staff",nullStaff);
            return "staff/addStaffForm";
        }

        else {
            List<Staff> staffForQuery = staffService.queryStaffCondition(new Staff(id, null, null));
            model.addAttribute("staffForQuery",staffForQuery.get(0));
            return "staff/updateStaffForm";
        }
    }
    @RequestMapping("addStaff")
    @ResponseBody
    public   APIResult  addShow(@ModelAttribute("staff") Staff staff){
        staffService.insertStaff(staff);
        return APIResult.createNg("OK");
    }
    @RequestMapping("SearchStaff")
    @ResponseBody
    public APIResult SearchStaff(@ModelAttribute("SearchStaff") Staff staff){
        List<Staff> staffList = staffService.queryStaffCondition(staff);
            if(!staffList.isEmpty())
            {
                return APIResult.createOk(staffList);
            }
            else
                return APIResult.createNg("empty");
    }
    @RequestMapping("updateStaff")
    public String updateStaff(@ModelAttribute("staffForQuery") Staff staff){
        Integer i = staffService.updateStaff(staff);
        if(i==1){
            return  "redirect:/staff/showStaff";
        }
        else{
            return "redirect:/staff/updateStaff";
        }
    }
}
