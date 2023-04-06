package de.tu_ilmenau.javaweb.bean;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Author : Binbin Luo
 * Date : 05.04.2023
 */
public class Filter implements jakarta.servlet.Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        /**
         * 什么时候不拦截？
         * 用户访问index.jsp的时候不应该拦截，目前拦截/*表示所有请求都拦截
         * 不拦截的情况：
         * 1. 访问index.jsp
         * 2. 用户已经登录
         * 3. 用户要去登录
         * 4. welcome.servlet也不能拦截
         */

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpSession session = request.getSession(false);

        String servletPath = request.getServletPath();

        if ("/index.jsp".equals(servletPath) || "/welcome".equals(servletPath) || "/dept/login".equals(servletPath)
        || "/dept/exit".equals(servletPath)  ||  (session != null && session.getAttribute("user") != null)) {
            // 已经登录成功,放行
         chain.doFilter(request,response);
        } else {
            // 跳转到登录页面
            // 访问根，自动跳转到index页面
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }

    }

    @Override
    public void destroy() {
    }
}
