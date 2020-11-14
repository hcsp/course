package com.github.hcsp.course;

import com.fasterxml.jackson.core.type.TypeReference;
import com.github.hcsp.course.model.PageResponse;
import com.github.hcsp.course.model.Role;
import com.github.hcsp.course.model.User;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserIntegrationTest extends AbstractIntegrationTest {
    @Test
    public void get401IfNotLogIn() throws IOException, InterruptedException {
        assertEquals(401, get("/user").statusCode());
        assertEquals(401, get("/user?search=a&pageSize=10&pageNum=1").statusCode());
        assertEquals(401, get("/user/1").statusCode());
    }

    @Test
    public void get403IfNotAdmin() throws IOException, InterruptedException {
        assertEquals(403, get("/user", studentUserCookie).statusCode());
        assertEquals(403, get("/user/1", studentUserCookie).statusCode());
        assertEquals(403, get("/user", teacherUserCookie).statusCode());
        assertEquals(403, get("/user/1", teacherUserCookie).statusCode());
        assertEquals(403, patch("/user/1", "{}", Map.of("Cookie", teacherUserCookie)).statusCode());
        assertEquals(403, patch("/user/1", "{}", Map.of("Cookie", studentUserCookie)).statusCode());
    }

    @Test
    public void adminCanGetAllUsers() throws IOException, InterruptedException {
        String responseBody = get("/user?pageSize=1&pageNum=2&orderBy=id&orderType=asc", adminUserCookie).body();
        PageResponse<User> userOfSecondPage = objectMapper.readValue(responseBody, new TypeReference<>() {
        });

        assertEquals(1, userOfSecondPage.getPageSize());
        assertEquals(2, userOfSecondPage.getPageNum());
        assertEquals(3, userOfSecondPage.getTotalPage());
        assertEquals(1, userOfSecondPage.getData().size());
        assertEquals("Teacher2", userOfSecondPage.getData().get(0).getUsername());
        assertEquals(List.of("老师"), userOfSecondPage.getData().get(0).getRoles().stream().map(Role::getName).collect(toList()));
    }

    @Test
    public void adminCanSearchUser() throws IOException, InterruptedException {
        String responseBody = getAssert200("/user?search=min", adminUserCookie).body();
        PageResponse<User> users = objectMapper.readValue(responseBody, new TypeReference<>() {
        });

        assertEquals(1, users.getPageNum());
        assertEquals(1, users.getTotalPage());
        assertEquals(1, users.getData().size());
        assertEquals("Admin3", users.getData().get(0).getUsername());
        assertEquals(List.of("管理员"), users.getData().get(0).getRoles().stream().map(Role::getName).collect(toList()));
    }

    @Test
    public void adminCanGetOneUser() throws IOException, InterruptedException {
        String responseBody = getAssert200("/user/2", adminUserCookie).body();
        User user = objectMapper.readValue(responseBody, User.class);
        assertEquals("Teacher2", user.getUsername());
        assertEquals(List.of("老师"), user.getRoles().stream().map(Role::getName).collect(toList()));
    }

    @Test
    public void get404IfUserNotExist() throws IOException, InterruptedException {
        assertEquals(404, get("/user/0", adminUserCookie).statusCode());
    }

    @Test
    public void adminCanUpdateUserRole() throws IOException, InterruptedException {
        // 授予用户2 管理员角色
        String responseBody = get("/user/2", adminUserCookie).body();
        User user = objectMapper.readValue(responseBody, User.class);
        Role role = new Role();
        role.setName("管理员");
        user.getRoles().add(role);

        var response = patch("/user/2", objectMapper.writeValueAsString(user), Map.of("Cookie", adminUserCookie));
        assertEquals(200, response.statusCode());
        user = objectMapper.readValue(response.body(), User.class);
        assertEquals("Teacher2", user.getUsername());
        assertEquals(List.of("老师", "管理员"), user.getRoles().stream().map(Role::getName).collect(toList()));

        // 现在用户2也是管理员了
        assertEquals(200, get("/user/1", teacherUserCookie).statusCode());
    }
}
