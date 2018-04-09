package initialize;

import control.CoinController;
import model.Coin;

public class Initialize {

    private static Initialize init;

    public static void main(String[] args) {
        getInit().injetarCadastros();
    }

    public void injetarCadastros() {

        Coin real = new Coin("Real", 1);
        Coin omega = new Coin("Omega", 2.38);
        Coin danger = new Coin("Danger", 1.72);
        Coin crips = new Coin("Crips", 5.98);
        Coin mob = new Coin("M.O.B.", 4.09);

        CoinController.getCoinController().create(real);
        CoinController.getCoinController().create(omega);
        CoinController.getCoinController().create(danger);
        CoinController.getCoinController().create(crips);
        CoinController.getCoinController().create(mob);

    }

    public static Initialize getInit() {
        if (init == null) {
            init = new Initialize();
        }
        return init;
    }

    public static void setInit(Initialize init) {
        Initialize.init = init;
    }
}
