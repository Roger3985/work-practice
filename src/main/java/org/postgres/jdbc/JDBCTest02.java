package org.postgres.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCTest02 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // getConnection 的 overloading method
        // getConnection(url)
        // Class.forName("org.postgresql.Driver");
        // String url = "jdbc:postgresql://localhost:5433/postgres?user=postgres&password=mysecretpassword";
        // Connection conn = DriverManager.getConnection(url);
        // System.out.println(conn);

        // getConnection(url, info)
        // url
        Class.forName("org.postgresql.Driver");
        String url   = "jdbc:postgresql://localhost:5433/postgres";
        // info
        Properties info = new Properties();
        info.setProperty("user", "postgres");
        info.setProperty("password", "mysecretpassword");
        Connection conn = DriverManager.getConnection(url, info);
        System.out.println("====> " + conn);
    }
}
