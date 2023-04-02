package de.tu_ilmenau.javaweb.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Author : Binbin Luo
 * Date : 01.04.2023
 */
@WebServlet("/test/session")
public class SessionTestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // session代表一次会话对象
        // 一次会话可以包含多个请求request
        // 获取session对象
        // http是无状态协议，请求结束以后，通道就关闭了，减少服务器的负载
        // 为了保存状态所以要使用session会话
        // 如果没有session的时候，就新建一个session
        // session是存在服务器当中的
        // 获取当前的session对象，如果没有获取任何session对象，则新建一个session
        HttpSession session = request.getSession();
//        HttpSession session = request.getSession(false);
        // 从服务器获取当前session对象，如果没有则不会新建，返回一个null，拿不到就算了
        // sessionID是以cookie的形式保存的，id是一个32位的数
        // cookie禁用了以后可以使用url重写机制
        // 比如servlet12/test/session;jsessionid=19Dxxxxx来确保每次请求都可以获得一样的session


        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.print("session = " + session);
//        org.apache.catalina.session.StandardSessionFacade@722aee40
        // 关闭浏览器之后再打开就是新的请求了
        // request < session < context

    }
}
