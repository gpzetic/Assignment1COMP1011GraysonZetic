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
    private TableColumn<Client, Integer> idColumn;

    @FXML
    private TableColumn<Client, String> nameColumn;

    @FXML
    private TableColumn<Client, Integer> revenueColumn;

    private ObservableList<Client> data;

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

            data = FXCollections.observableArrayList();
            try (
                Connection connection = DriverManager.getConnection(
                    url,
                    user,
                    password
                );
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(
                    "select clients.*, SUM(r.revenue) AS total_revenue from clients join revenue r on clients.id = r.companyId GROUP by clients.id;"
                )
            ) {
                while (resultSet.next()) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    int revenue = resultSet.getInt("total_revenue");
                    data.add(new Client(id, name, revenue));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            tableView.setItems(data);
        }
    }
}
