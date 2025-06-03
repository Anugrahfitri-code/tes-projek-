import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import controller.LoginController;

public class MainApp extends Application {
    @Override
    public void start(Stage primaryStage) {
        LoginController loginController = new LoginController(primaryStage);
        Scene scene = new Scene(loginController.getView(), 350, 300);
        primaryStage.setTitle("InnoVote – Login");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
} 