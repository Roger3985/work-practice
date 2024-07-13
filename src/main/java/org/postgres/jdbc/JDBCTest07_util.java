package org.postgres.jdbc;

import org.postgres.jdbc.utils.DBUtils;

import java.sql.*;

public class JDBCTest07_util {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtils.getConnection();
            stmt = conn.createStatement();
            String sql = "insert into \"user\" (username, password, create_time, update_time) values ('Roger21', 123456, now(), now())";
            int count = stmt.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                Integer id = rs.getInt(1);
                System.out.println("新增行的主鍵值: " + id);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            DBUtils.close(conn, stmt, rs);
        }
    }
}
