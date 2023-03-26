package de.tu_ilmenau.javaweb.web;

import de.tu_ilmenau.javaweb.jdbc.DButils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Author : Binbin Luo
 * Date : 27.03.2023
 */
public class DeptDelete extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String deptno = request.getParameter("deptno");
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        // 在数据库中删除
        try {
            conn = DButils.getConnection();

            // 开启事务
            conn.setAutoCommit(false);

            String sql = "delete from t_dept where deptno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,deptno);
            count = ps.executeUpdate();

            conn.commit();
        } catch (SQLException e) {
            // 遇到异常要回滚
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            DButils.close(conn,ps,null);
        }
        if (count == 1) {
            // 删除成功以后，又要跳转回去列表页面
            request.getRequestDispatcher("/dept/list").forward(request,response);
        } else {
            request.getRequestDispatcher("/error.html").forward(request,response);
        }
    }
}
