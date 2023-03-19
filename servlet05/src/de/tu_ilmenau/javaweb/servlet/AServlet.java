package de.tu_ilmenau.javaweb.servlet;

import jakarta.servlet.*;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Author : Binbin Luo
 * Date : 19.03.2023
 */

// 通过测试AServlet和BServlet获取的对象是同一个
    /*
        1. ServletContext是什么？
            ServletContext是接口，是Servlet规范中的一员
        2. ServletContext是谁实现的？
            是Tomcat服务器实现的
        3. ServletContext对象在web服务器启动的的时候创建
            对于一个应用来说，context对象只有一个
            context对象在服务器关闭的时候销毁
        4. ServletContext被称为上下文对象，Servlet对象的环境对象，上下文对象
        5. ServletContext对象对应的就是整个web.xml文件
        50个学生就相当于在一个servlet中，比如空调，语文老师是全部共享的。如果把信息放在web.xml就可以共享信息了
        整个tomcat就是一个容器，里面可以放很多个app，一个app就是一个班级，班级里的学生就是不同的servlet
     */
public class AServlet extends GenericServlet {
    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        // 获取context对象
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        // org.apache.catalina.core.ApplicationContextFacade@48a97538

        ServletContext application = this.getServletContext();
        out.print("Context对象：" + application);
    }
}
