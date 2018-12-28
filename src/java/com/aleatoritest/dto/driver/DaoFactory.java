package com.aleatoritest.dto.driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author oancan
 */
public class DaoFactory {

    private static final String CONNECT = "jdbc:mysql://localhost:3306/aleatoritest";
    private static final String USER = "aleatoriuser";
    private static final String PASS = "aleatoritest";
    private static Connection conn = null;

    public static Connection getConnection() {
        if (conn == null) {
            createConnection();
        }
        return conn;
    }
    
    private static void createConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(CONNECT,USER, PASS);
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
            for (StackTraceElement stackEl : ex.getStackTrace()) {
                System.out.println(stackEl);
            }
        }
    }
}
