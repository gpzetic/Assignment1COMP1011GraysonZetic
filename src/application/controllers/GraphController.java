package application.controllers;

import application.App;
import application.models.Client;
import java.sql.*;
import java.sql.Date;
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
    private LineChart<String, Number> lineChart2;

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
            series.setName("Revenue by Client");

            XYChart.Series<String, Number> series2 = new XYChart.Series<>();
            series2.setName("Revenue Over Time");

            DBUtility dbu = new DBUtility();
            DBUtility dbu2 = new DBUtility();

            try (
                ResultSet rs = DBUtility.connection
                    .createStatement()
                    .executeQuery(
                        "SELECT clients.name, sum(r.revenue) as rev FROM clients join revenue r on clients.id = r.companyId group by clients.id"
                    );
                ResultSet rs2 = DBUtility.connection
                    .createStatement()
                    .executeQuery(
                        "SELECT date, sum(revenue) as rev FROM revenue group by date order by date"
                    )
            ) {
                while (rs.next()) {
                    int rev = rs.getInt("rev");
                    String name = rs.getString("name");
                    series
                        .getData()
                        .add(new XYChart.Data<String, Number>(name, rev));
                }
                while (rs2.next()) {
                    int rev = rs2.getInt("rev");
                    String date = rs2.getString("date");
                    series2
                        .getData()
                        .add(new XYChart.Data<String, Number>(date, rev));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            lineChart.getData().add(series);
            lineChart2.getData().add(series2);
        }
    }
}
