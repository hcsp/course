package com.github.hcsp.course.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.hcsp.course.model.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * 全局错误处理。在任何地方抛出的{@link HttpException}都可以在这里被捕获并转换为对应的HTTP status code。
 */
@ControllerAdvice
public class ErrorHandlingController {
    ObjectMapper objectMapper = new ObjectMapper();

    @ExceptionHandler({HttpException.class})
    public void handleError(HttpServletResponse response, HttpException ex) throws Exception {
        response.setStatus(ex.getStatusCode());

        Map<String, Object> jsonObject = new HashMap<>();
        jsonObject.put("message", ex.getMessage());

        response.getOutputStream().write(objectMapper.writeValueAsBytes(jsonObject));
        response.getOutputStream().flush();
    }
}
