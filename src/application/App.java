package application;

import application.controllers.DBUtility;
import java.io.IOException;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class App extends Application {

    public static Scene scene1;
    public static Scene scene2;

    public static FXMLLoader fxmlLoader1;
    public static FXMLLoader fxmlLoader2;

    public static Stage stage;

    @Override
    public void start(Stage stage) throws IOException {
        this.stage = stage;
        DBUtility.connect();
        FXMLLoader fxmlLoader1 = new FXMLLoader(
            App.class.getResource("/app-view.fxml")
        );
        FXMLLoader fxmlLoader2 = new FXMLLoader(
            App.class.getResource("/table.fxml")
        );
        scene1 = new Scene(fxmlLoader1.load(), 1200, 800);
        scene2 = new Scene(fxmlLoader2.load(), 1200, 800);
        this.stage.setTitle("Client");
        this.stage.setScene(scene1);
        this.stage.show();
        try {
            DBUtility.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
