package com.neo.security.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @program: spring-boot-examples
 * @author: Mr.JHzhang
 * @create: 2020-07-25 18:14
 **/
@Component
public class MyUserDetailsService implements UserDetailsService {

    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 根据用户名查找用户信息
     *
     * @param s
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        logger.info("登录用户名 username={}", s);
        //todo 根据用户名去数据库查询对应的信息
        String password = passwordEncoder.encode("123456");
        logger.info("数据库密码 password={}",password);

        //该User类是security提供好的，也可以自定义bean implements UserDetails
        return new User(
                s,
                password,
                true,true,true,true,
                //该工具类方法，将字符串转换成需要的对象集合
                AuthorityUtils.commaSeparatedStringToAuthorityList("admin")
        );
    }
}
