package de.tu_ilmenau.javaweb.servlet;

import jakarta.servlet.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

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
        6. 所有的Context对象都可以有setAttribute和getAttribute还有removeAttribute
        setAttribute的时候要有名字，
        7. 但是以后要继承的是httpServlet，HttpServlet是GenericServlet的子类
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
        out.print("<br>");

        // 获取上下文初始化信心
        Enumeration<String> initParameterNames = application.getInitParameterNames();
        while (initParameterNames.hasMoreElements()) {
            String name = initParameterNames.nextElement();
            String value = application.getInitParameter(name);
            out.print(name + "=" + value + "<br>");
        }

        // 获取context path 获取应用上下文的根，非常重要
        // 这样就算改动名字也可以使用
        // 使用context对象获取路径, 这个路径是app的根目录
        String contextPath = application.getContextPath();
        out.print(contextPath + "<br>");

        // 获取文件的绝对路径,web就是根，这个是
        String realPath = application.getRealPath("/index.html");
//        String realPath = application.getRealPath("/index.html"); 不加斜杠也可以
        // 路径中带有斜杠/ 表示的是web的根，不加斜杠也是从根下开始找
        out.print(realPath + "<br>");

        // 向ServletContext应用域当中存储数据
        // 需要注意的是，创建的类里面必须要重写toString方法，不然无法输出，out.print是调用对象的toString方法的
        User user = new User("jack", "password");
        application.setAttribute("userObj", user);
        // 取出来
        Object Obj = application.getAttribute("userObj");
        // 输出到浏览器
        out.print(Obj + "<br>") ;
    }
}
