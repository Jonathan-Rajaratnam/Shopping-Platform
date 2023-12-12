import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUI2 extends JFrame {

    ArrayList<Product> productList = new ArrayList<>();

    JPanel productListPanel = new JPanel();
    JPanel productPanel = new JPanel();
    JPanel headerPanel = new JPanel();
    JPanel dropdownPanel = new JPanel();
    JPanel tablePanel = new JPanel();

    String type = "All";


    /**
     * This method returns the product category of the product.
     *
     * @param product Which will be used to get the product category
     * @return Category of the product i.e. Electronics, Clothing or Generic
     */
    private static String getProductCategory(Product product) {
        if (product instanceof Electronics) {
            return "Electronics";
        } else if (product instanceof Clothing) {
            return "Clothing";
        } else {
            return "Generic";
        }
    }

    /**
     * This method returns the product info of the product.
     *
     * @param product Which will be used to get the product info
     * @return Info which contains additional attributes of the product
     */
    private static String getProductInfo(Product product) {
        if (product instanceof Electronics) {
            return ((Electronics) product).getBrand() + ", " + ((Electronics) product).getWarranty() + " warranty";
        } else if (product instanceof Clothing) {
            return ((Clothing) product).getSize() + ", " + ((Clothing) product).getColor();
        } else {
            return "No info";
        }
    }


    public void main(String[] args) {
        JFrame frame = new JFrame("WestMinster Shopping Center");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        productPanel.setLayout(new BoxLayout(productPanel, BoxLayout.Y_AXIS));

        // Create the header panel for shopping cart button
        headerPanel.setLayout(new BorderLayout(5, 5));

        //Create the dropdown panel for the dropdown menu to select products
        dropdownPanel.setLayout(new FlowLayout());

        //Create the Shopping Cart button and place it on the top right
        JButton shoppingCartButton = new JButton("Shopping Cart");
        headerPanel.add(shoppingCartButton, BorderLayout.EAST);

        //Add event listener to the shopping cart button
        shoppingCartButton.addActionListener(new shoppingCartListener());

        //Table creation
        createTable("All");

        // Select product dropdown
        JLabel selectProductLabel = new JLabel("Select product:");
        String[] productCategory = {"All", "Clothing", "Electronics"};
        JComboBox<String> productDropdown = new JComboBox<>(productCategory);
        dropdownPanel.add(selectProductLabel);
        dropdownPanel.add(productDropdown);

        //Add event listener to the dropdown menu
        productDropdown.addActionListener(new DropdownListener());


        //Add the elements to the product panel
        productPanel.add(headerPanel);
        productPanel.add(dropdownPanel);
        productPanel.add(tablePanel);


        // Add the panels to the frame
        frame.add(productPanel, BorderLayout.NORTH);
        frame.add(productListPanel, BorderLayout.CENTER);

        //Make the frame visible
        frame.pack();
        frame.setVisible(true);
    }


    //Create a table to display the product list

    /**
     * This method creates a table to display the product list
     */
    private void createTable(String type) {

        //Remove all the elements from the table panel
        tablePanel.removeAll();

        //Add a table containing the product List
        tablePanel.setLayout(new FlowLayout());

        //String[] columnNames = {"Product Name", "Product Price", "Product Quantity",};
        String[] columnNames = {"Product ID", "Product Name", "Category", "Price", "Info"};


        //Create a table to display the product list (This is an improper way)
        //WestminsterShoppingManager wsm2 = new WestminsterShoppingManager();
        //wsm2.loadProductList();

        //Initialize the productList array from WestminsterShoppingManager
        productList = WestminsterShoppingManager.productList;
        System.out.println("Product list size: " + productList.size());

        //Create a new arraylist to store the products based on the type selected
        ArrayList<Product> productTypeList = new ArrayList<>();
        switch (type) {
            case "All" -> productTypeList = productList;
            case "Clothing" -> {
                for (Product p : productList) {
                    if (p instanceof Clothing) {
                        productTypeList.add(p);
                    }
                }
            }
            case "Electronics" -> {
                for (Product p : productList) {
                    if (p instanceof Electronics) {
                        productTypeList.add(p);
                    }
                }
            }
        }

        //Create the rows and columns of the table
        String[][] data = new String[productTypeList.size()][5];
        for (int i = 0; i < productTypeList.size(); i++) {
            data[i][0] = productTypeList.get(i).getProductID();
            data[i][1] = productTypeList.get(i).getProductName();
            data[i][2] = getProductCategory(productTypeList.get(i));
            data[i][3] = Double.toString(productTypeList.get(i).getPrice());
            data[i][4] = getProductInfo(productTypeList.get(i));

        }

        JTable productTable = new JTable();
        TableModel tableModel = new DefaultTableModel(data, columnNames);

        productTable.setModel(tableModel);

        JScrollPane productListScrollPane = new JScrollPane(productTable);
        productTable.setGridColor(Color.BLACK);
        productListScrollPane.setPreferredSize(new Dimension(500, 200));

        tablePanel.add(productListScrollPane, BorderLayout.CENTER);

        tablePanel.revalidate();
        tablePanel.repaint();

        productListPanel.add(tablePanel);
    }

    /**
     * This method adds an event listener to the dropdown menu
     */
    private class DropdownListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JComboBox<String> comboBox = (JComboBox<String>) e.getSource();
            String selectedProduct = (String) comboBox.getSelectedItem();
            switch (selectedProduct) {
                case "All" -> {
                    System.out.println("All products selected");
                    type = "All";
                }
                case "Clothing" -> {
                    System.out.println("Clothing selected");
                    type = "Clothing";
                }
                case "Electronics" -> {
                    System.out.println("Electronics selected");
                    type = "Electronics";
                }
            }
            System.out.println(selectedProduct);
            createTable(type);
        }

    }

    //When shopping cart button is clicked, view shopping cart in new window
    private class shoppingCartListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Shopping cart button clicked");
            JFrame shoppingCartFrame = new JFrame("Shopping Cart");
            shoppingCartFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
            shoppingCartFrame.setSize(600, 700);
            shoppingCartFrame.setVisible(true);

        }
    }
}
