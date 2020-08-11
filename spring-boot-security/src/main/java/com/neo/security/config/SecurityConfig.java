package com.neo.security.config;

import com.neo.security.core.properties.SecurityProperties;
import com.neo.security.core.validate.code.filter.ValidateCodeFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * @description:
 * @program: spring-boot-examples
 * @author: Mr.JHzhang
 * @create: 2020-07-24 10:22
 **/
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityProperties securityProperties;
    @Autowired
    private AuthenticationSuccessHandler sAuthenticationSuccessHandler;
    @Autowired
    private AuthenticationFailureHandler sAuthenticationFailureHandler;
    @Autowired
    private ValidateCodeFilter validateCodeFilter;
    @Autowired
    private DataSource dataSource;
    @Autowired
    private UserDetailsService myUserDetailsService;

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        JdbcTokenRepositoryImpl tokenRepository = new JdbcTokenRepositoryImpl();
        tokenRepository.setDataSource(dataSource);
//        tokenRepository.setCreateTableOnStartup(true);
        return tokenRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.addFilterBefore(validateCodeFilter, UsernamePasswordAuthenticationFilter.class)
                .formLogin()
                .loginPage("/authentication/require")
                .loginProcessingUrl("/authentication/form")
                .successHandler(sAuthenticationSuccessHandler)
                .failureHandler(sAuthenticationFailureHandler)
                .and()
                .rememberMe()//记住我
                .tokenRepository(persistentTokenRepository())//处理token
                .tokenValiditySeconds(securityProperties.getBrowser().getRememberMeSeconds())//token有效时间
                .userDetailsService(myUserDetailsService)//登录
                .and()
                .authorizeRequests()//下面都是授权配置
                .antMatchers("/authentication/require", "/error"
                        , securityProperties.getBrowser().getLoginPage(), "/code/image").permitAll()
                .anyRequest()//任何请求
                .authenticated()//都需要身份认证
                .and()
                .csrf().disable();
    }

    /**
     * 错误：There is no PasswordEncoder mapped for the id "null"
     * <p>
     * 原因：于是大概就明白了程序出错的原因： 前端传过来密码后，程序会查找被 花括号"{}"包括起来的id ，以此来确定后面的密码怎么进行加密，而我们在前面并没有按该格式进行处理，这就导致找不到id，就报错了。
     * <p>
     * 明白了报错的原因，就好解决了， 我们只需要对前端传过来的密码进行某种方式加密，就可以了，而官方推荐的是使用bcrypt的加密方式。解决办法如下：
     * <p>
     * 在Securty配置类SecurtyConfig(继承 WebSecurityConfigurerAdapter)中修改 配置即可。
     *
     * @return
     */

//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication() //认证信息存储到内存中
//                .passwordEncoder(passwordEncoder())
//                .withUser("user").password(passwordEncoder().encode("123456")).roles("ADMIN");
//    }

//    private PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }


    /**
     * 同样能解决问题
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
