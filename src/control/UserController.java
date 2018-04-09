package control;

import dao.UserDao;
import dao.DaoInterface;
import model.User;
import model.Wallet;

import java.util.*;

public class UserController implements DaoInterface {

    private static UserController userController;
    private static User sessionUser;

    @Override
    public void create(Object user) {
        if (user != null) {
            User cn = (User) user;
            if (read().isEmpty()) {
                cn.setUserId(1);
                UserDao.getUserDao().put(cn);
            } else {
                int lastId = read().get(read().size() - 1).getUserId();
                int newId = lastId + 1;
                cn.setUserId(newId);
                UserDao.getUserDao().put(cn);
            }
        }
    }

    @Override
    public void delete(String login) {
        if (login != null) {
            if (read().isEmpty()) {
                return;
            } else {
                UserDao.getUserDao().remove(login);
            }
        }
    }

    public void updatePersist(){
        UserDao.getUserDao().persist();
    }

    @Override
    public void update(Object user) {
        if (user != null) {
            User cn = (User) user;
            if (UserDao.getUserDao().get(cn.getLogin()) != null) {
                if (read().isEmpty()) {
                    return;
                } else {
                    UserDao.getUserDao().get(cn.getLogin()).setPassword(cn.getPassword());
                    UserDao.getUserDao().persist();
                }
            }
        }
    }

    @Override
    public ArrayList<User> read() {
        return new ArrayList<User>(UserDao.getUserDao().getList());
    }

    public static UserController getUserController() {
        if (userController == null) {
            userController = new UserController();
        }
        return userController;
    }

    public static void setCoinController(UserController userController) {
        UserController.userController = userController;
    }

    public static User getSessionUser() {
        return sessionUser;
    }

    public static void setSessionUser(User sessionUser) {
        UserController.getUserController().sessionUser = sessionUser;
    }

    public boolean authenticateUser(String login, String password) {
        if (UserDao.getUserDao().get(login) != null) {
            if (read().isEmpty()) {
                return false;
            } else {
                if (UserDao.getUserDao().get(login).getPassword().equals(password)) {
                    setSessionUser(UserDao.getUserDao().get(login));
                    return true;
                }
            }
        }
        return false;
    }

    public Wallet getCoinWallet(String nome) {
        for (int i = 0; i < sessionUser.getWallet().size(); i++) {
            if (sessionUser.getWallet().get(i).getCoinName().equals(nome)) {
                return sessionUser.getWallet().get(i);
            }
        }
        return null;
    }

    public List<Wallet> createWallet() {
        List<Wallet> wallet = new ArrayList<>();
        Wallet ww;
        for (int i = 0; i < CoinController.getCoinController().read().size(); i++) {
            ww = new Wallet(CoinController.getCoinController().read().get(i).getName(), 0);
            wallet.add(ww);
        }
        return wallet;
    }
}
