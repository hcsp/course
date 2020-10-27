package com.github.hcsp.course.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.hcsp.course.dao.SessionDao;
import com.github.hcsp.course.model.Session;
import com.github.hcsp.course.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.stream.Stream;

@Configuration
public class Config {

//    @Bean
//    public ObjectMapper objectMapper() {
//        return new ObjectMapper();
//    }

    public static class UserContext {
        private static ThreadLocal<User> currentUser = new ThreadLocal<>();

        // 获取当前线程上下文的用户，null代表没有登录
        public static User getCurrentUser() {
            return currentUser.get();
        }

        // 为当前线程上下文设置用户，null代表清空当前的用户
        public static void setCurrentUser(User currentUser) {
            UserContext.currentUser.set(currentUser);
        }
    }

    public static class UserInterceptor implements HandlerInterceptor {
        public static final String COOKIE_NAME = "COURSE_APP_SESSION_ID";

        @Autowired
        SessionDao sessionDao;

        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            // 从数据库根据cookie取出用户信息，并放到当前的 线程上下文里
            Cookie[] cookies = request.getCookies();

            Stream.of(cookies).filter(cookie -> cookie.getName().equals(COOKIE_NAME))
                    .map(Cookie::getValue)
                    .findFirst()
                    .flatMap(sessionDao::findByCookie)
                    .map(Session::getUser)
                    .ifPresent(UserContext::setCurrentUser);

            return true;
        }

        @Override
        public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
            UserContext.setCurrentUser(null);
        }
    }
}