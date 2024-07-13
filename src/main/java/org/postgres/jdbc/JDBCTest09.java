package org.postgres.jdbc;

import org.postgres.jdbc.utils.DBUtils;

import java.sql.*;
import java.util.Scanner;

public class JDBCTest09 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to use the user system");
        System.out.print("Please enter your username: ");
        String loginName = scan.nextLine();
        System.out.print("Please enter your password: ");
        String password = scan.nextLine();

        // connection the database try to validate the user is correct
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Boolean loginSuccess = false;
        String username = null;

        try {
            // Get the data connection
            conn = DBUtils.getConnection();
            String sql = "select * from \"user\" where username = ? and password = ?";
            // first to compile
            ps = conn.prepareStatement(sql);
            // important: the all JDBC the first is 1, not like other is first on 0
            // 以下 code 含義是，給第一個占位符 ? 傳值
            ps.setString(1, loginName);
            // 以下 code 含義是，給第二個占位符 ? 傳值
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                loginSuccess = true;
                username = rs.getString("username");
            }
        } catch (SQLException se) {
            throw new RuntimeException(se);
        } finally {
            DBUtils.close(conn, ps, rs);
        }
        System.out.println(loginSuccess ? "loginSuccess welcome: " + username : "loginFalse try again");
    }
}
