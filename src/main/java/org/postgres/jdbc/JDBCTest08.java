package org.postgres.jdbc;

import org.postgres.jdbc.utils.DBUtils;

import java.util.Scanner;
import java.sql.*;

public class JDBCTest08 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome to use the user system");
        System.out.print("Please enter your username: ");
        String loginName = scan.nextLine();
        System.out.print("Please enter your password: ");
        String password = scan.nextLine();

        // connection the database try to validate the user is correct
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        Boolean loginSuccess = false;
        String username = null;

        try {
            // Get the data connection
            conn = DBUtils.getConnection();
            // create the statement
            stmt = conn.createStatement();
            // run the sql statement
            String sql = "select * from \"user\" where username = '" + loginName + "' and password = '" + password + "'";
            rs = stmt.executeQuery(sql);
            if (rs.next()) {
                loginSuccess = true;
                username = rs.getString("username");
            }
            //
        } catch (SQLException se) {
            throw new RuntimeException(se);
        } finally {
            DBUtils.close(conn, stmt, rs);
        }

        System.out.println(loginSuccess ? "loginSuccess welcome: " + username : "loginFalse try again");

    }
}
