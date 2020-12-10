package com.dgut.ssm.dao;

import com.dgut.ssm.bean.Role;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface RoleDao {

    public List<Role> findByUid(Integer uid);


    public void save(Role role);


    public List<Role> findAll();
}
