import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        ShoppingManager shp = new WestminsterShoppingManager();
        shp.menu();
    }

}