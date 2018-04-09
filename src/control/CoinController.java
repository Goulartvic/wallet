package control;

import dao.CoinDao;
import dao.DaoInterface;
import model.Coin;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoinController implements DaoInterface {

    private static CoinController coinController;
    private static double tax = 0.99;

    @Override
    public void delete(String name) {
        if (name != null) {
            if (read().isEmpty()) {
                return;
            } else {
                CoinDao.getCoinDao().remove(name);
            }
        }
    }

    @Override
    public void update(Object coin) {
        if (coin != null) {
            Coin cn = (Coin) coin;
            if (CoinDao.getCoinDao().get(cn.getName()) != null) {
                if (read().isEmpty()) {
                    return;
                } else {
                    CoinDao.getCoinDao().get(cn.getName()).setPrice(cn.getPrice());
                    CoinDao.getCoinDao().persist();
                }
            }
        }
    }

    @Override
    public ArrayList<Coin> read() {
        return new ArrayList<Coin>(CoinDao.getCoinDao().getList());
    }

    public static CoinController getCoinController() {
        if (coinController == null) {
            coinController = new CoinController();
        }
        return coinController;
    }

    public static void setCoinController(CoinController coinController) {
        CoinController.coinController = coinController;
    }

    @Override
    public void create(Object coin) {
        if (coin != null) {
            Coin cn = (Coin) coin;
            System.out.println(cn.getName() + " " + cn.getPrice());
            CoinDao.getCoinDao().put(cn);
        }
    }

    public Map<String, Double> createWallet() {

        Map<String, Double> wallet = new HashMap<>();
        List<Coin> coins = new ArrayList<>();

        for (int i = 0; i < read().size(); i++) {
            wallet.put(read().get(i).getName(), read().get(i).getPrice());
        }
        System.out.println(wallet);
        return wallet;
    }

    public void depositMoney(double value) {
        System.out.println(UserController.getUserController().getSessionUser());
        double total = UserController.getUserController().getCoinWallet("Real").getCoinValue() + value;
        UserController.getUserController().getCoinWallet("Real").setCoinValue(total);
        Instant timeNow = Instant.now();
        String history = UserController.getUserController().getSessionUser().getLogin() + " - Depósito: R$" + value + " - Data: " + timeNow.toString();
        UserController.getUserController().getSessionUser().getHistory().add(history);
        UserController.getUserController().updatePersist();
    }

    public void exchangeMoney(double value, Coin coinOut, Coin coinIn) {
        if (value > UserController.getUserController().getCoinWallet(coinOut.getName()).getCoinValue()) {
            System.out.println("Falta dinheiro " + coinOut.getName() + " " + coinIn.getName());
        } else {
            double walletCoinOut = UserController.getUserController().getCoinWallet(coinOut.getName()).getCoinValue() - value;
            double walletCoinIn = UserController.getUserController().getCoinWallet(coinIn.getName()).getCoinValue();
            double coinOutToCoinIn;
            if (coinIn.getPrice() < coinOut.getPrice()) {
                coinOutToCoinIn = ((value * coinOut.getPrice())/coinIn.getPrice()) + walletCoinIn;
            }
            else {
                coinOutToCoinIn = (value / coinIn.getPrice()) + walletCoinIn;
            }
            UserController.getUserController().getCoinWallet(coinOut.getName()).setCoinValue(walletCoinOut);
            UserController.getUserController().getCoinWallet(coinIn.getName()).setCoinValue(coinOutToCoinIn*tax);

            Instant timeNow = Instant.now();
            String history = UserController.getUserController().getSessionUser().getLogin() + " - Venda De: " + value + " " + coinOut.getName() + " Para: " + coinOutToCoinIn + " " + coinIn.getName() + " - Data: " + timeNow.toString();
            UserController.getUserController().getSessionUser().getHistory().add(history);
            UserController.getUserController().updatePersist();
        }
    }

    public void withdrawMoney(double value) {
        if (value <= UserController.getUserController().getCoinWallet("Real").getCoinValue()) {
            UserController.getUserController().getCoinWallet("Real").setCoinValue(UserController.getUserController().getCoinWallet("Real").getCoinValue() - value);
            Instant timeNow = Instant.now();
            String history = UserController.getUserController().getSessionUser().getLogin() + " - Retirada: R$" + value + " - Data: " + timeNow.toString();
            UserController.getUserController().getSessionUser().getHistory().add(history);
            UserController.getUserController().updatePersist();
        } else {
            System.out.println("Falta dinheiro!");
        }
    }
}
