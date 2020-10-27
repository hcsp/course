package com.github.hcsp.course;

import com.github.hcsp.course.dao.UserRepository;
import com.github.hcsp.course.model.Permission;
import com.github.hcsp.course.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.stream.Collectors;

@SpringBootApplication
@Controller
@EnableJpaAuditing
public class CourseApplication {
    @Autowired
    UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(CourseApplication.class, args);
    }

    @GetMapping("/test")
    public String testJpa() {
        userRepository.findAll().forEach(user -> {
            System.out.println("user: " + user.getUsername());
            user.getRoles().forEach(role -> {
                System.out.println("role: " + role.getName());
                System.out.println(
                        role.getPermissions().stream().map(Permission::getName).collect(Collectors.toList()));
            });
        });
        return "OK";
    }
}
