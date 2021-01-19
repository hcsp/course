package com.github.hcsp.course.dao;

import com.github.hcsp.course.model.CourseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourseOrderDao extends JpaRepository<CourseOrder, Integer> {
    Optional<CourseOrder> findByCourseIdAndUserId(Integer courseId, Integer userId);
}
