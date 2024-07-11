package org.postgres.jdbc;

import java.sql.*;
import java.util.ResourceBundle;

public class JDBCTest06_getGeneratedKeys {
    public static void main(String[] args) {
        ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
        String url = bundle.getString("url");
        String user = bundle.getString("user");
        String password = bundle.getString("password");
        String driver = bundle.getString("driver-class-name");
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // 註冊驅動
            Class.forName(driver);

            // 建立連線
            conn = DriverManager.getConnection(url, user, password);

            // 開啟交易
            conn.setAutoCommit(false);

            // 創建資料庫操作物件
            stmt = conn.createStatement();

            // 執行相關 SQL 語句
            String sql = "insert into \"user\" (username, password, create_time, update_time) values ('Roger21', 123456, now(), now())";
            // 注意，第二個參數是標誌位置，用來表示是否將新插入的資料主鍵值返回
            int count = stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            // 獲取這個新增行的主鍵值
            // 返回的這個 rs 結果集中就包含了新增行的主鍵值返回
            rs = stmt.getGeneratedKeys();
            // 通過結果集取主鍵值
            if (rs.next()) {
                Integer id = rs.getInt(1);
                System.out.println("新增行的主鍵值: " + id);
            }

        } catch (Exception ex) {
            try {
                System.out.println("rollback" + ex.getMessage());
                conn.rollback();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        } finally {
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
