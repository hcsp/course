package com.github.hcsp.course;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.ClassicConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

import static com.github.hcsp.course.configuration.UserInterceptor.COOKIE_NAME;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = CourseApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {"spring.config.location=classpath:test.properties"})
public class AbstractIntegrationTest {
    static final String studentUserCookie = COOKIE_NAME + "=student_user_cookie";
    static final String teacherUserCookie = COOKIE_NAME + "=teacher_user_cookie";
    static final String adminUserCookie = COOKIE_NAME + "=admin_user_cookie";
    @Autowired
    Environment environment;
    @Value("${spring.datasource.url}")
    String databaseUrl;
    @Value("${spring.datasource.username}")
    String databaseUsername;
    @Value("${spring.datasource.password}")
    String databasePassword;

    ObjectMapper objectMapper = new ObjectMapper();

    HttpClient client = HttpClient.newHttpClient();

    String getPort() {
        return environment.getProperty("local.server.port");
    }

    @BeforeEach
    public void resetDatabase() {
        ClassicConfiguration conf = new ClassicConfiguration();
        conf.setDataSource(databaseUrl, databaseUsername, databasePassword);
        Flyway flyway = new Flyway(conf);
        flyway.clean();
        flyway.migrate();
    }

    HttpResponse<String> post(String path, // path是/user /session 这样的路径
                              String accept,
                              String contentType,
                              String body) throws IOException, InterruptedException {
        return post(path, body, Map.of("Accept", accept, "Content-Type", contentType));
    }

    HttpResponse<String> patch(String path, // path是/user /session 这样的路径
                               String body,
                               Map<String, String> headers) throws IOException, InterruptedException {
        return request("PATCH", path, body, headers);
    }

    HttpResponse<String> request(
            String method,
            String path, // path是/user /session 这样的路径
            String body,
            Map<String, String> headers) throws IOException, InterruptedException {
        var builder = HttpRequest.newBuilder(URI.create("http://localhost:" + getPort() + "/api/v1" + path))
                .method(method, HttpRequest.BodyPublishers.ofString(body));

        headers.forEach(builder::header);
        if (!headers.containsKey("Content-Type")) {
            builder.header("Content-Type", APPLICATION_JSON_VALUE);
        }
        if (!headers.containsKey("Accept")) {
            builder.header("Accept", APPLICATION_JSON_VALUE);
        }
        return client.send(builder.build(), HttpResponse.BodyHandlers.ofString());
    }

    HttpResponse<String> post(String path, // path是/user /session 这样的路径
                              String body,
                              Map<String, String> headers) throws IOException, InterruptedException {
        return request("POST", path, body, headers);
    }

    HttpResponse<String> get(String path) throws IOException, InterruptedException {
        return get(path, Map.of());
    }

    HttpResponse<String> get(String path, String cookie) throws IOException, InterruptedException {
        return get(path, Map.of("Cookie", cookie));
    }

    HttpResponse<String> getAssert200(String path, String cookie) throws IOException, InterruptedException {
        var ret = get(path, Map.of("Cookie", cookie));
        Assertions.assertEquals(200, ret.statusCode());
        return ret;
    }

    HttpResponse<String> get(String path, Map<String, String> headers) throws IOException, InterruptedException {
        var builder = HttpRequest.newBuilder(URI.create("http://localhost:" + getPort() + "/api/v1" + path));
        headers.forEach(builder::header);
        if (!headers.containsKey("Accept")) {
            builder.header("Accept", APPLICATION_JSON_VALUE);
        }
        return client.send(builder.build(), HttpResponse.BodyHandlers.ofString());
    }

    HttpResponse<String> delete(String path, String cookie) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .header("Accept", APPLICATION_JSON_VALUE)
                .header("Cookie", cookie)
                .uri(URI.create("http://localhost:" + getPort() + "/api/v1" + path))
                .DELETE()
                .build();
        return client.send(request, HttpResponse.BodyHandlers.ofString());
    }

}
