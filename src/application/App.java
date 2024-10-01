package application;

import java.io.IOException;
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
        FXMLLoader fxmlLoader1 = new FXMLLoader(
            App.class.getResource("/app-view.fxml")
        );
        FXMLLoader fxmlLoader2 = new FXMLLoader(
            App.class.getResource("/table.fxml")
        );
        scene1 = new Scene(fxmlLoader1.load(), 600, 400);
        scene2 = new Scene(fxmlLoader2.load(), 600, 400);
        this.stage.setTitle("Client");
        this.stage.setScene(scene1);
        this.stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
