package com.bupt.buptcar.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
    /**
     * 拦截，要求用户登录
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        // 登录检查逻辑
        HttpSession session = request.getSession();
        Object loginUser = session.getAttribute("user");
        if (loginUser != null) {  //放行
            return true;
        }
        // 拦截住。未登录。跳转到登录页
        request.setAttribute("msg", "请先登录");
        request.getRequestDispatcher("/login").forward(request, response);
        return false;
    }
}