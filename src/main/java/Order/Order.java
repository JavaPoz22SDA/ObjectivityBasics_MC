package Order;

import java.io.*;
import java.util.Scanner;

public class Order implements Serializable {
    private int numberOfItems;
    private int maximumItems;
    private Item[] items;

    public Order() {
        this.maximumItems = 10;
        this.numberOfItems = 0;
        this.items = new Item[maximumItems];
    }

    public Order(int maximumItems) {
        this.maximumItems = maximumItems;
        this.numberOfItems = 0;
        this.items = new Item[maximumItems];
    }

    public void addItem(Item item) {
        boolean flag = false;
        if (numberOfItems < maximumItems) {
            if (numberOfItems != 0) {
                for (int i = 0; i < numberOfItems; i++) {
                    if (items[i].getName().equals(item.getName())) {
                        items[i].setAmount(items[i].getAmount() + item.getAmount());
                        flag = true;
                    }
                }
            }
            if (flag == false) {
                items[numberOfItems] = item;
                numberOfItems++;
            }
        } else {
            System.out.println("Maximum items in orders: " + maximumItems + ". You can't add new item.");
        }
    }

    public double[] orderValue() {
        double value = 0;
        double valueWithDiscount = 0;
        for (int i = 0; i < numberOfItems; i++) {
            value = value + items[i].calculateValue();
            valueWithDiscount = valueWithDiscount + items[i].calculateValueWithDiscount();
//            if (value != valueWithDiscount){
//                double discount = (1 - (items[i].calculateValueWithDiscount()/items[i].calculateValue())) * 100;
//                System.out.println(items[i].getName() + ": rabat " + Math.round(discount) + "%");
//            }
        }
        double[] values = new double[2];
        values[0] = value;
        values[1] = valueWithDiscount;
        return values;
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("\n").append("Zamówienie: \n");
        for (int i = 0; i < numberOfItems; i++) {
            stringBuilder.append(items[i].toString()).append("\n");
        }
        stringBuilder.append("Razem: " + orderValue()[0] + "\n").append("Rabat: " + (orderValue()[0] - orderValue()[1]) + "\n");
        stringBuilder.append("Razem (z rabatem): " + orderValue()[1]);
        return stringBuilder.toString();
    }

    public void cancelItem (int indeks){
        this.items[indeks - 1] = null;
        for (int i = indeks; i < numberOfItems; i++){
            items[i-1] = items[i];
            items[i] = null;
        }
        numberOfItems--;
    }

    public void editItem (int indeks){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Pozycja, którą chcesz zmienić: ");
        Item item = items[indeks - 1];
        item.toString();
        System.out.println("Podaj nową nazwę produktu.");
        item.setName(scanner.nextLine());
        System.out.println("Podaj nową ilość.");
        item.setAmount(scanner.nextInt());
        System.out.println("Podaj nową cenę.");
        item.setPrice(scanner.nextDouble());
    }

    public static void saveOrder (Order order, String fileName){
        try {
            ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(fileName));
            outputStream.writeObject(order);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Order readOrder (String fileName){
        Order order = null;
        try {
            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName));
            order = (Order) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return order;
    }

}