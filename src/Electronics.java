import java.io.Serial;
import java.io.Serializable;

public class Electronics extends Product implements Serializable {

    @Serial
    private static final long serialVersionUID = -1844162502314195720L;


    private String brand;
    //Assumption is made as the warranty period is given in weeks
    private String warrantyPeriod;


    public Electronics(String productID, String productName, int noOfAvailableItems, double price, String brand, int warrantyPeriod) {
        super(productID, productName, noOfAvailableItems, price);
        this.brand = brand;
        this.warrantyPeriod = warrantyPeriod + " weeks";
    }

    /**
     * This method returns the brand of the electronic product.
     *
     * @return brand
     */
    public String getBrand() {
        return brand;
    }

    /**
     * This method sets the brand of the electronic product.
     *
     * @param brand of the electronic product
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * This method returns the warranty period of the electronic product.
     *
     * @return warrantyPeriod
     */
    public String getWarrantyPeriod() {
        return warrantyPeriod;
    }

    /**
     * This method sets the warranty period of the electronic product.
     *
     * @param warrantyPeriod of the electronic product
     */
    public void setWarrantyPeriod(String warrantyPeriod) {
        this.warrantyPeriod = warrantyPeriod;
    }

    @Override
    public String toString() {
        return "Type: Electronics \n" +
                super.toString() +
                "\nBrand: " + brand +
                "\nWarranty Period: " + warrantyPeriod;
    }

    public String getWarranty() {
        return warrantyPeriod;
    }

    @Override
    public String getInfo() {
        return "Brand: " + brand + "\nWarranty Period: " + warrantyPeriod;
    }
}
