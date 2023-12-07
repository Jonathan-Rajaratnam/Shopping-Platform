import java.io.*;
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
        String productID;
        DateTime dt = new DateTime();
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
        do {
            System.out.print("\nEnter Product ID: ");
            productID = input.next();
        } while (checkProductID(productID));

        System.out.print("\nEnter Product Name: ");
        String productName = input.next();
        System.out.print("\nEnter Product Price: ");
        while (!input.hasNextDouble()) {
            System.out.println("Invalid input");
            System.out.print("\nEnter Product Price: ");
            input.next();
        }
        double productPrice = input.nextDouble();
        System.out.print("\nEnter Number of Available Items: ");
        int noOfAvailableItems = input.nextInt();
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
        } else {
            System.out.print("\nEnter Size: ");
            String size = input.next();
            System.out.print("\nEnter Material: ");
            String material = input.next();
            Clothing c = new Clothing(productID, productName, noOfAvailableItems, productPrice, size, material);
            productList.add(c);
        }


    }

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

    @Override
    public void printProductList() {
        for (Product p : productList) {
            System.out.println(p.toString() + "\n");
        }

    }


    @Override
    public void saveProductList() {
        try {
            FileOutputStream fos = new FileOutputStream("productList.txt");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(productList);
            System.out.println("\nProduct list saved");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * This method should be called when the program starts
     * and the product list should be loaded from the file
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
            e.printStackTrace();
        }
    }


    public void menu() throws FileNotFoundException {
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
                GUI gui = new GUI();
                gui.screen();
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
