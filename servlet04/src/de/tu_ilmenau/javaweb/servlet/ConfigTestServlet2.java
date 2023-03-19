package de.tu_ilmenau.javaweb.servlet;

import jakarta.servlet.*;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Author : Binbin Luo
 * Date : 19.03.2023
 */
public class ConfigTestServlet2 extends GenericServlet {
    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        // 获取ServletConfig对象
        ServletConfig config = this.getServletConfig();
        // 输出
        out.print("ServletConfig对象：" + config + "<br>");

        String result = config.getInitParameter("key");
        out.print(result + "<br>");

    }
}
