package view;

import control.CoinController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class FXMLCashWithdrawalController implements Initializable {

    @FXML
    private MenuBar menuBar;

    @FXML
    private Button cashBtnWithdrawal;

    @FXML
    private MenuItem menuSell;

    @FXML
    private MenuItem menuQuotation;

    @FXML
    private TextField cashQuantText;

    @FXML
    private MenuItem menuDeposit;

    @FXML
    private Button cashBtnCancel;

    @FXML
    private MenuItem menuWallet;

    @FXML
    private Label cashValueLabel;

    @FXML
    public void goQuitAction() {
        CashWithdrawal.getStage().close();
    }

    @FXML
    public void goWalletAction() {
        Wallet wallet = new Wallet();
        goQuitAction();
        try {
            wallet.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void goQuotationAction() {
        Quotation quotation = new Quotation();
        goQuitAction();
        try {
            quotation.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void goDepositAction() {
        CashDeposit deposit = new CashDeposit();
        goQuitAction();
        try {
            deposit.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void goBuyAction() {
        ExchangeCoin exchangeCoin = new ExchangeCoin();
        goQuitAction();
        try {
            exchangeCoin.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    void cashBtnCancelAction() {
        Wallet wallet = new Wallet();
        goQuitAction();
        try {
            wallet.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void cashBtnWithdrawalAction() {
        System.out.println(cashQuantText.getText());
        CoinController.getCoinController().withdrawMoney(Double.parseDouble(cashQuantText.getText()));
        Wallet wallet = new Wallet();
        goQuitAction();
        try {
            wallet.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
