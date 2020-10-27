package com.github.hcsp.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyService {
    // 类似于Mybatis的Mapper的东西

    public static class User {
        Integer id;
        String name;
    }
}
