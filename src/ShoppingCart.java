import java.util.ArrayList;

public class ShoppingCart {

    ArrayList<Product> cart = new ArrayList<>();

    public void addToCart(Product p) {
        //TODO: Check if there are available number of products in the productList and then add them
        //to the cart and minus the number of products from the productList
        cart.add(p);
    }

    public void removeFromCart(Product p) {
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
