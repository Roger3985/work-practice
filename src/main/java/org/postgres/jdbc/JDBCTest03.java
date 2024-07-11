package org.postgres.jdbc;

import java.sql.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class JDBCTest03 {
    public static void main(String[] args) {
        // 讀取屬性配置文件
        ResourceBundle bundle = ResourceBundle.getBundle("jdbc", Locale.TAIWAN);
        String driver = bundle.getString("driver-class-name");
        String url = bundle.getString("url");
        String user = bundle.getString("user");
        String password = bundle.getString("password");
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // 註冊驅動
            Class.forName(driver);

             // 獲取連接
            conn = DriverManager.getConnection(url, user, password);
            System.out.println(conn);

            // 獲取資料庫操控物件
            stmt = conn.createStatement();

            // 執行 SQL　語句
            String sql = "select id, username, password from \"user\"";
            rs = stmt.executeQuery(sql);

            // 處理查詢結果集
            // 處理的方式是直接打印輸出
            while (rs.next()) {
                String id = rs.getString(1);
                String username = rs.getString(2);
                String pwd = rs.getString(3);
                System.out.println(id);
                System.out.println(username);
                System.out.println(pwd);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 釋放資源
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }

        }
    }
}
