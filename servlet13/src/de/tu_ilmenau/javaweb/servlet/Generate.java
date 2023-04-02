package de.tu_ilmenau.javaweb.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Author : Binbin Luo
 * Date : 02.04.2023
 */
@WebServlet("/cookie/generate")
public class Generate extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 创建cookie对象
        Cookie cookie = new Cookie("bubu", "1126");
        Cookie cookie1 = new Cookie("yier", "0522");
        // cookie方法


        // 设置cookie的有效时间 setMaxAge 以秒为单位，保存在硬盘文件当中
        // 如果设置为0，则表示cookie被删除
        // 如果设置为负数，表示该cookie不会被存储在硬盘当中，只会被存储在浏览器的运行内存当中。
        cookie.setMaxAge(-1); // 和不调用setMaxAge是一个效果
        cookie1.setMaxAge(-1);
        // 默认情况下，没有设置path的时候，cookie的关联路径是什么？
        // 浏览器提交或者不提交cookie和浏览器中输入的url相关
        // 默认的路径是contextPath/cookie以及它的子路径
       cookie.setPath(request.getContextPath()); // 这样表示只要是servlet13的路径和子路径都会发送cookie
       cookie1.setPath(request.getContextPath()); // 这样表示只要是servlet13的路径和子路径都会发送cookie


        // 将cookie发送给浏览器
        response.addCookie(cookie);
        response.addCookie(cookie1);
    }
}
