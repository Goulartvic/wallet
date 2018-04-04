package view;

import control.CoinController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Coin;

import java.net.URL;
import java.util.ResourceBundle;

public class FXMLQuotationController implements Initializable {

    @FXML
    private MenuBar menuBar;

    @FXML
    private TableView<Coin> tableQuotation;

    @FXML
    private TableColumn<Coin, Double> columnPrice;

    @FXML
    private TableColumn<Coin, String> columnCoin;

    @FXML
    private Button quoteBtnSell;

    @FXML
    private MenuItem menuSell;

    @FXML
    private MenuItem menuQuotation;

    @FXML
    private Button quoteBtnBuy;

    @FXML
    private MenuItem menuDeposit;

    @FXML
    private MenuItem menuWallet;


    public FXMLQuotationController() {

    }

    @FXML
    public void goQuitAction() {
        Quotation.getStage().close();
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
        BuyCoin buyCoin = new BuyCoin();
        goQuitAction();
        try {
            buyCoin.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void goSellAction() {
        SellCoin sellCoin = new SellCoin();
        goQuitAction();
        try {
            sellCoin.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void quoteBtnBuyAction() {
        BuyCoin buyCoin = new BuyCoin();
        goQuitAction();
        try {
            buyCoin.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void quoteBtnSellAction() {
        SellCoin sellCoin = new SellCoin();
        goQuitAction();
        try {
            sellCoin.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableList<Coin> loadTable() {
        ObservableList<Coin> coins = FXCollections.observableArrayList(CoinController.getCoinController().read());
        return coins;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        columnCoin.setCellValueFactory(
                new PropertyValueFactory<>("name"));
        columnPrice.setCellValueFactory(
                new PropertyValueFactory<>("price"));

        tableQuotation.setItems(loadTable());
    }
}
