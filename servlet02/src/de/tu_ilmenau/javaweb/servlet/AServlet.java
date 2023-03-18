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
    // init方法在创建的时候只被调用一次，而且程序员没有权限进行调用
    // init方法在执行的时候，AServlet已经被创建出来了

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    public AServlet() {
        System.out.println("AServlet实例化了！");
    }
    // 构造方法也是对象创建的时候只执行一次，这个是由tomcat服务器创建的

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        System.out.println("AServlet's service method execute!");
    }
    // service方法是用户请求一次，程序就执行一次

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {
        System.out.println("AServlet's destroy method execute!");
    }
    // destroy方法是服务器关闭，程序销毁的时候执行的
}
