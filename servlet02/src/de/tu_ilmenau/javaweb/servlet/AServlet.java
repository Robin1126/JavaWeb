package de.tu_ilmenau.javaweb.servlet;

import jakarta.servlet.*;

import java.io.IOException;

/*
    用户发生请求的时候，首先实例化构造方法，然后调用了init方法，然后调用service方法

    AServlet实例化了！
    AServlet's init method execute!
    AServlet's service method execute!

    再去发送请求，只会调用service方法
    因此我们知道servlet对象是单实例的，不是创建多个实例去应对request的 AServlet's service method execute!

    关闭服务器的时候，执行destroy方法，只会在服务器关闭的时候调用一次，销毁对象的内存
 */

public class AServlet implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        System.out.println("AServlet's init method execute!");
    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    public AServlet() {
        System.out.println("AServlet实例化了！");
    }

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        System.out.println("AServlet's service method execute!");
    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("AServlet's destroy method execute!");
    }
}
