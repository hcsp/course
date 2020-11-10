package com.github.hcsp.course.annotation;

import com.github.hcsp.course.dao.UserDao;
import com.github.hcsp.course.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 管理用户的角色，要求当前登录的用户必须是管理员
 */
@Service
public class UserRoleManagerService {
    @Autowired
    UserDao userDao;

    @Admin
    public List<User> getAllUsers() {
        return userDao.findAll();
    }
}
