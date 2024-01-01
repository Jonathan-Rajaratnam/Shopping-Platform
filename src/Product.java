import java.io.Serial;
import java.io.Serializable;

public abstract class Product implements Serializable, Comparable<Product> {

    @Serial
    private static final long serialVersionUID = -8404540139549563256L;

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

    public String getInfo() {
        return "Generic Product";
    }

    @Override
    public String toString() {
        return "Product ID: " + productID +
                "\nProduct Name: " + productName +
                "\nNo of Available Items: " + noOfAvailableItems +
                "\nPrice: " + price;
    }

    @Override
    public int compareTo(Product o) {
        try {
            int productID1Int = Integer.parseInt(this.productID);
            int productID2Int = Integer.parseInt(o.productID);
            return productID1Int - productID2Int;

        } catch (NumberFormatException e) {
            return this.productID.compareTo(o.productID);
        }
        //return this.productID.compareTo(o.productID);
    }


//    public int compare(Product p1, Product p2) {
//        String productID1 = p1.getProductID();
//        String productID2 = p2.getProductID();
//        try {
//            int productID1Int = Integer.parseInt(productID1);
//            int productID2Int = Integer.parseInt(productID2);
//            return productID1Int - productID2Int;
//        } catch (NumberFormatException e) {
//            return productID1.compareTo(productID2);
//        }
//        return this.productID.compareTo(o.productID);
//
//    }


}
