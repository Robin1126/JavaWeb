package de.tu_ilmenau.javaweb.servlet;

import jakarta.servlet.*;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Author : Binbin Luo
 * Date : 19.03.2023
 */
public class BServlet extends GenericServlet {
    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        ServletContext application = this.getServletContext();
        out.print("Context对象：" + application);

        // 通过servletContext对象也是可以记录日志的
        // 这个日志会放在 CATALINA_HOME/logs下面

        // IDEA生成的是Tomcat的副本，并不是真正的Tomcat
        // 在idea中可以找到，是local.host的方法
//        "C:\Users\luobi\AppData\Local\JetBrains\IntelliJIdea2022.3\tomcat\31efaf05-73c8-4c5b-98c7-ef063513160e\logs\localhost.2023-03-20.log"
        application.log("大家好，欢迎大家一起学习servlet");

        // Tomcat服务器的logs下面有什么文件
        // catalina.xxx-xxx.log 服务器端java程序运行的控制信息
        // localhost.xx-xx.log ServletContext对象的log方法记录的日志信息存储到这个文件中
        // localhost_access_log.xx-xx.txt 访问日志

        int age = 17;
        // 小于18岁非法
        if (age < 18) {
            application.log("年龄未成年", new RuntimeException("please leave!"));
        }
        // 取出来
        Object Obj = application.getAttribute("userObj");
        // 输出到浏览器
        out.print(Obj + "<br>");
        //在A里面set的存放可以在B里面直接取出来，但是A必须先运行，不然存不进去
    }
}
