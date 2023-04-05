package de.tu_ilmenau.javaweb.web;

import de.tu_ilmenau.javaweb.jdbc.DButils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Author : Binbin Luo
 * Date : 03.04.2023
 */

// 上来就执行这一个servlet，用来验证cookie的，如果没有cookie或者cookie无效，就会跳转到index.jsp页面
@WebServlet("/welcome")
public class WelcomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 获取cookie
        // 这个数组的长度可能是null，如果不是null，长度是不为0的
        Cookie[] cookies = request.getCookies();
        String username = null;
        String password = null;
        boolean flag = false;

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String name = cookie.getName();
                if ("username".equals(name)) {
                    // 取出来的类型是username
                    username = cookie.getValue(); // 获取username中的name
                } else if ("password".equals(name)) {
                    // 取出来的类型是password
                    password = cookie.getValue(); // 获取user的密码
                }
            }
        }

        // 这里要使用name和password
        if (username != null && password != null) {
            // 验证用户和密码是否正确
            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            try {
                conn = DButils.getConnection();
                String sql = "select * from t_user where name = ? and password = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, username);
                ps.setString(2, password);
                rs = ps.executeQuery();
                if (rs.next()) {
                    flag = true;
                }

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DButils.close(conn, ps, rs);
            }

            if (flag) {
                // 登录成功
                // 获取session
                HttpSession session = request.getSession();
                session.setAttribute("name", username);
                // 重定向到list
                response.sendRedirect(request.getContextPath() + "/dept/list");
            } else {
                // 登录失败
                response.sendRedirect(request.getContextPath() + "/index.jsp");
            }

        } else {
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }

    }
}
