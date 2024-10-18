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

/**
 * Controller class for handling graph-related operations in the application.
 * This class manages the display and updating of line charts showing revenue data.
 * It interacts with the database to fetch and visualize client revenue information.
 */

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
    /**
     * Handles the transition to Scene 2.
     * This method is called when the user needs to switch to the second scene of the application.
     *
     * @throws Exception if there's an error during the scene transition
     */

    private void handleGoToScene2() throws Exception {
        App.stage.setScene(App.scene2);
    }

    @FXML
    /**
     * Initializes the graph controller by populating the line charts with data.
     * This method is automatically called after the FXML file has been loaded.
     * It queries the database for client revenue data and revenue over time,
     * then adds this data to the respective line charts for visualization.
     */

    public void initialize() {
        if (lineChart != null) {
            XYChart.Series<String, Number> series = new XYChart.Series<>();
            series.setName("Revenue by Client");

            XYChart.Series<String, Number> series2 = new XYChart.Series<>();
            series2.setName("Revenue Over Time");

            ResultSet rs;

            try {
                DBUtility.queryData(
                    "SELECT clients.name, sum(r.revenue) as rev FROM clients join revenue r on clients.id = r.companyId group by clients.id"
                );
                while (DBUtility.rs.next()) {
                    int rev = DBUtility.rs.getInt("rev");
                    String name = DBUtility.rs.getString("name");
                    series
                        .getData()
                        .add(new XYChart.Data<String, Number>(name, rev));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                DBUtility.queryData(
                    "SELECT date, sum(revenue) as rev FROM revenue group by date order by date"
                );
                while (DBUtility.rs.next()) {
                    int rev = DBUtility.rs.getInt("rev");
                    String date = DBUtility.rs.getString("date");
                    series2
                        .getData()
                        .add(new XYChart.Data<String, Number>(date, rev));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            lineChart.getData().add(series);
            lineChart2.getData().add(series2);
        }
    }
}
