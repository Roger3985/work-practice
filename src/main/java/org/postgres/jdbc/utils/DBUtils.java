package org.postgres.jdbc.utils;

import java.sql.*;
import java.util.ResourceBundle;

/**
 * ClassName: DBUtils
 * Description: JDBC 工具類
 * DateTime: 2024/7/11
 * Author: Roger
 * Version: 1.0
 */
public class DBUtils {

    /**
     * 工具類的建構方法一般都是私有化的， 因為工具類中的一般都是靜態的
     * 工具類就是為了方便編寫程式，所以工具類中的方法都是直接採用"類名"
     * 的方法訪問，因此不需要 new 物件。
     */
    private DBUtils(){}

    // 靜態變量
    private static String driver;
    private static String url;
    private static String user;
    private static String password;

    // 靜態代碼塊
    static {
        ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
        driver = bundle.getString("driver-class-name");
        url = bundle.getString("url");
        user = bundle.getString("user");
        password = bundle.getString("password");

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException();
        }
    }

    /**
     * 獲取資料庫連接物件
     * @return 連接物件
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        // 實際上這裡每一次調用 getConnection() method 時都會獲取一個全新的資料庫物件，實際上這樣效率是比較低的，後期會使用連線池改善
        Connection conn = DriverManager.getConnection(url, user, password);
        return conn;
    }

    /**
     * 釋放資源
     * @param conn 連接物件
     * @param stmt 資料庫操作物件
     * @param rs 結果集物件
     */
    public static void close(Connection conn, Statement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException se) {
                throw new RuntimeException();
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException se) {
                throw new RuntimeException();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException se) {
                throw new RuntimeException();
            }
        }
    }
}
