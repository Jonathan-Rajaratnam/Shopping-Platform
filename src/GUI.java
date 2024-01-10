import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GUI extends JFrame {

    private static GUI instance;
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

    private GUI() {
        JFrame frame = new JFrame("Westminster Shopping Centre");
        frame.setSize(750, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Add the panels to the frame
        frame.add(productPanelDes(), BorderLayout.NORTH);
        frame.add(productListingPanel, BorderLayout.CENTER);
        //frame.pack();
        frame.setVisible(true);

    }

    public static GUI getInstance() {
        if (instance == null) {
            instance = new GUI();
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
        productTable.setAutoResizeMode(JTable.AUTO_RESIZE_NEXT_COLUMN);
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames) {

            //This method will make the cells non-editable
            @Override
            public boolean isCellEditable(int row, int column) {

                return false;
            }
        };

        //DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        productTable.setModel(tableModel);

        //Calls my custom cell render to apply the colour to cells
        CellRenderer cellRenderer = new CellRenderer();

        //Set the header renderer to center align the header text
        cellRenderer.setHeaderRenderer(productTable);


        for (int i = 0; i < productTable.getColumnCount(); i++) {
            productTable.getColumnModel().getColumn(i).setCellRenderer(cellRenderer);
        }

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
                    viewProductInfo(productID); //pass it to the productInfo method

                }
//                else if (product == null) {
//                    //product = null;
//                    productListingPanel.removeAll();
//                    productListingPanel.revalidate();
//                    productListingPanel.repaint();
//                }
            }
        });


        JScrollPane productListScrollPane = new JScrollPane(productTable);
        productTable.setGridColor(Color.BLACK);

        productTable.getColumnModel().getColumn(0).setPreferredWidth(20);
        productTable.getColumnModel().getColumn(2).setPreferredWidth(50);
        productTable.getColumnModel().getColumn(3).setPreferredWidth(10);
        productTable.getColumnModel().getColumn(4).setPreferredWidth(150);
        //productListScrollPane.setPreferredSize(productTable.getPreferredSize());
        productListScrollPane.setPreferredSize(new Dimension(700, 100));
        return productListScrollPane;
    }


    private void viewProductInfo(String productID) {

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
        productListingPanel.removeAll();

        productListingPanel.setLayout(new BoxLayout(productListingPanel, BoxLayout.Y_AXIS));
        productInfoPanel.setLayout(new GridLayout(8, 1));


        JLabel productInfoHeader = new JLabel("Selected Product - Details");
        productInfoHeader.setFont(new Font(productInfoHeader.getFont().getName(), Font.BOLD, productInfoHeader.getFont().getSize()));

        // This was used previously but decided to use multiple JLabels instead
        // JTextArea productInfo = new JTextArea(6, 30);
        // productInfo.setEditable(false); //This will make the text area non-editable as JTextArea
        // is editable by default
        Border padding = BorderFactory.createEmptyBorder(10, 30, 10, 30);
        productInfoPanel.setBorder(padding);


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
                    colorLabel = new JLabel("Color: " + ((Clothing) p).getColour());
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
        //Left align the product info header label
        productInfoHeader.setAlignmentX(Component.LEFT_ALIGNMENT);
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

        //Add the AddToCart Button
        JPanel addToCartPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton addToCartButton = new JButton("Add to Shopping Cart");
        addToCartButton.addActionListener(new AddToCartListener());

        //addToCartButton.setEnabled(product == null || product.getNoOfAvailableItems() != 0);
        if (product != null && product.getNoOfAvailableItems() == 0) {
            addToCartButton.setEnabled(false);
            itemsAvailableLabel.setText("Items Available: Out of Stock");
        } else {
            addToCartButton.setEnabled(true);
        }
        addToCartPanel.add(addToCartButton);
        productListingPanel.add(productInfoPanel);
        productListingPanel.add(addToCartPanel);
        productListingPanel.revalidate();
        productListingPanel.repaint();


    }

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
            return ((Clothing) product).getSize() + ", " + ((Clothing) product).getColour();
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

        //Create or get the instance of WestminsterShoppingManager and load the product list
        WestminsterShoppingManager wsm = WestminsterShoppingManager.getInstance();
        productList = wsm.getProductList();

        //Print the size of the product list (for debugging purposes)
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
        //Create the JScrollPane for the table
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
        shoppingCartButton.addActionListener(new ShoppingCartListener());

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

        //Add the header, dropdown and table elements to the product panel
        productPanel.add(headerPanel);
        productPanel.add(dropdownPanel);
        productPanel.add(tablePanel);
        //productPanel.add(new JSeparator());

        return productPanel;

    }


    private class CellRenderer extends DefaultTableCellRenderer {

        DefaultTableCellRenderer renderer;

        public void setHeaderRenderer(JTable table) {
            renderer = (DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer();
            renderer.setHorizontalAlignment(JLabel.CENTER);
        }


        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {

            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            //System.out.println("does this work?");  //Yes it does

            if (table.getTableHeader().getColumnModel().getColumn(column).getHeaderValue().equals(value)) {
                ((JLabel) c).setHorizontalAlignment(JLabel.CENTER);
            }

            //This will center align the  data rows  in the Table
            ((JLabel) c).setHorizontalAlignment(JLabel.CENTER);


            //This will change the colour of the text to red if the number of items is less than 3
            String productID = (String) table.getModel().getValueAt(row, 0);
            Product product = findProductByID(productID);

            if (product != null && product.getNoOfAvailableItems() < 3) {
                c.setForeground(Color.RED); // Items less than 3
                if (isSelected) {
                    c.setForeground(table.getSelectionForeground()); // A macOS issue where due the highlight colour
                    // being red and the text colour for items less than 3 being red as well and hence the text will not be visible therefore
                    // I have set the text colour to the selection foreground colour which when selected will turn white
                }
            } else {
                if (isSelected) {
                    c.setForeground(table.getSelectionForeground());
                } else {
                    c.setForeground(table.getForeground());
                }
            }

            return c;
        }


        /**
         * This method finds the product in the productList based on the product ID and returns the product object
         * which will then be used to get product info
         *
         * @param productID of the product
         * @return Product object
         */
        private Product findProductByID(String productID) {
            for (Product p : productList) {
                if (p.getProductID().equals(productID)) {
                    return p;
                }
            }
            return null;
        }
    }

    /**
     * This method adds an event listener to the dropdown menu
     */
    private class DropdownListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            @SuppressWarnings("unchecked") //This was added as the type will always be a JComboBox<String>
            JComboBox<String> comboBox = (JComboBox<String>) e.getSource();
            String selectedProduct = String.valueOf(comboBox.getSelectedItem());
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

    /**
     * This method adds an event listener to the shopping cart button
     */
    private class ShoppingCartListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Shopping cart button clicked");
            CartGUI cartGUI = CartGUI.getInstance();
            cartGUI.cartDetails();
            cartGUI.setVisible(true);


        }
    }

    private class AddToCartListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println("Add to cart button clicked");
            System.out.println("Selected product: " + product);
            ShoppingCart cart = ShoppingCart.getInstance();
            if (product.getNoOfAvailableItems() > 0) {
                cart.addToCart(product);
                viewProductInfo(product.getProductID());
//                if (product.getNoOfAvailableItems() == 0) {
//                    productList.remove(product);
//                    createTable(type);
//                    product = null;
//                    productListingPanel.removeAll();
//                    productListingPanel.revalidate();
//                    productListingPanel.repaint();
//                }

            }
        }
    }
}
