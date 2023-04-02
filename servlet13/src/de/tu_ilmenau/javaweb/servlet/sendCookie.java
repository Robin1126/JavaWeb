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
 * 创建一个java程序接收cookie
 */
@WebServlet("/sendCookies")
public class sendCookie extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 如何通过java程序获得cookie？
        // 使用getCookies()方法
        // 这个方法可能会返回一个null，并不是返回一个长度为0的数组，就不会创建这个数组
        Cookie[] cookies = request.getCookies();

        // 如果不是null，表示有cookie
        if (cookies != null) {
            // 遍历数组
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                String value = cookie.getValue();
                System.out.println(name + "=" + value);
            }

        }

    }
}
