package model;

import java.io.Serializable;
import java.util.*;

public class User implements Serializable {

    private int userId;
    private String login;
    private String password;
    private List<Wallet> wallet;
    private List<String> history;

    public User(String login, String password, List<Wallet> wallet) {
        super();
        this.login = login;
        this.password = password;
        this.wallet = wallet;
        this.history = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
    public List<Wallet> getWallet() {
        return wallet;
    }

    public void setWallet(List<Wallet> wallet) {
        this.wallet = wallet;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getHistory() {
        return history;
    }


}
