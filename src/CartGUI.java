import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CartGUI extends JFrame {

    private static CartGUI instance;

    JTable cartTable = new JTable();
    JPanel cartPanel = new JPanel();
    JPanel pricePanel = new JPanel();
    DecimalFormat df = new DecimalFormat("#.##");


    private CartGUI() {
        setTitle("Shopping Cart");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(600, 300);
        //cartDetails();
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

        //cartPanel.setLayout(new BorderLayout());
        cartPanel.setBorder(new EmptyBorder(20, 15, 20, 10)); // Top, left, bottom, right margins
        //cartPanel.setLayout(new BoxLayout(cartPanel, BoxLayout.Y_AXIS));

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
            data[i][2] = String.valueOf(df.format(product.getPrice() * productQuantities.get(productId)));
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
        productsInCart.setPreferredSize(new Dimension(500, 100));


        updatePricePanel();

        cartPanel.add(productsInCart, BorderLayout.NORTH);
        cartPanel.add(pricePanel, BorderLayout.CENTER);

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

    public void updatePricePanel() {


        ShoppingCart sc = ShoppingCart.getInstance();

        pricePanel.removeAll();
        pricePanel.setLayout(new BoxLayout(pricePanel, BoxLayout.Y_AXIS));

        JPanel pricePanel1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel pricePanel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JPanel pricePanel3 = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        Dimension rigidAreaDimension = new Dimension(0, 10);

        // Create JLabels with right-aligned text
        JLabel label1 = new JLabel("Total", SwingConstants.RIGHT);
        JLabel label2 = new JLabel(String.valueOf(df.format(sc.calculateTotal())), SwingConstants.RIGHT);
        JLabel label3 = new JLabel("Three Items in Same Category Discount (20%)", SwingConstants.RIGHT);
        JLabel label4 = new JLabel(String.valueOf(df.format(sc.checkSameType())), SwingConstants.RIGHT);
        JLabel label5 = new JLabel("Final Total", SwingConstants.RIGHT);
        JLabel label6 = new JLabel(String.valueOf(df.format(sc.calculateTotal() - sc.checkSameType())), SwingConstants.RIGHT);

        label5.setFont(new Font(label5.getFont().getName(), Font.BOLD, label5.getFont().getSize()));
        label6.setFont(new Font(label6.getFont().getName(), Font.BOLD, label6.getFont().getSize()));
        // Add labels to the panel
        pricePanel1.add(label1);
        pricePanel1.add(Box.createHorizontalStrut(20));
        pricePanel1.add(label2);
        pricePanel2.add(label3);
        pricePanel2.add(Box.createHorizontalStrut(20));
        pricePanel2.add(label4);
        pricePanel3.add(label5);
        pricePanel3.add(Box.createHorizontalStrut(20));
        pricePanel3.add(label6);

        pricePanel.add(pricePanel1);
        pricePanel.add(Box.createRigidArea(rigidAreaDimension));
        pricePanel.add(pricePanel2);
        pricePanel.add(Box.createRigidArea(rigidAreaDimension));
        pricePanel.add(pricePanel3);

    }
}
