import java.io.Serial;
import java.io.Serializable;

public class Clothing extends Product implements Serializable {

    @Serial
    private static final long serialVersionUID = -5434306555668546251L;
    private String size;
    private String colour;

    public Clothing(String productID, String productName) {
        super(productID, productName);
    }

    public Clothing(String productID, String productName, int noOfAvailableItems, double price,
                    String size, String color) {
        super(productID, productName, noOfAvailableItems, price);
        this.size = size;
        this.colour = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    @Override
    public String getInfo() {
        return "Size: " + size + "\nColor: " + colour;
    }

    @Override
    public String toString() {
        return "Type: Clothing \n" +
                super.toString() +
                "\nSize: '" + size +
                ", \nColor: '" + colour;
    }
}
