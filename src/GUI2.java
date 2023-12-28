import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUI2 extends JFrame {

    private static GUI2 instance;
    ArrayList<Product> productList = new ArrayList<>();
    JPanel productPanel = new JPanel();
    JPanel productListingPanel = new JPanel();
    JPanel productInfoPanel = new JPanel();
    JPanel headerPanel = new JPanel();
    JPanel dropdownPanel = new JPanel();
    JPanel tablePanel = new JPanel();
    JTable productTable = new JTable();

    String type = "All";

    private Product product = null;

    private GUI2() {
        JFrame frame = new JFrame("WestMinster Shopping Center");
        frame.setSize(620, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //productListPanel.setBackground(Color.lightGray);

        // Add the panels to the frame
        frame.add(productPanelDes(), BorderLayout.NORTH);
        frame.add(productListingPanel, BorderLayout.CENTER);
        //frame.pack();
        frame.setVisible(true);

    }

    public static GUI2 getInstance() {
        if (instance == null) {
            instance = new GUI2();
        }
        return instance;
    }

    /**
     * This creates a JScrollPane that wil contain the table of products
     *
     * @param data        Contains the data to be filled in the table
     * @param columnNames contains the column headers
     * @return JScrollPane which contains the table
     */
    private JScrollPane getjScrollPane(String[][] data, String[] columnNames) {
        productTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {

            //This method will make the cells non-editable
            @Override
            public boolean isCellEditable(int row, int column) {

                return false;
            }
        };

        //DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        productTable.setModel(tableModel);

        productTable.getSelectedRow();
        productTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        productTable.getSelectionModel().addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                int selectedRow = productTable.getSelectedRow();
                if (selectedRow != -1) {
                    //System.out.println("Selected row: " + selectedRow);
                    //System.out.println("Selected product ID: " + productTable.getValueAt(selectedRow, 0));
                    //Get the product ID of the selected row
                    String productID = (String) productTable.getValueAt(selectedRow, 0);

                    //Get the product object from the productList array
                    //This will be used to add to the cart
                    for (Product p : productList) {
                        if (p.getProductID().equals(productID)) {
                            product = p;
                            break;
                        }
                    }
                    productInfo(productID); //pass it to the productInfo method

                } else {
                    product = null;
                    productListingPanel.removeAll();
                    productListingPanel.revalidate();
                    productListingPanel.repaint();
                }
            }
        });


        JScrollPane productListScrollPane = new JScrollPane(productTable);
        productTable.setGridColor(Color.BLACK);

        productTable.getColumnModel().getColumn(4).setPreferredWidth(200);
        productListScrollPane.setPreferredSize(new Dimension(600, 100));
        return productListScrollPane;
    }


    private void productInfo(String productID) {

        boolean flagCloth = false;
        boolean flagElectronics = false;

        JLabel productIDLabel = new JLabel();
        JLabel categoryLabel = new JLabel();
        JLabel brandLabel = new JLabel();
        JLabel nameLabel = new JLabel();
        JLabel warrantyLabel = new JLabel();
        JLabel itemsAvailableLabel = new JLabel();
        JLabel sizeLabel = new JLabel();
        JLabel colorLabel = new JLabel();

        productInfoPanel.removeAll();


        JLabel productInfoHeader = new JLabel("Selected Product - Details");
        productInfoHeader.setFont(new Font(productInfoHeader.getFont().getName(), Font.BOLD, productInfoHeader.getFont().getSize()));

        // This was used previously but decided to use multiple JLabels instead
        // JTextArea productInfo = new JTextArea(6, 30);
        // productInfo.setEditable(false); //This will make the text area non-editable as JTextArea
        // is editable by default
        Border padding = BorderFactory.createEmptyBorder(10, 10, 10, 10);
        productListingPanel.setBorder(padding);


        for (Product p : productList) {
            if (p.getProductID().equals(productID)) {
                if (p instanceof Electronics) {
//                    productInfo = new JTextArea("\nProduct ID: " + productID +
//                            "\n\nCategory: " + "Electronics" +
//                            "\n\nBrand: " + ((Electronics) p).getBrand() +
//                            "\n\nName: " + p.getProductName() +
//                            "\n\nWarranty: " + ((Electronics) p).getWarrantyPeriod() +
//                            "\n\nItems Available: " + p.getNoOfAvailableItems())
                    productIDLabel = new JLabel("Product ID: " + productID);
                    categoryLabel = new JLabel("Category: " + "Electronics");
                    brandLabel = new JLabel("Brand: " + ((Electronics) p).getBrand());
                    nameLabel = new JLabel("Name: " + p.getProductName());
                    warrantyLabel = new JLabel("Warranty: " + ((Electronics) p).getWarrantyPeriod());
                    itemsAvailableLabel = new JLabel("Items Available: " + p.getNoOfAvailableItems());
                    flagElectronics = true;

                } else if (p instanceof Clothing) {

                    productIDLabel = new JLabel("Product ID: " + productID);
                    categoryLabel = new JLabel("Category: " + "Clothing");
                    nameLabel = new JLabel("Name: " + p.getProductName());
                    sizeLabel = new JLabel("Size: " + ((Clothing) p).getSize());
                    colorLabel = new JLabel("Color: " + ((Clothing) p).getColor());
                    itemsAvailableLabel = new JLabel("Items Available: " + p.getNoOfAvailableItems());
                    flagCloth = true;
//                    productInfo = new JTextArea("\n" +
//                            "Product ID: " + productID +
//                            "\n\nCategory: " + "Clothing" +
//                            "\n\nName: " + p.getProductName() +
//                            "\n\nSize: " + ((Clothing) p).getSize() +
//                            "\n\nColor: " + ((Clothing) p).getColor() +
//                            "\n\nItems Available: " + p.getNoOfAvailableItems());
                }
            }
        }
        //Add the product info labels to the productInfoPanel
        productInfoPanel.add(productInfoHeader);
        productInfoPanel.add(Box.createVerticalStrut(10));

        productInfoPanel.add(productIDLabel);
        productInfoPanel.add(Box.createVerticalStrut(10));
        productInfoPanel.add(categoryLabel);
        productInfoPanel.add(Box.createVerticalStrut(10));
        productInfoPanel.add(nameLabel);
        productInfoPanel.add(Box.createVerticalStrut(10));
        if (flagCloth) {
            productInfoPanel.add(sizeLabel);
            productInfoPanel.add(Box.createVerticalStrut(10));
            productInfoPanel.add(colorLabel);
            productInfoPanel.add(Box.createVerticalStrut(10));
        } else if (flagElectronics) {
            productInfoPanel.add(brandLabel);
            productInfoPanel.add(Box.createVerticalStrut(10));
            productInfoPanel.add(warrantyLabel);
            productInfoPanel.add(Box.createVerticalStrut(10));
        }
        productInfoPanel.add(itemsAvailableLabel);


        //productListingPanel.add(Box.createVerticalStrut(10));

        //Add the AddToCart Button
        JPanel addToCartPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton addToCartButton = new JButton("Add to Shopping Cart");
        addToCartButton.addActionListener(new addToCartListener());

//        JPanel productInfoPanelWrapper = new JPanel(new FlowLayout(FlowLayout.LEFT));
//        productInfoPanelWrapper.add(productInfoPanel);

        productInfoPanel.setLayout(new BoxLayout(productInfoPanel, BoxLayout.Y_AXIS));


        addToCartPanel.add(addToCartButton);
        productInfoPanel.add(addToCartPanel);
        productListingPanel.add(productInfoPanel);
        productListingPanel.revalidate();
        productListingPanel.repaint();


    }


    //private void addVGap(JLabel productIDLabel, JLabel categoryLabel, JLabel brandLabel, JLabel nameLabel) {

    /**
     * This method returns the product category of the product.
     *
     * @param product Which will be used to get the product category
     * @return Category of the product i.e. Electronics, Clothing or Generic
     */
    private String getProductCategory(Product product) {
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
    private String getProductInfo(Product product) {
        if (product instanceof Electronics) {
            return ((Electronics) product).getBrand() + ", " + ((Electronics) product).getWarranty() + " warranty";
        } else if (product instanceof Clothing) {
            return ((Clothing) product).getSize() + ", " + ((Clothing) product).getColor();
        } else {
            return "No info";
        }
    }


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
        //print Instance of wsm
        //System.out.println("Instance of wsm: " + wsm);
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


        JScrollPane productListScrollPane = getjScrollPane(data, columnNames);

        tablePanel.add(productListScrollPane, BorderLayout.CENTER);

        tablePanel.revalidate();
        tablePanel.repaint();

        productPanel.add(tablePanel);
    }


    private JPanel productPanelDes() {
        productPanel.setLayout(new BoxLayout(productPanel, BoxLayout.Y_AXIS));

        // Create the header panel for shopping cart button
        headerPanel.setLayout(new BorderLayout());

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
        productDropdown.addActionListener(new dropdownListener());

        //tablePanel.add(new JSeparator());


        //Add the header, dropdown and table elements to the product panel
        productPanel.add(headerPanel);
        productPanel.add(dropdownPanel);
        productPanel.add(tablePanel);
        //productPanel.add(new JSeparator());


        return productPanel;

    }

    /**
     * This method creates the main frame of the GUI
     */
//    public void mainFrame() {
//        JFrame frame = new JFrame("WestMinster Shopping Center");
//        frame.setSize(620, 600);
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        //productListPanel.setBackground(Color.lightGray);
//
//        // Add the panels to the frame
//        frame.add(productPanelDes(), BorderLayout.NORTH);
//        frame.add(productListingPanel, BorderLayout.CENTER);
//        //frame.pack();
//        frame.setVisible(true);
//
//    }

    /**
     * This method adds an event listener to the dropdown menu
     */
    private class dropdownListener implements ActionListener {
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
            CartGUI cartGUI = CartGUI.getInstance();
            cartGUI.setVisible(true);

        }
    }

    private class addToCartListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Add to cart button clicked");
            System.out.println("Selected product: " + product);
            ShoppingCart cart = new ShoppingCart();
            cart.addToCart(product);

            //TODO: Add the selected product to the shopping cart

        }
    }
}
