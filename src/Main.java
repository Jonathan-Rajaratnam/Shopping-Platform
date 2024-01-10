import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        ShoppingManager shp = WestminsterShoppingManager.getInstance();
        shp.loadProductList();
        shp.menu();
    }

}