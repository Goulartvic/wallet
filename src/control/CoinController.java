package control;

import dao.CoinDao;
import interfaces.ICrud;
import model.Coin;
import model.User;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CoinController extends Controller implements ICrud {

    private static CoinController coinController;

    public CoinController() {
        super();
    }

    @Override
    public void initiate() {

    }

    public static void main(String[] args) {
//        Coin real = new Coin("Real", 1);
//        Coin putin = new Coin("Putin", 8.12);
//        Coin doge = new Coin("Doge", 3.5);
//        Coin marreta = new Coin("Marreta", 1.43);
//        Coin recayd = new Coin("Recayd", 4.09);
//        getCoinController().create(real);
//        getCoinController().create(putin);
//        getCoinController().create(doge);
//        getCoinController().create(marreta);
//        getCoinController().create(recayd);
//        User user = new User("Teste", "teste", "Teste", "TOMANOCU", getCoinController().createWallet());
//        UserController.getUserController().create(user);
        getCoinController().depositReal(UserController.getUserController().searchUser("Teste"), 200);
        Coin coinOut = getCoinController().searchCoin("1");
        Coin coinIN = getCoinController().searchCoin("3");
        getCoinController().exchange(UserController.getUserController().searchUser("Teste"), 100, coinOut, coinIN);
        System.out.println(UserController.getUserController().searchUser("Teste").getWallet());
        getCoinController().withdrawReal(UserController.getUserController().searchUser("Teste"), 80);
        System.out.println(UserController.getUserController().searchUser("Teste").getWallet());
        getCoinController().depositReal(UserController.getUserController().searchUser("Teste"), 300);
        System.out.println(UserController.getUserController().searchUser("Teste").getHistory());

    }

    @Override
    public void delete(String extendedId) {
        if (extendedId != null) {
            if (read().isEmpty()) {
                return;
            } else {
                CoinDao.getCoinDao().remove(extendedId);
            }
        }
    }

    @Override
    public void update(Object coin) {
        if (coin != null) {
            Coin cn = (Coin) coin;
            if (CoinDao.getCoinDao().get(cn.getExtId()) != null) {
                if (read().isEmpty()) {
                    return;
                } else {
                    CoinDao.getCoinDao().get(cn.getExtId()).setName(cn.getName());
                    CoinDao.getCoinDao().get(cn.getExtId()).setPrice(cn.getPrice());
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

    public void receiveData(String name, double price) {
        Coin coin = new Coin(name, price);
        create(coin);
    }

    public void updateData(String extendedId, String name, double price) {
        Coin coin = new Coin(name, price);
        coin.setExtId(extendedId);
        update(coin);
    }

    @Override
    public void create(Object coin) {
        if (coin != null) {
            Coin cn = (Coin) coin;
            if (read().isEmpty()) {
                cn.setExtId("1");
                CoinDao.getCoinDao().put(cn);
            } else {
                String lastId = read().get(read().size() - 1).getExtId();
                int lastIntId = Integer.parseInt(lastId);
                int newIntId = lastIntId + 1;
                String newId = Integer.toString(newIntId);
                cn.setExtId(newId);
                CoinDao.getCoinDao().put(cn);
            }
        }
    }

    // Continue from here warning!

    public Map<String, Double> createWallet() {

        Map<String, Double> wallet = new HashMap<>();

        for (int i = 0; i < read().size(); i++) {
            wallet.put(read().get(i).getExtId(), read().get(i).getPrice() * 0);
        }

        return wallet;
    }

    public void depositReal(User user, double value) {
        user.getWallet().replace("Real", value);
        Instant timeNow = Instant.now();
        String history = user.getName() + " - Depósito: R$" + value + " - Data:" + timeNow.toString();
        user.getHistory().add(history);
    }

    public void exchange(User user, double value, Coin coinOut, Coin coinIn) {
        if (value > user.getWallet().get(coinOut.getExtId())) {
            System.out.println("Falta dinheiro");
        } else {
            double walletCoinOut = user.getWallet().get(coinOut.getExtId()) - value;
            double walletCoinIn = user.getWallet().get(coinIn.getExtId());
            double coinOutToCoinIn = (value / coinIn.getPrice()) + walletCoinIn;

            user.getWallet().replace(coinOut.getExtId(), walletCoinOut);
            user.getWallet().replace(coinIn.getExtId(), coinOutToCoinIn);
        }
    }

    public void withdrawReal(User user, double value) {
        if (value <= user.getWallet().get("1")) {
            user.getWallet().replace("1", (user.getWallet().get("1") - value));
            Instant timeNow = Instant.now();
            String history = user.getName() + " - Retirada: R$" + value + " - Data:" + timeNow.toString();
            user.getHistory().add(history);
        } else {
            System.out.println("Falta dinheiro");
        }
    }

    public Coin searchCoin(String extendedId) {
        return CoinDao.getCoinDao().get(extendedId);
    }

}
