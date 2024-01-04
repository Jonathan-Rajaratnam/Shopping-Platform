import java.util.*;

public class ShoppingCart {

    private static ShoppingCart instance;

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
        double total = 0.0;
        for (Product p : products) {
            total += p.getPrice();
        }
        return total;
    }

    /**
     * This method checks if there are 3 or more products of the same type in the cart
     */
    public double checkSameType() {
        double discount = 0.0;
        //I have used a HashSet here instead of a HashMap since I need to only count how many distinct products
        // of the same type are in the cart and didn't need the use of a key,val. A HashSet only allows unique values and does not allow duplicates.
        Set<String> electronics = new HashSet<>();
        Set<String> clothes = new HashSet<>();

        for (Product p : products) {
            if (p instanceof Electronics) {
                electronics.add(p.getProductID());
            } else if (p instanceof Clothing) {
                clothes.add(p.getProductID());
            }
        }

        if (electronics.size() >= 3 || clothes.size() >= 3) {
            discount = calculateTotal() * 0.2;
            System.out.println("You have 3 or more products of the same type in your cart");

        }
        return 0 - discount;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }
}
