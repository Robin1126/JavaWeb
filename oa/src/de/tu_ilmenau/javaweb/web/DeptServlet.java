package de.tu_ilmenau.javaweb.web;

import de.tu_ilmenau.javaweb.jdbc.DButils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
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
 * Date : 30.03.2023
 */
// 模板类 也可以写成"/dept/*"表示模糊匹配
@WebServlet({"/dept/list", "/dept/update", "/dept/edit", "/dept/save", "/dept/delete", "/dept/detail"})
public class DeptServlet extends HttpServlet {
    // 模板方法
    // 重写service方法

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletPath = request.getServletPath(); // 这个是整个的路径
        if ("/dept/list".equals(servletPath)) {
            doList(request, response);
        } else if ("/dept/save".equals(servletPath)) {
            doSave(request, response);
        } else if ("/dept/detail".equals(servletPath)) {
            doDetail(request, response);
        } else if ("/dept/update".equals(servletPath)) {
            doUpdate(request, response);
        } else if ("/dept/delete".equals(servletPath)) {
            doDel(request, response);
        } else if ("/dept/edit".equals(servletPath)) {
            doEdit(request, response);
        }

    }

    private void doDel(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
            ps.setString(1, deptno);
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
            DButils.close(conn, ps, null);
        }
        if (count == 1) {
            // 删除成功以后，又要跳转回去列表页面
//            request.getRequestDispatcher("/dept/list").forward(request,response);
            response.sendRedirect(request.getContextPath() + "/dept/list");
        } else {
//            request.getRequestDispatcher("/error.html").forward(request,response);
            response.sendRedirect(request.getContextPath() + "/error.html");
        }
    }

    private void doEdit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String contextPath = request.getContextPath(); // 这个是根路径

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

    private void doUpdate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String deptno = request.getParameter("deptno");
        String dname = request.getParameter("dname");
        String loc = request.getParameter("loc");

        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            conn = DButils.getConnection();
            String sql = "update t_dept set dname = ?, loc = ? where deptno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, dname);
            ps.setString(2, loc);
            ps.setString(3, deptno);
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButils.close(conn, ps, null);
        }
        if (count == 1) {
            // 更新成功，返回list页面
//            request.getRequestDispatcher("/dept/list").forward(request,response);
            response.sendRedirect("/table/dept/list");
        } else {
//            request.getRequestDispatcher("/error.html").forward(request,response);
            response.sendRedirect("/table/error.html");
        }
    }

    private void doDetail(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
            DButils.close(conn, ps, rs);
        }
        out.print("		<input type='button' value='back' onclick='window.history.back()'/>");
        out.print("	</body>    ");
        out.print("</html>  ");
    }

    private void doSave(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            ps.setString(1, deptno);
            ps.setString(2, dname);
            ps.setString(3, loc);
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButils.close(conn, ps, null);
        }
        if (count == 1) {
            // 保存成功，跳转到列表页面
            // 但是转发的时候是post请求，而Deptlist处理的是get请求
            request.getRequestDispatcher("/dept/list").forward(request, response);
        } else {
            // 保存失败，跳转到错误页面
            // 不需要加项目名，这是默认在项目下的根查找的
            request.getRequestDispatcher("/error.html").forward(request, response);
        }
    }

    private void doList(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
            String sql = "select deptno, dname, loc from t_dept order by deptno";
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
                out.print("					<a href='" + contextPath + "/dept/edit?deptno=" + deptno + "'>修改</a>   ");
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
        out.print("		<a href='" + contextPath + "/add.html'>新增部门</a>   "); // 带有项目名的href
        out.print("	</body>   ");
        out.print("</html>   ");
    }
}
