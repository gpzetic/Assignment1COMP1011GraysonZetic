package application.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

    /**
     * Converts a CSV file to SQL CREATE TABLE and INSERT statements.
     *
     * @param csvFile The path to the CSV file
     * @param tableName The name of the table to be created
     * @return A string containing the CREATE TABLE statement followed by INSERT statements
     */
    public static String csvToSql(InputStream csvFile, String tableName) {
        StringBuilder sqlStatements = new StringBuilder();
        try (
            BufferedReader br = new BufferedReader(
                new InputStreamReader(csvFile)
            )
        ) {
            String line;
            String[] headers = br.readLine().split(",");

            // Generate CREATE TABLE statement
            sqlStatements
                .append("CREATE TABLE ")
                .append(tableName)
                .append(" (");
            for (int i = 0; i < headers.length; i++) {
                sqlStatements.append(headers[i]).append(" TEXT");
                if (i < headers.length - 1) sqlStatements.append(", ");
            }
            sqlStatements.append(");\n\n");

            // Generate INSERT statements
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                sqlStatements
                    .append("INSERT INTO ")
                    .append(tableName)
                    .append(" (");
                sqlStatements
                    .append(String.join(", ", headers))
                    .append(") VALUES (");
                for (int i = 0; i < values.length; i++) {
                    sqlStatements
                        .append("'")
                        .append(values[i].replace("'", "''"))
                        .append("'");
                    if (i < values.length - 1) sqlStatements.append(", ");
                }
                sqlStatements.append(");\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "Error processing CSV file: " + e.getMessage();
        }
        return sqlStatements.toString();
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
