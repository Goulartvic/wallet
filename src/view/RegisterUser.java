package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class RegisterUser extends Application {

    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        RegisterUser.stage = stage;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        Parent registerUser = FXMLLoader.load(getClass().getResource("RegisterUser.fxml"));
        Scene scene = new Scene(registerUser);
        stage.setScene(scene);
        stage.show();
        setStage(stage);
    }

}
