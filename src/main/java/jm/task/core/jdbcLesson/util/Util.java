package jm.task.core.jdbcLesson.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private static final String URL = "jdbc:mysql://localhost:3306/users?serverTimezone=Europe/Moscow";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "127486Pf";
    private static Connection connection;

    public static Connection getConnection() {
        return connection;
    }

        // 1. Register JDBC Driver
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        // 2. Create Connection
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        // 3. Close Connection
//        try {
//            connection.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }
}
