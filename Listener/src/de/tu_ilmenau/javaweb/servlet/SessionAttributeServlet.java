package de.tu_ilmenau.javaweb.servlet;

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
@WebServlet("/session")
public class SessionAttributeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 新建session
        HttpSession session = request.getSession();

        // session中存入数据
        session.setAttribute("name","布布");

        // session中改变数据,要想改变数据，需要存储的名字相同，也就是都是name才可以
        session.setAttribute("name","一二");

        // session中删除数据
        session.removeAttribute("name");

    }

}
