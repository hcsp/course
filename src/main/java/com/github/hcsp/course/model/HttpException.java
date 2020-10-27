package com.github.hcsp.course.model;

/**
 * 任何方法抛出这个异常，意味着HTTP请求应该返回对应的值
 */
public class HttpException extends RuntimeException {
    private int statusCode;
    private String message;

    public HttpException(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
