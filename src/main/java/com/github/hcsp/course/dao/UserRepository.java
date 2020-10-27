package com.github.hcsp.course.dao;

import com.github.hcsp.course.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {
    // select * from user where status != OK
    // 不是SQL 而是 JPQL
    @Query(value = "select * from \"user\" where id <> 2", nativeQuery = true)
    List<User> findUserWhoseIdNotEqual2();

    @Modifying
    @Query(value = "update \"user\" set status = 'DELETED' where id=:id", nativeQuery = true)
    void deleteAllUsers();
}
