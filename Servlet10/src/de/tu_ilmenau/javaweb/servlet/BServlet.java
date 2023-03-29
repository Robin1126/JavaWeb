package de.tu_ilmenau.javaweb.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Author : Binbin Luo
 * Date : 29.03.2023
 */
public class BServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8"); // 响应的乱码问题就是用这个setContentType中设置charset=UTF-8
        // Tomcat10之后就不需要这段代码了
        PrintWriter out = response.getWriter();
        // 注意取的时候的name要和保存的时候一致，不然出来的是null
        Object obj = request.getAttribute("userObj");
        out.print("转发过来的对象是：" + obj);
    }
}
