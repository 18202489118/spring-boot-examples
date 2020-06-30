package com.neo.controller;

import com.neo.entity.PersonDTO;
import org.hibernate.validator.constraints.Range;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @description:
 * @program: spring-boot-examples
 * @author: Mr.JHzhang
 * @create: 2020-06-29 17:05
 **/
@Validated
@RestController
public class DemoController {

    /**
     * 测试自定义注解
     */
    @PostMapping("/test/{id}")
    public String test(
            @PathVariable @Valid @Range(min = 1, max = 10, message = "不可以超过10")  Integer id,
            @Validated @RequestBody PersonDTO personDTO) {

        return "测试自定义注解";
    }
}
