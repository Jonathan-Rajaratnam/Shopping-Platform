import java.io.FileNotFoundException;

public interface ShoppingManager {

    public abstract void addProduct();
    //TODO: ask if the user wants to add a new product or update an existing query

    public abstract void removeProduct();

    public abstract void printProductList();

    public abstract void saveProductList();

    public abstract void loadProductList() throws FileNotFoundException;

    public abstract void menu() throws FileNotFoundException;
}
