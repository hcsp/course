package com.github.hcsp.course.service;

import com.github.hcsp.course.annotation.Admin;
import com.github.hcsp.course.dao.UserDao;
import com.github.hcsp.course.model.HttpException;
import com.github.hcsp.course.model.PageResponse;
import com.github.hcsp.course.model.Role;
import com.github.hcsp.course.model.RoleDao;
import com.github.hcsp.course.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

@Service
public class UserService {
    @Autowired
    UserDao userDao;
    @Autowired
    RoleDao roleDao;

    private User findById(Integer id) {
        return userDao.findById(id).orElseThrow(() -> new HttpException(404, "用户不存在！"));
    }

    @Admin
    public User updateUser(Integer id, User user) {
        User userInDb = findById(id);

        Map<String, Role> nameToRoles = roleDao.findAll().stream().collect(toMap(Role::getName, x -> x));
        List<Role> newRoles = user.getRoles().stream()
                .map(Role::getName)
                .map(nameToRoles::get)
                .filter(Objects::nonNull)
                .collect(toList());

        userInDb.setRoles(newRoles);
        userDao.save(userInDb);
        return userInDb;
    }

    @Admin
    public PageResponse<User> getAllUsers(String search, Integer pageSize, Integer pageNum, String orderBy, Sort.Direction orderType) {
        Pageable pageable = orderType == null ?
                PageRequest.of(pageNum - 1, pageSize) :
                PageRequest.of(pageNum - 1, pageSize, Sort.by(orderType, orderBy));
        Page<User> page = StringUtils.isEmpty(search) ? userDao.findAll(pageable) : userDao.findBySearch(search, pageable);
        return new PageResponse<>(page.getTotalPages(), pageNum, pageSize, page.toList());
    }

    @Admin
    public User getUser(Integer id) {
        return findById(id);
    }
}
