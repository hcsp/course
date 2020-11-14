package com.github.hcsp.course;

import com.github.hcsp.course.model.Session;
import com.github.hcsp.course.model.User;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.http.MediaType.APPLICATION_FORM_URLENCODED_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class AuthIntegrationTest extends AbstractIntegrationTest {
    @Test
    public void registerLoginLogout() throws IOException, InterruptedException {
        // 注册用户
        // username=aaa&password=bbb
        String usernameAndPassword = "username=zhangsan&password=123456";
        HttpResponse<String> response = post("/user", APPLICATION_JSON_VALUE, APPLICATION_FORM_URLENCODED_VALUE, usernameAndPassword);
        User responseUser = objectMapper.readValue(response.body(), User.class);

        assertEquals(201, response.statusCode());
        assertEquals("zhangsan", responseUser.getUsername());
        assertNull(responseUser.getEncryptedPassword());

        // 用该用户进行登录
        response = post("/session", APPLICATION_JSON_VALUE, APPLICATION_FORM_URLENCODED_VALUE, "username=zhangsan&password=123456");
        System.out.println(response.statusCode());
        System.out.println(response.body());
        responseUser = objectMapper.readValue(response.body(), User.class);
        // 现在在HTTP响应里应该有一个Set-Cookie，作为下次发起请求的凭证

        String cookie = response.headers()
                .firstValue("Set-Cookie")
                .get();

        assertNotNull(cookie);
        assertEquals(200, response.statusCode());
        assertEquals("zhangsan", responseUser.getUsername());
        assertNull(responseUser.getEncryptedPassword());

        // 确定该用户已经登录成功

        response = get("/session", cookie);
        assertEquals(200, response.statusCode());
        Session session = objectMapper.readValue(response.body(), Session.class);
        assertEquals("zhangsan", session.getUser().getUsername());
        // 调用注销接口
        response = delete("/session", cookie);
        assertEquals(204, response.statusCode());

        // 再次尝试访问用户的登录状态
        // 确定该用户已经登出
        response = get("/session", cookie);
        assertEquals(401, response.statusCode());
    }

    @Test
    public void getErrorIfUsernameAlreadyRegistered() throws IOException, InterruptedException {
        // 注册用户
        // 注册用户
        String usernameAndPassword = "username=zhangsan&password=123456";
        HttpResponse<String> response = post("/user", APPLICATION_JSON_VALUE, APPLICATION_FORM_URLENCODED_VALUE, usernameAndPassword);
        assertEquals(201, response.statusCode());
        // 成功
        // 再次使用同名用户注册
        // 失败
        response = post("/user", APPLICATION_JSON_VALUE, APPLICATION_FORM_URLENCODED_VALUE, usernameAndPassword);
        assertEquals(409, response.statusCode());
    }
}
