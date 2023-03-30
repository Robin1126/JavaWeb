package de.tu_ilmenau.javaweb.jdbc;

import java.sql.*;
import java.util.ResourceBundle;

/**
 * Author : Binbin Luo
 * Date : 25.03.2023
 * JDBC工具类
 */
public class DButils {
    // 静态变量先执行
    private static ResourceBundle bundle = ResourceBundle.getBundle("resources.jdbc");
    private static String driver = bundle.getString("driver");
    private static String url = bundle.getString("url");
    private static String username = bundle.getString("username");
    private static String password = bundle.getString("password");

    static {
        // 注册驱动
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // 获取连接对象，返回conn
    public static Connection getConnection() throws SQLException {


        Connection conn = DriverManager.getConnection(url, username, password);
        return conn;
    }

    //释放资源

    /**
     *
     * @param conn 连接对象
     * @param ps 数据库操作对象
     * @param rs 查询结果集
     */
    public static void close(Connection conn, Statement ps, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}
