package de.tu_ilmenau.javaweb.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Author : Binbin Luo
 * Date : 24.03.2023
 */
public class BServletTime extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8"); // 响应的乱码问题就是用这个setContentType中设置charset=UTF-8
        // Tomcat10之后就不需要这段代码了
        PrintWriter out = response.getWriter();

        Object obj = request.getAttribute("nowTime");
        out.print("转发过来的时间是：" + obj);
        out.print("<br>");
        // 获取应用的根路径，项目名
        String path = request.getContextPath();
        out.print(path + "<br>");    //  /Servlet09

        // 获取请求方式
        String method = request.getMethod();
        out.print(method + "<br>"); // GET

        // 获取请求的uri
        String uri = request.getRequestURI();
        out.print(uri + "<br>"); // /Servlet09/b

        // 获取servlet path
        String servletPath = request.getServletPath();
        out.println(servletPath + "<br>");// /b 不带项目名
    }
}
