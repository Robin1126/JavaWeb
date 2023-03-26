package de.tu_ilmenau.javaweb.web;

import de.tu_ilmenau.javaweb.jdbc.DButils;

import java.sql.*;

/**
 * Author : Binbin Luo
 * Date : 26.03.2023
 */
public class Test {
    public static void main(String[] args) {
        Connection conn = null;
        Statement ps = null;
        ResultSet rs = null;

        try {
            conn = DButils.getConnection();
            String sql = "select deptno, dname, loc from dept";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery(sql);

            while (rs.next()) {
                String deptno = rs.getString("deptno");
                String dname = rs.getString("dname");
                String loc = rs.getString("loc");
                System.out.println(deptno + dname + loc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }  finally {
            DButils.close(conn,ps,rs);
        }

    }

}
