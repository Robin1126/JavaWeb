package de.tu_ilmenau.javaweb.web;

import de.tu_ilmenau.javaweb.jdbc.DButils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Author : Binbin Luo
 * Date : 02.04.2023
 */
@WebServlet({"/dept/login","/dept/exit"})
public class UserLoginServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String path = request.getServletPath();
        if ("/dept/login".equals(path)) {
            doLogin(request,response);
        } else if ("/dept/exit".equals(path)) {
            doExit(request,response);
        }


    }

    private void doExit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取session对象，销毁session
        HttpSession session = request.getSession(false);
        if (session != null) {
            // 手动销毁session
            session.invalidate();
            // 销毁后跳转到登录页，首页
            response.sendRedirect(request.getContextPath());
        }
    }

    private void doLogin(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        String name = request.getParameter("name");
        String pwd = request.getParameter("pwd");
        boolean success = false;


        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DButils.getConnection();
            String sql = "select id from t_user where name = ? and password = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, pwd);
            rs = ps.executeQuery();
            if (rs.next()) {
                success = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButils.close(conn, ps, rs);
        }

        if (success) {
            // 获取session对象
            // 没有就新建
            HttpSession session = request.getSession();
            session.setAttribute("name", name);
            // 成功
            request.getRequestDispatcher("/dept/list").forward(request, response);
        } else {
            // 失败
            response.sendRedirect(request.getContextPath() + "/loginfail.jsp");
        }
    }
}
