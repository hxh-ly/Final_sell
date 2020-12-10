package com.dgut.ssm.serviceImpl;

import com.dgut.ssm.bean.Role;
import com.dgut.ssm.bean.User;
import com.dgut.ssm.dao.UserDao;
import com.dgut.ssm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao dao;

    /**
     * @param user
     */
    public void save(User user) {

    }

    public List<User> getAllUser() {
         return    dao.getAllUser();
    }

    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        try {
    User user = dao.getUserByName(s);
    if (user == null) {
        return null;
    }
    List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
    /*动态添加权限*/
            List<Role> roles = user.getRoles();
            for (Role role : roles) {
                authorities.add(new SimpleGrantedAuthority(role.getRoleName()));
            }

    UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUsername(), "{noop}" + user.getPassword(), authorities);
    return userDetails;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
