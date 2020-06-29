package com.neo.controller;

import com.neo.exception.http.HttpException;
import com.neo.exception.http.NotFoundException;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description: 异常控制器
 * @program: spring-boot-examples
 * @author: Mr.JHzhang
 * @create: 2020-06-28 09:56
 **/
@RestController
public class ExceptionController {

    @GetMapping("/info")
    public String info(@RequestParam(required = false) Integer id) {

        if (ObjectUtils.isEmpty(id)) {
            throw new NotFoundException(10001);
        }
        return "info";
    }
}
