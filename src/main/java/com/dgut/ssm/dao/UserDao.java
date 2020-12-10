package com.dgut.ssm.dao;


import com.dgut.ssm.bean.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    User getUserByName(String username);
    List<User> getAllUser();
}
