package org.postgres.jdbc;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;

public class JDBCTest05 {
    // 處理時間
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");

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

            // 獲得連線
            conn = DriverManager.getConnection(url, user, password);
            System.out.println(conn);

            // 將自動提交修改成手動控制
            conn.setAutoCommit(false);

            // 執行 SQL 語句
            stmt = conn.createStatement();
            String sql = "select * from \"user\" where username = 'Roger'";
            rs = stmt.executeQuery(sql);

            // 處理查詢結果集
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String username = rs.getString("username");
                Timestamp create_time = rs.getTimestamp("create_time");
                System.out.println(id + "\t" + username + "\t" + create_time);
                // 將 java.sql.Timestamp 轉換成 java.util.Timestamp (通過建構子的方式來完成轉換)
                Date timestamp = new Date(create_time.getTime());
                String strDate = sdf.format(timestamp);
                System.out.println(strDate);
            }

            // 通過獲取結果集合獲得 metadata
            ResultSetMetaData resultSetMetaData = rs.getMetaData();
            int columnCount = resultSetMetaData.getColumnCount();
            System.out.println("共" + columnCount + "列");
            // 遍歷每一列
            for (int i = 1; i <= columnCount; i++) {
                // 1 是下標，所有的 JDBC 都是從 1 開始
                String columnName = resultSetMetaData.getColumnName(i);
                // 獲取列名
                System.out.println("第" + i + "列的列名: " + columnName);
                // 獲取資料類型的名稱
                String columnTypeName = resultSetMetaData.getColumnTypeName(i);
                System.out.println("第" + i + "列的資料類型的名稱: " + columnTypeName);
                // 獲取列的長度
                int columnDisplaySize = resultSetMetaData.getColumnDisplaySize(i);
                System.out.println("第" + i + "列的長度: " + columnDisplaySize);
            }

            // 提交修改
            conn.commit();

        } catch (Exception ex) {
            try {
                System.out.println("Transaction rollback");
                conn.rollback();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            ex.printStackTrace();
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
