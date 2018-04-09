package view;

import control.UserController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginControllerFxml implements Initializable {

    private static Stage stage;

    @FXML
    private MenuItem rateConfig;

    @FXML
    private Button loginBtnRegisterUser;

    @FXML
    private PasswordField LoginUserPass;

    @FXML
    private Button LoginBtn;

    @FXML
    private TextField LoginUserText;

    @FXML
    private MenuItem menuQuit;

    public LoginControllerFxml() {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @FXML
    public void goQuitAction() {
        Login.getStage().close();
    }

    @FXML
    public void loginBtnRegisterUserAction() {
        RegisterUser registerUser = new RegisterUser();
        goQuitAction();
        try {
            registerUser.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void LoginBtnAction() {
        if (UserController.getUserController().authenticateUser(LoginUserText.getText(), LoginUserPass.getText())) {
            Quotation quotation = new Quotation();
            goQuitAction();
            try {
                quotation.start(new Stage());
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Erro");
            alert.setHeaderText("Login Inválido");
            alert.setContentText("Verifique os dados digitados");
            alert.show();
        }

    }
}
