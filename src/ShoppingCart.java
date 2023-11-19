import java.util.ArrayList;
public class ShoppingCart {

    ArrayList<Product> cart = new ArrayList<Product>();

    public void addProduct(Product p){
        cart.add(p);
    }

    public void removeProduct(Product p){
        cart.remove(p);
    }

    public void calculateTotal(){
        double total = 0;
        for(Product p : cart){
            total += p.getPrice();
        }
        System.out.println("Total: " + total);
    }
}
