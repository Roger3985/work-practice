package org.postgres.jdbc;

import org.postgres.jdbc.utils.DBUtils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Base64;

public class JDBCTest12_picture {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;
        FileInputStream in = null;

        try {
            conn = DBUtils.getConnection();
            conn.setAutoCommit(false);
            String sql = "insert into \"user\"(username, user_pic, create_time, update_time) values(?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            // give the param on ?
            ps.setString(1, "Roger2");
            in = new FileInputStream("/Users/sunzhengchang/Desktop/test.jpeg");
            byte[] imageBytes = in.readAllBytes();
            ps.setBytes(2, imageBytes);
            Timestamp now = new Timestamp(System.currentTimeMillis());
            ps.setTimestamp(3, now);
            ps.setTimestamp(4,now);
            // 執行 SQL
            int count = ps.executeUpdate();
            System.out.println("插入了" + count +"條資料");
            conn.commit();
            // 將字節陣列轉換為Base64編碼（或其他格式）
            String base64Image = Base64.getEncoder().encodeToString(imageBytes);

            // 在網頁上顯示圖片，這裡假設是HTML頁面
            String html =
                    "<html>" +
                    "<body>" +
                        "<img src='data:image/jpeg;base64," + base64Image + "'/>" +
                    "</body>" +
                    "</html>";
            System.out.println(html);
            System.out.println("成功插入圖片");
        } catch (Exception e) {
            try {
                conn.rollback();
            } catch (SQLException se) {
                se.printStackTrace();
            }
            throw new RuntimeException(e.getMessage());
        } finally {
            if (in != null) {
                try {
                    in.close();
                } catch (IOException se) {
                    throw new RuntimeException(se.getMessage());
                }
            }
            DBUtils.close(conn, ps, null);
        }
    }
}
