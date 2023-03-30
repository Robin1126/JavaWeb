package de.tu_ilmenau.javaweb.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Author : Binbin Luo
 * Date : 30.03.2023
 */
// 增加注解WebServlet 表明这是一个Servlet
    // 注解中的属性 name 相当于servletname
    // loadOnStartup 指定服务器启动时是否加载这个servlet类
    // 以后直接在注解里面写出来就行了
@WebServlet(name = "hello", urlPatterns = {"/hello"}, loadOnStartup = 1, initParams = {@WebInitParam(name = "bubu", value = "1")
, @WebInitParam(name = "yier", value = "0")})
public class HelloServlet extends HttpServlet {
    public HelloServlet() {
        System.out.println("无参数构造方法执行，HelloServlet加载完成");
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        // 获取servletName
        String servletName = getServletName();
        out.print("servlet name = " + servletName + "<br>");

        // getInitParameter 是获取属性值， getInitParameterNames() 获取属性名数组
        String par1 = getInitParameter("bubu");
        String par2 = getInitParameter("yier");
        out.print(par1 + "<br>");
        out.print(par2 + "<br>");

    }
}
