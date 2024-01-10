import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class WestminsterShoppingManager implements ShoppingManager {

    public static ArrayList<Product> productList = new ArrayList<>();
    private static WestminsterShoppingManager instance;
    int maxNoOfProducts = 50;

    public static synchronized WestminsterShoppingManager getInstance() {
        if (instance == null) {
            instance = new WestminsterShoppingManager();
        }
        return instance;
    }

    @Override
    public void addProduct() {
        Scanner input = new Scanner(System.in);
        String productID;
        int warrantyPeriod;
        String brand;

        boolean isTrue = true;
        int userInput = 0;
        while (isTrue) {
            try {
                System.out.print("Select product Category: 1. Electronics 2. Clothes: ");
                //System.out.flush();
                userInput = input.nextInt();
                if (userInput == 1 || userInput == 2) {
                    isTrue = false;
                } else {

                    System.out.println("Invalid choice");
                    //System.out.println();
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid choice");
            }

        }
        //input.next();
        //Check if the product entered already exits in the array
        do {
            System.out.print("\nEnter Product ID: ");
            productID = input.next();
        } while (checkProductID(productID));

        //Get other info for the product
        System.out.print("\nEnter Product Name: ");
        String productName = input.next();
        System.out.print("\nEnter Product Price: ");

        //Check if the price input is a valid double/int
        while (!input.hasNextDouble()) {
            System.out.println("Invalid input");
            System.out.print("\nEnter Product Price: ");
            input.next();
        }
        double productPrice = input.nextDouble();
        System.out.print("\nEnter Number of Available Items: ");
        int noOfAvailableItems = input.nextInt();

        //If user selected Electronics as the product
        if (userInput == 1) {
            System.out.print("\nEnter Brand: ");
            brand = input.next();
            System.out.print("\nEnter Warranty Period: ");
            while (!input.hasNextInt()) {
                System.out.println("Invalid input");
                System.out.print("\nEnter Warranty Period: ");
                input.next();
            }
            warrantyPeriod = input.nextInt();
//            while (true) {
//                try {
//                    System.out.print("\nEnter Warranty Period: ");
//                    warrantyPeriod = input.nextInt();
//                    break;
//
//                } catch (InputMismatchException e) {
//                    System.out.println("Invalid input");
//                }
//            }
            Electronics e = new Electronics(productID, productName, noOfAvailableItems, productPrice, brand, warrantyPeriod);
            productList.add(e);

            // If user selected Clothing as the product
        } else {
            System.out.print("\nEnter Size: ");
            String size = input.next();
            System.out.print("\nEnter Material: ");
            String material = input.next();
            Clothing c = new Clothing(productID, productName, noOfAvailableItems, productPrice, size, material);
            productList.add(c);
        }


    }

    /**
     * This method checks if the product ID entered by the user already exists in the array
     *
     * @param productID The product ID entered by the user
     * @return true if the product ID already exists in the array, false if it doesn't
     */
    public boolean checkProductID(String productID) {
        boolean isPresent = false;
        for (Product p : productList) {
            if (p.getProductID().equals(productID)) {
                System.out.println("Product ID already exists");
                isPresent = true;
            } else {
                isPresent = false;
            }
        }
        return isPresent;
    }

    public ArrayList<Product> getProductList() {
        for (Product p : productList) {
            System.out.println(p.toString());
        }
        return productList;
    }

    /**
     * This method removes a product from the system based on the product ID entered by the user
     */
    @Override
    public void removeProduct() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Product ID: ");
        String productID = input.nextLine();
        for (Product p : productList) {
            if (p.getProductID().equals(productID)) {
                productList.remove(p);
                //Print the product that was removed with the type of product
                if (p instanceof Electronics) {
                    System.out.println("Product removed: Type: Electronics" + p);
                } else {
                    System.out.println("Product removed: Type: Clothing " + p);
                }
                System.out.println("\nNo of products in the list now: " + productList.size());
                break;
            } else {
                System.out.println("Product not found");
            }
        }

        //productList.removeIf(p -> p.getProductID().equals(productID));


    }

    /**
     * This method prints the product list in alphabetical order of product ID
     */
    @Override
    public void printProductList() {
        for (Product p : productList) {
            System.out.println(p.toString() + "\n");
        }
    }

    /**
     * This method should be called when the program is exiting
     * and the product list should be saved to the file
     * This uses the java serialization API tp write and read objects to and from a file
     */
    @Override
    public void saveProductList() {
        try {
            FileOutputStream fos = new FileOutputStream("productList.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(productList);
            System.out.println("\nProduct list saved");

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * This method should be called at the start each time the program starts
     * and the product list should be loaded from the file into the array
     */
    @Override
    public void loadProductList() {

        try {
            FileInputStream fis = new FileInputStream("productList.txt");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Object readObject = ois.readObject();
            if (readObject instanceof ArrayList) {
                productList = (ArrayList<Product>) readObject;
                System.out.println("\nProduct list loaded");
            } else {
                System.out.println("\nInvalid file format");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }


    /**
     * This method displays the menu to the user and calls the appropriate method based on the
     * user's choice
     */
    public void menu() {
        Scanner input = new Scanner(System.in);
        //Scanner input2 = new Scanner(System.in);
        System.out.println("\n1. Add Product");
        System.out.println("2. Delete Product");
        System.out.println("3. Print Product List");
        System.out.println("4. Save Product List");
        System.out.println("5. Load Product List");
        System.out.println("6. Open GUI");
        System.out.println("7. Exit");


        System.out.print("Enter your choice: ");
        String choice = input.nextLine();

        switch (choice) {

            case "1":
                //Performs a check to see ff the maximum number of products which is 50 has been reached
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
//                GUI2 gui = new GUI2();
//                gui.mainFrame();
                System.out.println("Instance of WSM is " + instance);
                GUI.getInstance();
                break;
            case "7":
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice");
                menu();
                break;
        }


    }
}
