package com.dgut.ssm.dao;

import com.dgut.ssm.bean.Client;
import com.dgut.ssm.bean.Staff;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface StaffDao {
    @Select("select id,name,phone from staff where id=#{id}")
    public Staff getStaffById(@Param("id") int id);

}
