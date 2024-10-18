package application.controllers;

import java.sql.*;

/**
 * Utility class for database operations.
 * Provides methods for connecting to the database and executing queries.
 * This class manages a static connection and result set for database interactions.
 */

public class DBUtility {

    private static String url = "jdbc:mysql://localhost:3306/clients";
    private static String user = "student";
    private static String password = "student";

    public static Connection connection;
    public static ResultSet rs;

    /**
     * Establishes a connection to the database using the specified URL, user, and password.
     * If the connection fails, the exception stack trace is printed.
     */

    public static void connect() {
        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Executes a SQL query on the database and stores the result in the static ResultSet.
     * If the query execution fails, the exception stack trace is printed.
     *
     * @param query The SQL query string to be executed
     */

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
