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
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class GraphController {

    private String url = "jdbc:mysql://localhost:3306/clients";
    private String user = "student";
    private String password = "student";

    @FXML
    private LineChart<String, Number> lineChart;

    @FXML
    private CategoryAxis xAxis;

    @FXML
    private NumberAxis yAxis;

    @FXML
    private void handleGoToScene2() throws Exception {
        App.stage.setScene(App.scene2);
    }

    @FXML
    public void initialize() {
        if (lineChart != null) {
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName("Database Data");

            try (
                Connection connection = DriverManager.getConnection(
                    url,
                    user,
                    password
                );
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(
                    "SELECT clients.name, sum(r.revenue) as rev FROM clients join revenue r on clients.id = r.companyId group by clients.id"
                )
            ) {
                while (resultSet.next()) {
                    int rev = resultSet.getInt("rev");
                    String name = resultSet.getString("name");
                    series
                        .getData()
                        .add(new XYChart.Data<String, Number>(name, rev));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            lineChart.getData().add(series);
        }
    }
}
