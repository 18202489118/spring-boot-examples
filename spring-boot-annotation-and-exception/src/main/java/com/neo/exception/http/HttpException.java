package com.neo.exception.http;

/**
 * @description:
 * @program: spring-boot-examples
 * @author: Mr.JHzhang
 * @create: 2020-06-28 13:52
 **/
public class HttpException extends RuntimeException {
    protected Integer code;
    protected Integer httpStatusCode = 500;

    public Integer getCode() {
        return code;
    }
}
