package de.tu_ilmenau.javaweb.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Author : Binbin Luo
 * Date : 05.04.2023
 * 采用过滤器filter来解决每个servlet都需要的
 */
public class OrderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // post请求乱码问题
        request.setCharacterEncoding("UTF-8");
        // 响应中文乱码问题
        response.setContentType("text/html;charset=UFT-8");

        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("name") != null) {
            String servletPath = request.getServletPath();
            // ...
        } else {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }
    }
}
