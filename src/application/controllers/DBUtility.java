package application.controllers;

import java.sql.*;

public class DBUtility {

    private static String url = "jdbc:mysql://localhost:3306/clients";
    private static String user = "student";
    private static String password = "student";

    public static Connection connection;
    public static ResultSet rs;

    public static void connect() {
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void queryData(String query) {
        try {
            rs = connection.createStatement().executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // public ResultSet getResults(String s) {
    //     try (
    //         Statement statement = connection.createStatement();
    //         ResultSet resultSet = statement.executeQuery(s)
    //     ) {
    //         return resultSet;
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    //     return null;
    // }
}
