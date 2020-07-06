package com.neo.filter;

import cn.hutool.core.util.ArrayUtil;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RequestAuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        // 开始进入请求地址拦截
        boolean flag = true;
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        Cookie[] cookies = request.getCookies();
        if (ArrayUtil.isNotEmpty(cookies)) {
            int length = cookies.length;
            for (int i = 0; i < length; i++) {
                Cookie cookie = cookies[i];
                String name = cookie.getName();
                // 可在此处作Token校验
                if ("token".equals(name)) {
                    flag = false;
                } else {
                    continue;
                }
            }
        }

        if (flag) {
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            String fullUrl = request.getContextPath() + "/user/login";
            response.sendRedirect(fullUrl);
        } else {
            chain.doFilter(servletRequest, servletResponse);
        }
    }
}
