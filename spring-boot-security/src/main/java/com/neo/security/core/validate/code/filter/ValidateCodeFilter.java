package com.neo.security.core.validate.code.filter;

import com.neo.security.controller.ValidateCodeController;
import com.neo.security.core.properties.SecurityProperties;
import com.neo.security.core.validate.code.image.ImageCode;
import com.neo.security.exception.ValidateCodeException;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * @description: 自定义filter
 * @program: spring-boot-examples
 * @author: Mr.JHzhang
 * @create: 2020-08-11 14:12
 **/
@Component
public class ValidateCodeFilter extends OncePerRequestFilter implements InitializingBean {

    @Autowired
    private AuthenticationFailureHandler sAuthenticationFailureHandler;
    @Autowired
    private SecurityProperties securityProperties;

    private SessionStrategy sessionStrategy = new HttpSessionSessionStrategy();
    private Set<String> urls = new HashSet<>();
    private AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public void afterPropertiesSet() throws ServletException {
        super.afterPropertiesSet();
        //其他参数都组装完成之后初始化该url数组
        String[] configUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(
                securityProperties.getCode().getImage().getUrl(), ","
        );
        for (String configUrl : configUrls) {
            urls.add(configUrl);
        }
        urls.add("/authentication/form");
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse rsp, FilterChain filterChain) throws ServletException, IOException {

        boolean action = false;
        for (String url : urls) {
            if (antPathMatcher.match(url, req.getRequestURI())) {
                action = true;
            }
        }

        if (action) {
            //校验验证码
            try {
                validate(new ServletWebRequest(req));
            } catch (ValidateCodeException e) {
                sAuthenticationFailureHandler.onAuthenticationFailure(req, rsp, e);
                return;
            }
        }
        //如果不是登录请求，直接跳过
        filterChain.doFilter(req, rsp);
    }

    private void validate(ServletWebRequest webRequest) {
        ImageCode codeInSession = (ImageCode) sessionStrategy.getAttribute(webRequest, ValidateCodeController.SESSION_KEY);
        String codeInRequest = "";
        try {
            codeInRequest = ServletRequestUtils.getStringParameter(webRequest.getRequest(), "imageCode");
        } catch (ServletRequestBindingException e) {
            e.printStackTrace();
        }
        if (StringUtils.isBlank(codeInRequest)) {
            throw new ValidateCodeException("验证码不能为空");
        }

        if (codeInSession == null) {
            throw new ValidateCodeException("验证码不纯在");
        }
        if (codeInSession.isExpried()) {
            throw new ValidateCodeException("验证码已过期");
        }
        if (!StringUtils.equals(codeInRequest, codeInSession.getCode())) {
            throw new ValidateCodeException("验证码错误");
        }

        sessionStrategy.removeAttribute(webRequest, ValidateCodeController.SESSION_KEY);
    }
}
