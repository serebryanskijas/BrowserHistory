import java.util.ArrayList;
import java.util.PriorityQueue;

//Очередь в магазин. Каждый покупатель имеет корзину, в каждой корзине набор товаров определенной цены.
//После обслуживания покупателя сумма в кассе увеличивается.
//Без очереди обслуживаются ветераны, затем дети до 10 лет
public class ShopQueue {
    static double sum = 0;

    public static void main(String[] args) {
        Product woter = new Product("Wotter", 2.22);
        Product broad = new Product("Broad", 1.11);
        Product oil = new Product("Oil", 3.33);
        Seller galya = new Seller();
        Buyer buyer1 = new Buyer(TypeCustomer.REGULAR);
        buyer1.addProduct(broad);
        Buyer buyer2 = new Buyer(TypeCustomer.CHILD);
        buyer2.addProduct(broad).addProduct(woter);
        Buyer buyer3 = new Buyer(TypeCustomer.VETERAN);
        buyer3.addProduct(oil).addProduct(woter).addProduct(broad);
        PriorityQueue<Buyer> queue = new PriorityQueue<>();
        queue.add(buyer1);
        queue.add(buyer2);
        queue.add(buyer3);
        while (!queue.isEmpty()) {
            Buyer buyer = queue.poll();
            sum += galya.calcSum(buyer);
            System.out.println(buyer.tCust + " набрал на - " + sum + " руб.");
        }
    }
}

class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

enum TypeCustomer {
    VETERAN, CHILD, REGULAR;
}

class Buyer implements Comparable {
    public TypeCustomer tCust;
    public ArrayList<Product> basket = new ArrayList<>();

    public Buyer(TypeCustomer tCust) {
        this.tCust = tCust;

    }

    @Override
    public int compareTo(Object o) {
        int index1 = this.tCust.ordinal();
        int index2 = ((Buyer) o).tCust.ordinal();
        return index1 - index2;
    }

    public Buyer addProduct(Product product) {
        basket.add(product);
        return this;
    }

}

class Seller {
    public double calcSum(Buyer buyer) {
        double sum = buyer.basket.stream().mapToDouble(p -> p.getPrice()).sum();
        return sum;
    }
}