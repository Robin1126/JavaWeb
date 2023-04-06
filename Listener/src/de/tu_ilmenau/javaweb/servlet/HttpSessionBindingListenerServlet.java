package de.tu_ilmenau.javaweb.servlet;

import bean.User1;
import bean.User2;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Author : Binbin Luo
 * Date : 06.04.2023
 */
@WebServlet("/bind")
public class HttpSessionBindingListenerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取session对象
        HttpSession session = request.getSession();

        // 准备两个对象 user1 和 user2
        User1 user1 = new User1("1126","布布","123");
        User2 user2 = new User2("0522","一二","123");

        // 存储user1到session域
        // 只有user1才会绑定数据，因为user1类实现了BindingListener接口
        // 这个类比较特殊，如果把这个类绑定session的时候会监听
        session.setAttribute("user1",user1);

        // 存储user2到session域
        session.setAttribute("user2",user2);


    }
}
