package model;

import java.io.Serializable;

public class Wallet implements Serializable {

    private String coinName;
    private double coinValue;

    public Wallet(String coinName, double coinValue) {
        this.coinName = coinName;
        this.coinValue = coinValue;
    }

    public String getCoinName() {
        return coinName;
    }

    public void setCoinName(String coinName) {
        this.coinName = coinName;
    }

    public double getCoinValue() {
        return coinValue;
    }

    public void setCoinValue(double coinValue) {
        this.coinValue = coinValue;
    }
}
