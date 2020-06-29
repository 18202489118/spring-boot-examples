package com.neo.exception.http;

/**
 * @description:
 * @program: spring-boot-examples
 * @author: Mr.JHzhang
 * @create: 2020-06-28 13:57
 **/
public class ForbiddenException extends HttpException {

    public ForbiddenException(int code) {
        this.httpStatusCode = 403;
        this.code = code;
    }
}
