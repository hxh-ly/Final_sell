package com.dgut.ssm.dao;

import com.dgut.ssm.bean.Client;
import com.dgut.ssm.bean.Staff;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface StaffDao {
    @Select("select id,name,phone from staff where id=#{id}")
    public Staff getStaffById(@Param("id") int id);
    public List<Staff> showAllStaff();
    public Integer insertStaff(Staff staff);
    public List<Staff> queryStaffCondition(Staff staff);
    public Integer updateStaff(Staff staff);
    @Select("select id,name,phone from staff where phone =#{phone}")
    public Staff GetByPhone(String phone);
}
