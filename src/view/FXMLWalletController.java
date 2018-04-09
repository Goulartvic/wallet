package view;

import control.UserController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Wallet;

import java.net.URL;
import java.util.ResourceBundle;

public class FXMLWalletController implements Initializable {


    @FXML
    private Button walletDepositBtn;

    @FXML
    private MenuItem menuWallet;

    @FXML
    private TableView<Wallet> walletTable;

    @FXML
    private TableColumn<Wallet, String> coinColumn;

    @FXML
    private TableColumn<Wallet, Double> valueColumn;

    @FXML
    private Button walletBtnSell;

    @FXML
    private MenuBar menuBar;

    @FXML
    private MenuItem menuSell;

    @FXML
    private MenuItem menuQuotation;

    @FXML
    private MenuItem menuDeposit;

    @FXML
    private Button walletBtnBuy;

    @FXML
    private Button walletCashWithBtn;

    @FXML
    public void goQuitAction() {
        view.Wallet.getStage().close();
    }

    @FXML
    public void goWalletAction() {
        view.Wallet wallet = new view.Wallet();
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
    void walletDepositBtnAction(ActionEvent event) {
        CashDeposit deposit = new CashDeposit();
        goQuitAction();
        try {
            deposit.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void walletCashWithBtnAction(ActionEvent event) {
        CashWithdrawal withdrawal = new CashWithdrawal();
        goQuitAction();
        try {
            withdrawal.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void walletBtnBuyAction(ActionEvent event) {
        ExchangeCoin exchangeCoin = new ExchangeCoin();
        goQuitAction();
        try {
            exchangeCoin.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        coinColumn.setCellValueFactory(new PropertyValueFactory<>("coinName"));
        valueColumn.setCellValueFactory(new PropertyValueFactory<>("coinValue"));
        walletTable.setItems(FXCollections.observableList(UserController.getSessionUser().getWallet()));
    }
}
