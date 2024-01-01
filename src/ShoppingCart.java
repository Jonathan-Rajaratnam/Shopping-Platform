import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ShoppingCart {

    private static ShoppingCart instance;
    boolean sameType = false;
    private ArrayList<Product> products = new ArrayList<>();

    public ShoppingCart() {
    }

    public static ShoppingCart getInstance() {
        if (instance == null) {
            instance = new ShoppingCart();
        }
        return instance;
    }

    public Map<Product, Integer> getProductCount() {
        Map<Product, Integer> productCount = new HashMap<>();
        for (Product p : products) {
            if (productCount.containsKey(p)) {
                productCount.put(p, productCount.get(p) + 1);
            } else {
                productCount.put(p, 1);
            }
        }
        return productCount;
    }


    public void addToCart(Product p) {
        //TODO: Check if there are available number of products in the productList and then add them
        //to the cart and minus the number of products from the productList
        products.add(p);
    }

    public void removeFromCart(Product p) {
        products.remove(p);
    }

    public double calculateTotal() {
        double total = 0;
        for (Product p : products) {
            total += p.getPrice();
        }
        return total;
    }

    public boolean checkSameType() {
        //TODO: if 3 or more products of same type is added add a 20% discount
        return sameType;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }
}
