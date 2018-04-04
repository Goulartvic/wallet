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

    public Map<String, Double> createWallet() {

        Map<String, Double> wallet = new HashMap<>();

        for (int i = 0; i < read().size(); i++) {
            wallet.put(read().get(i).getExtId(), read().get(i).getPrice() * 0);
        }

        return wallet;
    }

    public void depositReal(User user, double value) {
        user.getWallet().replace("1", value);
        Instant timeNow = Instant.now();
        String history = user.getName() + " - Depósito: R$" + value + " - Data: " + timeNow.toString();
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

            Instant timeNow = Instant.now();
            String history = user.getName() + " - Venda De: " + value + " " + coinOut.getName() + " Para: " + coinOutToCoinIn + " " + coinIn.getName() + " - Data: " + timeNow.toString();
            user.getHistory().add(history);
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

    public boolean verifyCoinExistance(String extendedId) {
        if (CoinDao.getCoinDao().get(extendedId) != null) {
            return true;
        }
        return false;
    }

    // Continue from here warning!

}
