package model;

import java.io.Serializable;

public class Coin implements Serializable {

    private String extId;
    private String name;
    private double price;

    public Coin(String name, double price) {
        super();
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Coin{" +
                "extId='" + extId + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    public String getExtId() {
        return extId;
    }

    public void setExtId(String extId) {
        this.extId = extId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
