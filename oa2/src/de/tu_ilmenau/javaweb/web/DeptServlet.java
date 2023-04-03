package de.tu_ilmenau.javaweb.web;

import de.tu_ilmenau.javaweb.bean.Dept;
import de.tu_ilmenau.javaweb.jdbc.DButils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Author : Binbin Luo
 * Date : 30.03.2023
 */
// 模板类 也可以写成"/dept/*"表示模糊匹配
// 可以将详情和修改写在一个servlet里面，只不过加一个flag，最后跳转到页面不同
@WebServlet({"/dept/list", "/dept/edit", "/dept/add", "/dept/delete", "/dept/detail"})
public class DeptServlet extends HttpServlet {
    // 模板方法
    // 重写service方法

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 访问jsp的时候，会自动生成一个session对象，因此判断的时候要加入一个session.getAttribute("name") != null
        // 或者在jsp文件中设定不自动生成session
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("name") != null) {
            // 已经登录成功
            String servletPath = request.getServletPath(); // 这个是整个的路径
            if ("/dept/list".equals(servletPath)) {
                doList(request, response);
            } else if ("/dept/add".equals(servletPath)) {
                doAdd(request, response);
            } else if ("/dept/detail".equals(servletPath)) {
                doDetail(request, response);
            } else if ("/dept/edit".equals(servletPath)) {
                doEdit(request, response);
            } else if ("/dept/delete".equals(servletPath)) {
                doDel(request, response);
            }
        } else {
            // 跳转到登录页面
            // 访问根，自动跳转到index页面
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }

    }

/*
    private void doLogin(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");
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
            session.setAttribute("name",name);
            // 成功
            request.getRequestDispatcher("/dept/list").forward(request,response);
        } else {
            // 失败
            response.sendRedirect(request.getContextPath()+"/loginfail.jsp");
        }
    }
*/

    /*private void doUpdate(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 通过deptno查出对应的数据
        Dept dept = new Dept();
        // 连接数据库查询
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String deptno = request.getParameter("deptno");

        try {
            conn = DButils.getConnection();
            String sql = "select deptno, dname, loc from t_dept where deptno = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,deptno);
            rs = ps.executeQuery();
            if (rs.next()) {
                // 查到了数据，转发给jsp
                dept.setDeptno(rs.getString("deptno"));
                dept.setDname(rs.getString("dname"));
                dept.setLoc(rs.getString("loc"));
                request.setAttribute("dept",dept);
                request.getRequestDispatcher("/edit.jsp").forward(request,response);
            } else {
                response.sendRedirect(request.getContextPath() + "/error.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButils.close(conn, ps, rs);
        }


    }*/

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
            // 这个是要带项目名的
//            request.getRequestDispatcher("/dept/list").forward(request,response);
            response.sendRedirect(request.getContextPath() + "/dept/list");
        } else {
//            request.getRequestDispatcher("/error.jsp").forward(request,response);
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }


    private void doEdit(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
            response.sendRedirect(request.getContextPath() + "/dept/list");
        } else {
//            request.getRequestDispatcher("/error.jsp").forward(request,response);
            response.sendRedirect(request.getContextPath() + "/error.jsp");
        }
    }

    private void doDetail(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        response.setContentType("text/html;charset=UTF-8");
        // 连接数据库 根据deptno查询
        // 获取传过来的属性值
        String deptno = request.getParameter("deptno");
        Dept dept = new Dept();
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
                // 查到对应的数据
                dept.setDeptno(deptno);
                dept.setDname(rs.getString("dname"));
                dept.setLoc(rs.getString("loc"));
                request.setAttribute("dept", dept);
                String flag = request.getParameter("f");
                if ("m".equals(flag)) {
                    request.getRequestDispatcher("/edit.jsp").forward(request, response);
                } else if ("d".equals(flag)) {
                    request.getRequestDispatcher("/detail.jsp").forward(request, response);
                }
            } else {
                // 没查到，跳转到error页面
                response.sendRedirect(request.getContextPath() + "/error.jsp");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DButils.close(conn, ps, rs);
        }
    }

    private void doAdd(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            response.sendRedirect(request.getContextPath() + "/dept/list");
        } else {
            // 保存失败，跳转到错误页面
            // 不需要加项目名，这是默认在项目下的根查找的
            response.sendRedirect("/error.jsp");
        }
    }

    private void doList(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        // 准备一个容器，用来专门存储部门
        List<Dept> depts = new ArrayList<>();
        // Servlet查询，然后用JSP展示
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
            // 遍历结果集
            while (rs.next()) {
                // 转发给jsp
                Dept dept = new Dept();
                dept.setDeptno(rs.getString("deptno"));
                dept.setDname(rs.getString("dname"));
                dept.setLoc(rs.getString("loc"));

                // 将部门对象放到List中
                depts.add(dept);
            }
            // 将这个容器放到request域中转发
            request.setAttribute("depts", depts);
            request.getRequestDispatcher("/list.jsp").forward(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 释放资源
            DButils.close(conn, ps, rs);
        }


    }
}
