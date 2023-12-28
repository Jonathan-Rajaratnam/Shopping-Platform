import java.io.Serializable;

public abstract class Product implements Serializable {

    private String productID = "Generic";
    private String productName;
    private int noOfAvailableItems;
    private double price;


    public Product(String productID, String productName) {
        this.productID = productID;
        this.productName = productName;

    }

    public Product(String productID, String productName, int noOfAvailableItems, double price) {
        this.productID = productID;
        this.productName = productName;
        this.noOfAvailableItems = noOfAvailableItems;
        this.price = price;
    }

    public String getProductID() {
        return productID;
    }


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getNoOfAvailableItems() {
        return noOfAvailableItems;
    }

    public void setNoOfAvailableItems(int noOfAvailableItems) {
        this.noOfAvailableItems = noOfAvailableItems;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product ID: " + productID +
                "\nProduct Name: " + productName +
                "\nNo of Available Items: " + noOfAvailableItems +
                "\nPrice: " + price;
    }

}
