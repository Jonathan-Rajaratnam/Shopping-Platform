import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class WestminsterShoppingManager implements ShoppingManager {

    int maxNoOfProducts = 50;

    ArrayList<Product> productList = new ArrayList<>();

    public WestminsterShoppingManager() {
    }

    @Override
    public void addProduct() {
        Scanner input = new Scanner(System.in);

        boolean isTrue = true;
        int userInput = 0;
        while (isTrue) {
            try {
                System.out.print("Select product Category: 1. Electronics 2. Clothes: ");
                System.out.flush();
                userInput = input.nextInt();
                if (userInput == 1 || userInput == 2) {
                    isTrue = false;
                } else {

                    System.err.println("Invalid choice");
                    System.err.flush();
                    //System.out.println();
                }
            } catch (InputMismatchException e) {
                System.err.println("Invalid choice");
                System.err.flush();
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
            productList.add(e);
        } else {
            System.out.println("Enter Size: ");
            String size = input.nextLine();
            System.out.println("Enter Material: ");
            String material = input.nextLine();
            Clothing c = new Clothing(productID, productName, noOfAvailableItems, productPrice, size, material);
            productList.add(c);
        }


    }

    @Override
    public void removeProduct() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Product ID: ");
        String productID = input.nextLine();
        for (Product p : productList) {
            if (p.getProductID().equals(productID)) {
                productList.remove(p);
                //print the product that was removed with the type of product
                if (p instanceof Electronics) {
                    System.out.println("Product removed: Type: Electronics" + p.toString());
                } else {
                    System.out.println("Product removed: Type: Clothing " + p.toString());
                }
                break;
            } else {
                System.out.println("Product not found");
            }
        }

        //productList.removeIf(p -> p.getProductID().equals(productID));


    }

    @Override
    public void printProductList() {
        for (Product p : productList) {
            System.out.println(p.toString());
        }

    }

    @Override
    public void saveProductList() {
        //TODO: save the product list to a file


    }

    /**
     * TODO: load the product list from a file
     * This method should be called when the program starts
     * and the product list should be loaded from the file
     */
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
                if (productList.size() == maxNoOfProducts) {
                    System.out.println("Product List is full");
                    System.out.println("Please delete a product to add a new product");
                } else {
                    addProduct();
                }
                menu();
                break;
            case "2":
                removeProduct();
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
