package dao;

import java.util.ArrayList;

public interface DaoInterface<E> {
    void create(E e);

    void update(E e);

    void delete(String e);

    ArrayList<E> read();
}
