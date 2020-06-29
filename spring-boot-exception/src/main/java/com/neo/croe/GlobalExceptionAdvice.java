package com.neo.croe;

import com.neo.exception.http.HttpException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * @description: 异常处理
 * @program: spring-boot-examples
 * @author: Mr.JHzhang
 * @create: 2020-06-28 11:34
 **/
@ControllerAdvice
public class GlobalExceptionAdvice {

    /**
     * 未知异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = {Exception.class})
    @ResponseBody
//    @ResponseStatus(HttpStatus.OK)
    public UnifyResponse handleException(HttpServletRequest req, Exception e) {
        UnifyResponse msg = new UnifyResponse(9999, "服务器繁忙", req.getRequestURI());
        return msg;
    }

    /**
     * 已知异常
     *
     * @param req
     * @param e
     */
    @ExceptionHandler(value = {HttpException.class})
    @ResponseBody
    public UnifyResponse handleHttpException(HttpServletRequest req, HttpException e) {
        UnifyResponse msg = new UnifyResponse(e.getCode(), e.getMessage(), req.getRequestURI());
        return msg;
    }
}
