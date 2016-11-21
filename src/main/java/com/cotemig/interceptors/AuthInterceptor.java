package com.cotemig.interceptors;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Ian Luca on 20/11/2016.
 */
public class AuthInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object controller) throws Exception {

        if (request.getRequestURI().equals("/login")){
            return true;
        }

        if(request.getSession().getAttribute("user") != null) {
            return true;
        }

        response.sendRedirect("/login");
        return false;
    }
}
