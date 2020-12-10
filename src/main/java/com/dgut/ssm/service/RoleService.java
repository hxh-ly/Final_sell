package com.dgut.ssm.service;

import com.dgut.ssm.bean.Role;

import java.util.List;

public interface RoleService {
    public void save(Role role);

    public List<Role> findAll();
}
