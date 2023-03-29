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
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Author : Binbin Luo
 * Date : 29.03.2023
 */
public class DeptEdit extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 输出动态网页
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String contextPath = request.getContextPath();

        // 处理修改页面过来的请求
        String deptno = request.getParameter("deptno");
        // 根据部门编号查询
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        out.print("        <!DOCTYPE html>");
        out.print("<html>");
        out.print("	<head>");
        out.print("		<meta charset='utf-8'>");
        out.print("		<title>修改部门</title>");
        out.print("	</head>");
        out.print("	<body>");
        out.print("		<h1>修改部门</h1>");
        out.print("		<hr>");
        out.print("		<form action='" + contextPath + "/dept/update' method='post'>");
        try {
            conn = DButils.getConnection();
            String sql = "select dname, loc from t_dept where deptno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, deptno);
            rs = ps.executeQuery();
            while (rs.next()) {
                String dname = rs.getString("dname");
                String loc = rs.getString("loc");
                out.print("                部门编号<input type='text' name='deptno' value='" + deptno + "' readonly/><br>");
                out.print("                部门名称<input type='text' name='dname' value='" + dname + "'/><br>");
                out.print("                部门位置<input type='text' name='loc' value='" + loc + "'/><br>");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButils.close(conn, ps, rs);
        }
        out.print("			<input type='submit' value='修改'/>");
        out.print("		</form>");
        out.print("	</body>");
        out.print("<html>");
    }
}
