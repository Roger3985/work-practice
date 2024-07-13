package org.postgres.jdbc;

import org.postgres.jdbc.utils.DBUtils;

import java.sql.*;

public class JDBCTest11_Page {
    public static void main(String[] args) {
        // 每頁顯示記錄條數
        int pageSize = 3;
        // 顯示在第幾頁數（頁碼）
        int pageNo = 1;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            String sql = "select username from \"user\" limit ? offset ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, pageSize); // 設置每頁顯示的紀錄數
            ps.setInt(2, (pageNo - 1) * pageSize); // 計算偏移量，第一頁從 0 開始
            rs = ps.executeQuery();
            while (rs.next()) {
                String username = rs.getString("username");
                System.out.println(username);
            }
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        } finally {
            DBUtils.close(conn, ps, rs);
        }
    }
}
