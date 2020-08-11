package com.neo.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @description:
 * @program: spring-boot-examples
 * @author: Mr.JHzhang
 * @create: 2020-08-11 14:18
 **/
public class ValidateCodeException extends AuthenticationException {

    public ValidateCodeException(String detail) {
        super(detail);
    }
}
