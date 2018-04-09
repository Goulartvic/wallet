package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Wallet extends Application {

    private static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {

        Parent wallet = FXMLLoader.load(getClass().getResource("Wallet.fxml"));
        Scene scene = new Scene(wallet);
        stage.setScene(scene);
        stage.show();
        setStage(stage);
        stage.setMaximized(true);
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        Wallet.stage = stage;
    }


    public static void main(String[] args) {
        launch(args);
    }

}
