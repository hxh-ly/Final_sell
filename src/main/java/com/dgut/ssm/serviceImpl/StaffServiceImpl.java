package com.dgut.ssm.serviceImpl;

import com.dgut.ssm.bean.Client;
import com.dgut.ssm.bean.Staff;
import com.dgut.ssm.dao.StaffDao;
import com.dgut.ssm.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class StaffServiceImpl implements StaffService {
    @Autowired
    private StaffDao staffDao;
    public Staff getStaffById(int id) {
        return null;
    }

    public List<Staff> showAllStaff() {
        return staffDao.showAllStaff();
    }

    public Integer insertStaff(Staff staff) {
        return staffDao.insertStaff(staff);
    }

    public List<Staff> queryStaffCondition(Staff staff) {
        return staffDao.queryStaffCondition(staff);
    }

    public Integer updateStaff(Staff staff) {
        return staffDao.updateStaff(staff);
    }

    public Staff GetByPhone(String phone) {
        return staffDao.GetByPhone(phone);
    }
}
