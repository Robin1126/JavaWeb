package de.tu_ilmenau.javaweb.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Author : Binbin Luo
 * Date : 05.04.2023
 * 类似栈的结构，Filter1 到 Filter2 跟配置的顺序有关，FilterMapping靠上的先执行
 */
@WebServlet("/a.do")
public class AServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("AServlet's doGet method execute!");
    }
}
