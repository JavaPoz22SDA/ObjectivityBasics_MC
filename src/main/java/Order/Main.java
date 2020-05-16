package Order;


public class Main {
    public static void main(String[] args) {
        Item p1 = new Item("Chleb", 1, 3.5);
        System.out.println(p1);
        Item p2 = new Item("Cukier", 3, 4);
        System.out.print(p2);
        Item p3 = new Item("Masło", 10, 6);
        Order z = new Order(20);
        z.addItem(p1);
        z.addItem(p2);
        z.addItem(p3);
        System.out.println(z);
        Order.saveOrder(z,"order.bin");
        Order order = Order.readOrder("order.bin");
        System.out.println(order == z);
    }
}
