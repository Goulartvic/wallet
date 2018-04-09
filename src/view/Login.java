package view;

import update.UpdateThread;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Login extends Application {

    private static Stage stage;

    @Override
    public void start(Stage stage) throws Exception {
        ScheduledThreadPoolExecutor stpe = new ScheduledThreadPoolExecutor(2);
        stpe.scheduleAtFixedRate(new UpdateThread(), 0, 30, TimeUnit.SECONDS);
        Parent login = FXMLLoader.load(getClass().getResource("Login.fxml"));
        Scene scene = new Scene(login);
        stage.setScene(scene);
        stage.show();
        setStage(stage);
    }

    public static Stage getStage() {
        return stage;
    }

    public static void setStage(Stage stage) {
        Login.stage = stage;
    }

    public static void main(String[] args) {
        launch(args);
    }

}
