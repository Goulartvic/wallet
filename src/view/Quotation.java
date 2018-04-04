package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Quotation extends Application {

    private static Stage stage;

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        Quotation.stage = stage;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        Parent quotation = FXMLLoader.load(getClass().getResource("Quotation.fxml"));
        Scene scene = new Scene(quotation);
        stage.setScene(scene);
        stage.show();
        setStage(stage);
    }

}
