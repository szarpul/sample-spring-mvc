package com.intive.samples.spring.mvc.interceptors;


import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GlobalJspInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, final HttpServletResponse response, Object handler) throws Exception {

        InterceptorRegistry registry;

        String redirectURI = request.getParameter("redirect");

        if (redirectURI != null) {
            response.sendRedirect(redirectURI);
            return false;
        }

        return true;
    }
}
