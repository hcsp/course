package com.github.hcsp.course.service;

import com.github.hcsp.course.annotation.PermissionRequired;
import com.github.hcsp.course.model.Course;
import org.springframework.stereotype.Service;

@Service
public class CourseService {
    @PermissionRequired({"管理课程"})
    public Course createCourse(Integer id) {
        return null;
    }

    @PermissionRequired({"管理课程"})
    public Course deleteCourse(Integer id) {
        return null;
    }
    @PermissionRequired({"管理课程"})
    public Course updateCourse(Integer id) {
        return null;
    }
}
