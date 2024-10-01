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
    private void handleGoToScene1() throws Exception {
        App.stage.setScene(App.scene1);
    }

    @FXML
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
            try (
                Connection connection = DriverManager.getConnection(
                    url,
                    user,
                    password
                );
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(
                    "select clients.*, SUM(r.revenue) AS total_revenue from clients join revenue r on clients.id = r.companyId GROUP by clients.id;"
                );
                Statement statement2 = connection.createStatement();
                ResultSet resultSet2 = statement2.executeQuery(
                    "select *, c.company as comp from revenue join clients c on c.id = companyId"
                )
            ) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    int revenue = resultSet.getInt("total_revenue");
                    data.add(new Client(id, name, revenue));
                }
                while (resultSet2.next()) {
                    int id = resultSet2.getInt("id");
                    String name = resultSet2.getString("comp");
                    int revenue = resultSet2.getInt("revenue");
                    String date = resultSet2.getString("date");
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
