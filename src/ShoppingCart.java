import java.util.ArrayList;

public class ShoppingCart {

    ArrayList<Product> cart = new ArrayList<>();

    public void addProduct(Product p) {
        cart.add(p);
    }

    public void removeProduct(Product p) {
        cart.remove(p);
    }

    public double calculateTotal() {
        double total = 0;
        for (Product p : cart) {
            total += p.getPrice();
        }
        return total;
    }
}
