package Order;

import java.io.Serializable;

public class Item  implements Serializable {
    private String name;
    private int amount;
    private double price;

    public Item(String name, int amount, double price) {
        this.name = name;
        this.amount = amount;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double calculateValue(){
        return this.amount * this.price;
    }

    public double calculateValueWithDiscount(){
        if (this.amount > 4 && this.amount < 10){
            return this.calculateValue() * 0.95;
        } if (this.amount > 9 && this.amount < 20){
            return this.calculateValue() * 0.9;
        } if (this.amount > 19){
            return this.calculateValue() * 0.85;
        } else {
            return this.calculateValue();
        }
    }

    @Override
    public java.lang.String toString() {
        return this.name + new String(" ").repeat(20 - this.name.length()) +
                this.price + "zł" + new String(" ").repeat(10) +
                this.amount + " szt.    " + calculateValue() + "zł" + " (rabat " +
                Math.round((1 - (calculateValueWithDiscount()/calculateValue())) * 100) + "%)";
    }
}
