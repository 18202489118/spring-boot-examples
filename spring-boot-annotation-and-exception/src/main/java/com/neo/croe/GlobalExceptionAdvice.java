package com.neo.croe;

import com.neo.exception.http.HttpException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;
import java.util.List;

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
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public UnifyResponse handleException(HttpServletRequest req, Exception e) {
        UnifyResponse msg = new UnifyResponse(9999, "服务器繁忙", req.getRequestURI());
        return msg;
    }


    /**
     * TODO
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = {HttpException.class})
    @ResponseBody
    public UnifyResponse handleHttpException(HttpServletRequest req, HttpException e) {
        UnifyResponse msg = new UnifyResponse(e.getCode(), e.getMessage(), req.getRequestURI());
        return msg;
    }

    /**
     * Bean字段验证异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public UnifyResponse handleBeanValidationException(HttpServletRequest req, MethodArgumentNotValidException e) {
        String requestUrl = req.getRequestURI();
        String method = req.getMethod();

        List<ObjectError> errors = e.getBindingResult().getAllErrors();

        //拼接错误信息
        StringBuffer errorMsg = new StringBuffer();
        errors.forEach(err -> {
            errorMsg.append(err.getDefaultMessage());
        });

        UnifyResponse msg = new UnifyResponse(10001, errorMsg.toString(), method + "  " + requestUrl);
        return msg;
    }

    /**
     * 方法体内参数校验异常
     *
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(value = {ConstraintViolationException.class})
    @ResponseBody
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public UnifyResponse handleConstraintViolationException(HttpServletRequest req, ConstraintViolationException e) {
        String requestUrl = req.getRequestURI();
        String method = req.getMethod();

        UnifyResponse msg = new UnifyResponse(10001, e.getMessage(), method + "  " + requestUrl);
        return msg;
    }
}
