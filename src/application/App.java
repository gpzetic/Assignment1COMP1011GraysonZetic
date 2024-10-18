package application;

import application.controllers.DBUtility;
import java.io.IOException;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * The main application class for the client management system.
 * This class extends JavaFX's Application class and is responsible for
 * initializing the user interface, setting up scenes, and managing
 * the primary stage of the application.
 */

public class App extends Application {

    public static Scene scene1;
    public static Scene scene2;

    public static FXMLLoader fxmlLoader1;
    public static FXMLLoader fxmlLoader2;

    public static Stage stage;

    /**
     * Starts the JavaFX application.
     * This method is called after the init() method has returned, and after
     * the system is ready for the application to begin running.
     *
     * @param stage the primary stage for this application, onto which
     * the application scene can be set.
     * @throws IOException if there is an error loading the FXML files.
     */

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
        this.stage.getIcons()
            .add(new Image(getClass().getResourceAsStream("/money.png")));
        this.stage.setTitle("Client");
        this.stage.setScene(scene1);
        this.stage.show();
        String sqlStatements = DBUtility.csvToSql(
            getClass().getResourceAsStream("/sample_clients.csv"),
            "clientstest"
        );
        System.out.println(sqlStatements);
        try {
            DBUtility.connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * The main entry point for the application.
     * This method launches the JavaFX application.
     *
     * @param args command line arguments passed to the application.
     *             These are not used in this application.
     */

    public static void main(String[] args) {
        launch();
    }
}
