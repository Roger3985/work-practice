package org.postgres.jdbc;

import org.postgres.jdbc.utils.DBUtils;

import java.sql.*;

public class JDBCTest13_batch {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        Long begin = System.currentTimeMillis();
        try {
            conn = DBUtils.getConnection();
            String sql = "insert into \"user\"(username, create_time, update_time) values(?, ?, ?)";
            ps = conn.prepareStatement(sql);
            int count = 0;
            for (int i = 1; i <= 10000; i++) {
                ps.setString(1, "batch: " + i);
                Timestamp now = new Timestamp(System.currentTimeMillis());
                ps.setTimestamp(2, now);
                ps.setTimestamp(3, now);
                // 打包
                ps.addBatch();
                // 如果打包夠 500 個，則執行 1 次 （則磁盤 IO 流一次）
                if (i % 500 == 0) {
                    count += ps.executeBatch().length;
                }
            }
            // 循環結束，也要在執行一次，避免丟失
            System.out.println("插入了：" + count + "條紀錄");

        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            DBUtils.close(conn, ps, rs);
        }

        long end = System.currentTimeMillis();

        System.out.println("總耗時 " + (end - begin) + "s");
    }
}
