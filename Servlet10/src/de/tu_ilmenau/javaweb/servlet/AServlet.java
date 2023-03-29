package de.tu_ilmenau.javaweb.servlet;

import de.tu_ilmenau.javaweb.bean.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Author : Binbin Luo
 * Date : 29.03.2023
 */
public class AServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = new User();
        user.setId("111");
        user.setName("布布");

        // 将用户信息存储到request对象当中
        request.setAttribute("userObj", user);



        // 转发
        request.getRequestDispatcher("/b").forward(request,response);

       // 重定向
        // 重定向时的路径当中需要以项目名开头，或者说需要添加项目名
        // 需要以项目名开始
        // 浏览器自发的发送另一个全新的请求
        // response对象将路径 /Servlet10/b 响应给了浏览器
        // 浏览器知道后又朝服务器发送了一次，所以地址栏最后发生了改变

        // 转发是web服务器搞定的，重定向是浏览器说了算
//        response.sendRedirect(request.getContextPath() + "/b");
    }
}
