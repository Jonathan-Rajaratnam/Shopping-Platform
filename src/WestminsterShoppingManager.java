import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class WestminsterShoppingManager implements ShoppingManager {

    ArrayList<Product> productList = new ArrayList<Product>();

    public WestminsterShoppingManager() {
    }

    ;

    @Override
    public void addProduct(Product product) {


    }

    @Override
    public void removeProduct(String productID) {
        productList.removeIf(p -> p.getProductID().equals(productID));


    }

    @Override
    public void printProductList() {
        for (Product p : productList) {
            System.out.println(p.toString());
        }

    }

    @Override
    public void saveProductList() {

    }

    @Override
    public void loadProductList() {

    }


    public void menu() {
        Scanner input = new Scanner(System.in);
        //Scanner input2 = new Scanner(System.in);
        System.out.println("1. Add Product");
        System.out.println("2. Delete Product");
        System.out.println("3. Print Product List");
        System.out.println("4. Save Product List");
        System.out.println("5. Load Product List");
        System.out.println("6. Exit");


        System.out.print("Enter your choice: ");
        String choice = input.nextLine();

        switch (choice) {

            case "1":
                boolean isTrue = true;
                int userInput = 0;
                while (isTrue) {
                    try {
                        System.out.print("Select product Category: 1. Electronics 2. Clothes: ");
                        userInput = input.nextInt();
                        if (userInput == 1 || userInput == 2) {
                            isTrue = false;
                        } else {

                            System.err.println("Invalid choice");
                            //System.out.println();
                        }
                    } catch (InputMismatchException e) {
                        System.err.println("Invalid choice");
                        input.next();
                    }

                }
                input.nextLine();
                System.out.println("Enter Product ID: ");
                String productID = input.nextLine();
                System.out.println("Enter Product Name: ");
                String productName = input.nextLine();
                System.out.println("Enter Product Price: ");
                double productPrice = input.nextDouble();
                System.out.println("Enter Number of Available Items: ");
                int noOfAvailableItems = input.nextInt();
                if (userInput == 1) {
                    System.out.println("Enter Brand: ");
                    String brand = input.nextLine();
                    System.out.println("Enter Warranty Period: ");
                    String warrantyPeriod = input.next();
                    Electronics e = new Electronics(productID, productName, noOfAvailableItems, productPrice, brand, warrantyPeriod);
                } else {
                    System.out.println("Enter Size: ");
                    String size = input.nextLine();
                    System.out.println("Enter Material: ");
                    String material = input.nextLine();
                    Clothing c = new Clothing(productID, productName, noOfAvailableItems, productPrice, size, material);
                }

                //Product p = new Product(productID, productName, productPrice);
                //productList.add(p);
                menu();
                break;
            case "2":
                System.out.println("Enter Product ID: ");
                String productID2 = input.nextLine();
                removeProduct(productID2);
                menu();
                break;
            case "3":
                printProductList();
                menu();
                break;
            case "4":
                saveProductList();
                menu();
                break;
            case "5":
                loadProductList();
                menu();
                break;
            case "6":
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice");
                menu();
                break;
        }


    }
}
