import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CartGUI extends JFrame {

    private static CartGUI instance;

    JTable cartTable = new JTable();
    JPanel cartPanel = new JPanel();

    private CartGUI() {
        setTitle("Shopping Cart");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(500, 500);

        cartDetails();
        add(cartPanel);
    }

    public static CartGUI getInstance() {
        if (instance == null) {
            instance = new CartGUI();
        }
        return instance;
    }

    public void printCart() {
        ShoppingCart shoppingCart = ShoppingCart.getInstance();
        System.out.println("Products in cart: ");
        for (Product p : shoppingCart.getProducts()) {
            System.out.println(p.getProductName());
        }
    }

    public void cartDetails() {

        cartPanel.removeAll();

        //Create JTable with products from ShoppingCart class
        ShoppingCart shoppingCart = ShoppingCart.getInstance();

        /*
        The use of a HashMap is more appropriate here than an ArrayList as it only allows unique keys which will be a
        product ID in this case. The value will be the quantity of that product in the cart. This avoids duplicate rows
        of the same product to show up in the cart table.

        Reference: https://www.geeksforgeeks.org/map-interface-java-examples/
        */
        Map<String, Integer> productQuantities = new HashMap<>();

        for (Product p : shoppingCart.getProducts()) {
            String key = p.getProductID();
            productQuantities.put(key, productQuantities.getOrDefault(key, 0) + 1);

        }


        //System.out.println("No of products in cart: " + shoppingCart.getProducts().size());
        String[] columnNames = {"Product", "Quantity", "Price"};
        String[][] data = new String[shoppingCart.getProducts().size()][3];

        int i = 0;
        for (String productId : productQuantities.keySet()) {
            Product product = findProductById(shoppingCart.getProducts(), productId);
            assert product != null;
            data[i][0] = product.getProductID() + "\n" + product.getProductName() + "\n" + product.getInfo();
            data[i][1] = String.valueOf(productQuantities.get(productId));
            data[i][2] = String.format("%.2f", product.getPrice() * productQuantities.get(productId));
            i++;
        }


        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        cartTable.setModel(model);
        cartTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        cartTable.setGridColor(Color.BLACK);


        JScrollPane productsInCart = new JScrollPane(cartTable);
        productsInCart.setPreferredSize(new Dimension(400, 100));
        cartPanel.add(productsInCart);

        cartPanel.revalidate();
        cartPanel.repaint();


    }

    private Product findProductById(ArrayList<Product> products, String productId) {
        for (Product product : products) {
            if (product.getProductID().equals(productId)) {
                return product;
            }
        }
        return null; // or handle this case as needed
    }
}
