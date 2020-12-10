package com.dgut.ssm.service;

import com.dgut.ssm.bean.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService extends UserDetailsService {
    public void save(User user);

    public List<User> getAllUser();
}
