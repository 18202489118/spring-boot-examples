package com.neo.security.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @program: spring-boot-examples
 * @author: Mr.JHzhang
 * @create: 2020-07-10 14:34
 **/
@RestController
public class UserController {

    /**
     * hello
     *
     * @return
     */
    @GetMapping("/hello")
    public String hello() {
        return "hello security";
    }

    @GetMapping("/me")
    public Object getCurrentUser(@AuthenticationPrincipal UserDetails user) {
//        return SecurityContextHolder.getContext().getAuthentication();
//        return authentication;
        return user;
    }
}
