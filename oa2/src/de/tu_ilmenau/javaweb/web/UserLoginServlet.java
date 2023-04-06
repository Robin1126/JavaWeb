package de.tu_ilmenau.javaweb.web;

import de.tu_ilmenau.javaweb.bean.User;
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

        // 销毁cookie？
        // 删除cookie要将对应的值设置为null，MaxAge设置成0，马上销毁，然后路径还要设置成项目的根路径进行覆盖
        // 因为子路径的cookie不能删除父路径上的cookie，路径必须一模一样
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if ("username".equals(cookie.getName())) {
                cookie = new Cookie("username",null);
            }
            if ("password".equals(cookie.getName())) {
                cookie = new Cookie("password",null);
            }
            cookie.setMaxAge(0);
            // 覆盖原来的cookie路径
            cookie.setPath(request.getContextPath());
            response.addCookie(cookie);
        }

        // 获取session对象，销毁session
        HttpSession session = request.getSession(false);
        if (session != null) {
            // 从session域当中删除user对象
            session.removeAttribute("user");
            // 手动销毁session
            session.invalidate();

        }
        // 销毁后跳转到登录页，首页
        response.sendRedirect(request.getContextPath() + "/index.jsp");
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
            // 登录成功的时候，就新建一个cookie
            HttpSession session = request.getSession();
            //session.setAttribute("name", name);
            User user = new User(name,pwd);
            session.setAttribute("user",user);

            String check = request.getParameter("check");
            if("1".equals(check)) {
                Cookie cookie1 = new Cookie("username", name);
                Cookie cookie2 = new Cookie("password", pwd);

                // 设置cookie有效时间
                cookie1.setMaxAge(60 * 60 * 24 * 10);
                cookie2.setMaxAge(60 * 60 * 24 * 10);

                // 设置cookie路径
                cookie1.setPath(request.getContextPath());
                cookie2.setPath(request.getContextPath());

                // 设置响应cookie给浏览器
                response.addCookie(cookie1);
                response.addCookie(cookie2);
            }
            // 成功,跳转到用户界面
            response.sendRedirect(request.getContextPath() + "/dept/list");

        } else {
            // 失败
            response.sendRedirect(request.getContextPath() + "/loginfail.jsp");
        }
    }
}
