package com.dgut.ssm.serviceImpl;

import com.dgut.ssm.bean.Role;
import com.dgut.ssm.dao.RoleDao;
import com.dgut.ssm.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    public void save(Role role) {

    }

    public List<Role> findAll() {
        return null;
    }
  /*  @Autowired
    private RoleDao roleDao;
    public void save(Role role) {
    roleDao.save(role);
    }

    public List<Role> findAll() {
        return roleDao.findAll();
    }*/
}
