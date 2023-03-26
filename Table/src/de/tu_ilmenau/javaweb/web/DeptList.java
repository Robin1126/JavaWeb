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
public class DeptList extends HttpServlet {


    // 重写doGet方法


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取应用的根路径
        String contextPath = request.getContextPath();

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.print("        <!DOCTYPE html>");
        out.print("<html>   ");
        out.print("	<head>   ");
        out.print("		<meta charset='utf-8'>   ");
        out.print("		<title>部门列表页面</title>   ");
        out.print("	</head>   ");
        out.print("	<body>   ");

        out.print("    	<script type='text/javascript'>");
        out.print("            function del(dno) {  ");
        out.print("        if(window.confirm('确认删除？')) {    ");
        out.print("            document.location.href = '" + contextPath + "/dept/delete?deptno=' + dno;   ");
        out.print("        }   ");
        out.print("    }        ");
        out.print("</script>      ");

        out.print("		<h1 align='center'>部门列表</h1>   ");
        out.print("		<hr>  ");
        out.print("		<table border='1px' align='center' width='50%'>   ");
        out.print("			<tr>       ");
        out.print("				<th>序号</th>    ");
        out.print("				<th>部门编号</th>    ");
        out.print("				<th>部门名称</th>    ");
        out.print("				<th>部门位置</th>    ");
        out.print("				<th>操作</th>  ");
        out.print("			</tr>   ");

        // 上面死的一部分


        // 连接数据库查询 JDBC
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DButils.getConnection();
            String sql = "select deptno, dname, loc from t_dept";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            int i = 0;
            while (rs.next()) {
                String deptno = rs.getString("deptno");
                String dname = rs.getString("dname");
                String loc = rs.getString("loc");

                out.print("			<tr align='center'>   ");
                out.print("				<td>" + (++i) + "</td>   ");
                out.print("				<td>" + deptno + "</td>    ");
                out.print("				<td>" + dname + "</td>   ");
                out.print("				<td>" + loc + "</td>   ");
                out.print("				<td>      ");
                out.print("					<a href='javascript:void(0)' onclick='del(" + deptno + ")'>删除</a>");
                out.print("					<a href='edit.html'>修改</a>   ");
                // 点超链接的时候相当于发送了请求，这个格式不是随便写的，都是uri?属性=属性值&属性=属性值
                out.print("					<a href='" + contextPath + "/dept/detail?deptno=" + deptno + "'>详情</a>");
                out.print("				</td>   ");
                out.print("			</tr>  ");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButils.close(conn, ps, rs);
        }
        // 下面死的一部分
        out.print("		</table>     ");
        out.print("		<hr>    ");
        out.print("		<a href='add.html'>新增部门</a>   ");
        out.print("	</body>   ");
        out.print("</html>   ");
    }
}
