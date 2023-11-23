public interface ShoppingManager {

    public abstract void addProduct(Product product);

    public abstract void removeProduct(String productID);

    public abstract void printProductList();

    public abstract void saveProductList();

    public abstract void loadProductList();

    public abstract void menu();
}
