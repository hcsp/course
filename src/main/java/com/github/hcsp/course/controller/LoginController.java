package com.github.hcsp.course.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Controller
public class LoginController {
    private Map<String, String> userPasswords = new ConcurrentHashMap<>();
    //  存储cookie到用户的映射关系
    private Map<String, String> cookieToUsername = new ConcurrentHashMap<>();

    {
        userPasswords.put("zhangsan", "123");
        userPasswords.put("lise", "456");
    }

    public static class UsernameAndPassword {
        private String username;
        private String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }

    @interface OnlyAdmin {

    }

//    @OnlyAdmin
//    @LoginRequired
//    @OnlyRoles(Admin, Teacher)
//    @RequestMapping("/admin/uploadVideo")
//    public void uploadVideo() {
//    }

    // POST请求 /login
    // 1. {"username": xxx, "password": yyy}
    //    content-type: application/json
    // 2. <username>xxx</username>
    //    <password>yyy</password>
    //    content-type: application/ml
    // 3. content-type: application/x-www-form-urlencoded
    //    username: xxx
    //    password: yyy
    @PostMapping("/login")
    @ResponseBody
    public String login(@RequestBody UsernameAndPassword usernameAndPassword, HttpServletResponse response) {
        // 检验用户的登录名和密码对不对
        // 如果对，允许用户登录，并向用户发送一段Cookie
        // 下次用户带Cookie访问的时候就无需登录
        // Cookie和用户信息在服务器端存储的东西就叫 session (会话)
        String username = usernameAndPassword.username;
        String password = usernameAndPassword.password;
        if (password.equals(userPasswords.get(username))) {
            // 登录成功，向用户发送一个Cookie
            String sessionId = UUID.randomUUID().toString();
            String cookieName = "OnlineCourseSessionId";

            response.addCookie(new Cookie(cookieName, sessionId));
            cookieToUsername.put(sessionId, username);
            // SpringMVC 默认把返回的字符串当成View的名字，去查找这个view
            return "Login successfully";
        } else {
            return "Login failed";
        }
    }
}
