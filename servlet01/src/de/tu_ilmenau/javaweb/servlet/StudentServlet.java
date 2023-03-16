package de.tu_ilmenau.javaweb.servlet;

import jakarta.servlet.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

public class StudentServlet implements Servlet {
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        // 如果想打印到网页当中？
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();



        // JDBC连接数据库，查询所有学生信息
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try
        {
            // 注册驱动
            Class.forName("com.mysql.jdbc.Driver");
            // 获取连接
            String url = "jdbc:mysql://localhost:3306/mydatabase";
            String username = "root";
            String password = "woaiky0522";
            conn = DriverManager.getConnection(url, username, password);
            // 获取预编译的数据库对象
            String sql = "select no, name from t_student";
            ps = conn.prepareStatement(sql);

            // 执行sql
            rs = ps.executeQuery();
            // 处理查询结果
            while(rs.next()) {
                String no = rs.getString("no");
                String name = rs.getString("name");
                System.out.println("no :" + no + ", name :" + name);
                out.print("编号：" + no + ", 姓名：" + name + "<br>");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        } finally {
            if (rs != null)
            {
                try
                {
                    rs.close();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            if (ps != null)
            {
                try
                {
                    ps.close();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
            if (conn != null)
            {
                try
                {
                    conn.close();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }

    }

    @Override
    public String getServletInfo() {
        return null;
    }

    @Override
    public void destroy() {

    }
}
