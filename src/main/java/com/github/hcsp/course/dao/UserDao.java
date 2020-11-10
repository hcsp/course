package com.github.hcsp.course.dao;

import com.github.hcsp.course.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserDao extends JpaRepository<User, Integer> {
    // select * from user where status != OK
    // 不是SQL 而是 JPQL
//    @Query("SELECT u FROM User u WHERE u.username = ?1 and u.encryptedPassword = ?2")
    User findUsersByUsername(String username);
}
