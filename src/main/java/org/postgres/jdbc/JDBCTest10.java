package org.postgres.jdbc;

import org.postgres.jdbc.utils.DBUtils;

import java.sql.*;
import java.util.ResourceBundle;

public class JDBCTest10 {
    public static void main(String[] args) {
        ResourceBundle resource = ResourceBundle.getBundle("jdbc");
        String driver = resource.getString("driver-class-name");
        String url = resource.getString("url");
        String user = resource.getString("user");
        String password = resource.getString("password");

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            // this is like Class.forName(driver), but register is not suggest to use
            DriverManager.registerDriver(new org.postgresql.Driver());
            conn = DriverManager.getConnection(url, user, password);
            System.out.println(conn);
            conn.setAutoCommit(false);
            String sql = "select * from \"user\" where username like ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, "_o%");
            rs = ps.executeQuery();
            while (rs.next()) {
                String username = rs.getString("username");
                System.out.println(username);
            }
            conn.commit();
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            DBUtils.close(conn, ps, rs);
        }
    }
}
