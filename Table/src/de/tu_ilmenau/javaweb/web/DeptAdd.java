package de.tu_ilmenau.javaweb.web;

import de.tu_ilmenau.javaweb.jdbc.DButils;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Author : Binbin Luo
 * Date : 28.03.2023
 */

// 这个小程序是用来处理数据库新增数据的
public class DeptAdd extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取部门的信息
        // 注意乱码问题
        request.setCharacterEncoding("UTF-8");
        String deptno = request.getParameter("deptno");
        String dname = request.getParameter("dname");
        String loc = request.getParameter("loc");

        // 连接数据库
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;

        try {
            conn = DButils.getConnection();
            String sql = "insert into t_dept(deptno, dname, loc) values(?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1,deptno);
            ps.setString(2,dname);
            ps.setString(3,loc);
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButils.close(conn,ps,null);
        }
        if (count == 1) {
            // 保存成功，跳转到列表页面
            // 但是转发的时候是post请求，而Deptlist处理的是get请求
            request.getRequestDispatcher("/dept/list").forward(request,response);
        } else {
            // 保存失败，跳转到错误页面
            // 不需要加项目名，这是默认在项目下的根查找的
            request.getRequestDispatcher("/error.html").forward(request,response);
        }
    }
}
