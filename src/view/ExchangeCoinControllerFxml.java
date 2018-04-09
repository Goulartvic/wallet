package view;

import control.CoinController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Coin;

import java.net.URL;
import java.util.ResourceBundle;

public class ExchangeCoinControllerFxml implements Initializable {

    @FXML
    private MenuBar menuBar;

    @FXML
    private TextField buyQuantText;

    @FXML
    private Label buyValueLabel;

    @FXML
    private MenuItem menuSell;

    @FXML
    private MenuItem menuQuotation;

    @FXML
    private MenuItem menuDeposit;

    @FXML
    private Button buyBtn;

    @FXML
    private MenuItem menuWallet;

    @FXML
    private Button buyBtnCancel;

    @FXML
    private ComboBox<Coin> buyCbBoxCoin;

    @FXML
    private ComboBox<Coin> buyCbBoxCoinOut;

    @FXML
    public void goQuitAction() {
        ExchangeCoin.getStage().close();
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
    public void buyBtnAction() {
        CoinController.getCoinController().exchangeMoney(Double.parseDouble(buyQuantText.getText()), buyCbBoxCoinOut.getValue(), buyCbBoxCoin.getValue());
        Wallet wallet = new Wallet();
        goQuitAction();
        try {
            wallet.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void buyBtnCancelAction() {
        Wallet wallet = new Wallet();
        goQuitAction();
        try {
            wallet.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ObservableList<Coin> loadCheckBox() {
        ObservableList<Coin> coins = FXCollections.observableArrayList(CoinController.getCoinController().read());
        return coins;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        buyCbBoxCoin.setItems(loadCheckBox());
        buyCbBoxCoinOut.setItems(loadCheckBox());
    }
}
