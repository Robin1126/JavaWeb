package de.tu_ilmenau.javaweb.servlet;

import jakarta.servlet.ServletException;
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
// 注解的属性是数组，并且数组中只有一个元素的时候，大括号可以省略
// 数组中的属性urlpattern和value是一样的
@WebServlet(name = "welcome", value = {"/welcome","/wel"}, loadOnStartup = 2)
public class WelcomeServlet extends HttpServlet {
    public WelcomeServlet() {
        System.out.println("初始化WelcomeServlet成功");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.print("welcome!"+"<br>");
    }
}
