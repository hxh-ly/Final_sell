package com.dgut.ssm.service;

import com.dgut.ssm.bean.Client;
import com.dgut.ssm.bean.Staff;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StaffService {
    public Staff getStaffById( int id);
    public List<Staff> showAllStaff();
    public Integer insertStaff(Staff staff);
    public List<Staff> queryStaffCondition(Staff staff);
    public Integer updateStaff(Staff staff);
    public Staff GetByPhone(String phone);
}
