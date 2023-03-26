package de.tu_ilmenau.javaweb.web;

import de.tu_ilmenau.javaweb.jdbc.DButils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

/**
 * Author : Binbin Luo
 * Date : 26.03.2023
 */
public class DeptDetail extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.print("        <!DOCTYPE html>");
        out.print("<html>  ");
        out.print("	<head>   ");
        out.print("		<meta charset='utf-8'>   ");
        out.print("		<title>部门详情</title>  ");
        out.print("	</head>   ");
        out.print("	<body>  ");
        out.print("		<h1>部门详情</h1>  ");
        out.print("		<hr>  ");


        // 连接数据库 根据deptno查询
        // 获取传过来的属性值
        String deptno = request.getParameter("deptno");

        // 连接数据库
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DButils.getConnection();
            String sql = "select deptno, dname, loc from t_dept where deptno = ?"; // 设置模板
            ps = conn.prepareStatement(sql);
            ps.setString(1, deptno);
            rs = ps.executeQuery();
            if (rs.next()) {
                String dname = rs.getString("dname");
                String loc = rs.getString("loc");
                out.print(" 部门编号：" + deptno + "<br> ");
                out.print(" 部门名称：" + dname + "<br>   ");
                out.print(" 部门位置：" + loc + "<br>  ");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButils.close(conn,ps,rs);
        }
        out.print("		<input type='button' value='back' onclick='window.history.back()'/>");
        out.print("	</body>    ");
        out.print("</html>  ");
    }
}
