package view;

import control.CoinController;
import control.UserController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.User;

import java.util.HashMap;
import java.util.Map;

public class RegisterUserControllerFxml {


    @FXML
    private Button registerUserBtnCancel;

    @FXML
    private Button registerUserBtnSave;

    @FXML
    private PasswordField registerUserPassword;

    @FXML
    private TextField registerUserLoginName;

    @FXML
    private MenuItem menuQuit;

    public RegisterUserControllerFxml() {
    }

    @FXML
    public void goQuitAction() {
        RegisterUser.getStage().close();
    }

    @FXML
    public void registerUserBtnCancelAction() {
        Login login = new Login();
        goQuitAction();
        try {
            login.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void registerUserBtnSaveAction() {
        UserController userCtrl = UserController.getUserController();
        User user = new User(registerUserLoginName.getText(), registerUserPassword.getText(), UserController.getUserController().createWallet());
        userCtrl.create(user);
        Login login = new Login();
        goQuitAction();
        try {
            login.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
