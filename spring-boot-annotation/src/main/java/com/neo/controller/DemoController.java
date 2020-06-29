package com.neo.controller;

import com.neo.entity.PersonDTO;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @program: spring-boot-examples
 * @author: Mr.JHzhang
 * @create: 2020-06-29 17:05
 **/
@RestController
public class DemoController {

    /**
     * 测试自定义注解
     */
    @PostMapping("/test")
    public String test(@Validated @RequestBody PersonDTO personDTO) {
        return "111";
    }
}
