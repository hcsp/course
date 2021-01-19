package com.github.hcsp.course.dao;

import com.github.hcsp.course.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseDao extends JpaRepository<Course, Integer> {
}
