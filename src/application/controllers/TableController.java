package application.controllers;

import application.App;
import application.models.Client;
import java.sql.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * Controller class for managing table views of client data.
 * This class handles the initialization and population of table views
 * with client information retrieved from a database.
 */

public class TableController {

    private String url = "jdbc:mysql://localhost:3306/clients";
    private String user = "student";
    private String password = "student";

    @FXML
    private TableView<Client> tableView;

    @FXML
    private TableView<Client> tableView2;

    @FXML
    private TableColumn<Client, Integer> idColumn;

    @FXML
    private TableColumn<Client, String> nameColumn;

    @FXML
    private TableColumn<Client, Integer> revenueColumn;

    @FXML
    private TableColumn<Client, String> nameColumn2;

    @FXML
    private TableColumn<Client, Integer> revenueColumn2;

    @FXML
    private TableColumn<Client, Integer> idColumn2;

    @FXML
    private TableColumn<Client, String> dateColumn2;

    private ObservableList<Client> data;
    private ObservableList<Client> data2;

    @FXML
    /**
     * Handles the action of switching to scene1.
     * This method is called when the user wants to navigate back to the first scene.
     *
     * @throws Exception if there's an error during scene transition
     */

    private void handleGoToScene1() throws Exception {
        App.stage.setScene(App.scene1);
    }

    @FXML
    /**
     * Initializes the table views with client data.
     * This method sets up the table columns, retrieves data from the database,
     * and populates the table views with the fetched information.
     * It handles two different queries:
     * 1. Aggregated client data with total revenue
     * 2. Detailed revenue data for each client
     * If any exception occurs during data retrieval, it will be printed to the stack trace.
     */

    public void initialize() {
        if (idColumn != null) {
            idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
            nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
            revenueColumn.setCellValueFactory(
                new PropertyValueFactory<>("revenue")
            );
            dateColumn2.setCellValueFactory(new PropertyValueFactory<>("date"));
            nameColumn2.setCellValueFactory(new PropertyValueFactory<>("name"));
            idColumn2.setCellValueFactory(
                new PropertyValueFactory<>("companyId")
            );
            revenueColumn2.setCellValueFactory(
                new PropertyValueFactory<>("revenue")
            );

            data = FXCollections.observableArrayList();
            data2 = FXCollections.observableArrayList();
            try {
                DBUtility.queryData(
                    "select clients.*, SUM(r.revenue) AS total_revenue from clients join revenue r on clients.id = r.companyId GROUP by clients.id;"
                );
                while (DBUtility.rs.next()) {
                    int id = DBUtility.rs.getInt("id");
                    String name = DBUtility.rs.getString("name");
                    int revenue = DBUtility.rs.getInt("total_revenue");
                    data.add(new Client(id, name, revenue));
                }
                DBUtility.queryData(
                    "select *, c.company as comp from revenue join clients c on c.id = companyId"
                );

                while (DBUtility.rs.next()) {
                    int id = DBUtility.rs.getInt("id");
                    String name = DBUtility.rs.getString("comp");
                    int revenue = DBUtility.rs.getInt("revenue");
                    String date = DBUtility.rs.getString("date");
                    data2.add(new Client(id, name, revenue, date));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            tableView.setItems(data);
            tableView2.setItems(data2);
        }
    }
}
