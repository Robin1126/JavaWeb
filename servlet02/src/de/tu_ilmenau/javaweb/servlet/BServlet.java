package de.tu_ilmenau.javaweb.servlet;

import jakarta.servlet.*;

import java.io.IOException;

public class BServlet implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    public BServlet() {
        System.out.println("BServlet实例化了！");
    }

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {

    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
