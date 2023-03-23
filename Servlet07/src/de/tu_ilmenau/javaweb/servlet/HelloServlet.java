package de.tu_ilmenau.javaweb.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Author : Binbin Luo
 * Date : 23.03.2023
 */
public class HelloServlet extends HttpServlet {
    // 首先通过无参数的构造方法创建对象
    // 没有提供init方法，那么就执行HttpServlet的init方法
    // HttpServlet没有init方法，那么就调用GenericServlet类中的init方法
    // 接下来就是要执行service方法，这个service方法如果没有重写，那就是执行HttpServlet的方法
    // GenericServlet中的service方法是抽象方法，不会执行
    // service方法会不断的执行，只要用户不断发送请求

    // service方法没有重写，会自动判断getMethod来判断请求是什么类型
    // 如果没有改写get方法的时候，如果前端给你发一个get请求，就会报错405 不允许这种方法，因为没有重写的时候就是默认报错
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // get请求执行doGet
//        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.print("<h1>doGet</h1>");

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // post方法执行doPost方法
//        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.print("<h1>doPost</h1>");
    }

/*    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 享受不了http协议的405错误
       *//* resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.print("<h1>hello servlet</h1>");*//*


    }*/
}
