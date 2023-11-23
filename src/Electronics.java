public class Electronics extends Product {

    private String brand;
    private String warrantyPeriod;


    public Electronics(String productID, String productName, int noOfAvailableItems, double price, String brand, String warrantyPeriod) {
        super(productID, productName, noOfAvailableItems, price);
        this.brand = brand;
        this.warrantyPeriod = warrantyPeriod;
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


}
