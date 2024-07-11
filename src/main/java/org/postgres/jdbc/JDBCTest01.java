package org.postgres.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

public class JDBCTest01 {
    public static void main(String[] args) {
        ResourceBundle bundle = ResourceBundle.getBundle("jdbc", Locale.TAIWAN);
        String driver = bundle.getString("driver-class-name");
        String url = bundle.getString("url");
        String user = bundle.getString("user");
        String password = bundle.getString("password");

        Connection conn = null;
        try {
            // 1. 註冊驅動
            Class.forName(driver);

            // 2. 獲取連接
            conn = DriverManager.getConnection(url, user, password);
            System.out.println(conn);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
