import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.util.ArrayList;

public class GUI2 extends JFrame {

    JPanel productListPanel = new JPanel();
    JPanel productPanel = new JPanel();
    JPanel headerPanel = new JPanel();
    JPanel dropdownPanel = new JPanel();

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

        // Select product dropdown
        JLabel selectProductLabel = new JLabel("Select product:");
        String[] productCategory = {"All", "Clothing", "Electronics"};
        JComboBox<String> productDropdown = new JComboBox<>(productCategory);
        dropdownPanel.add(selectProductLabel);
        dropdownPanel.add(productDropdown);

        //Add a table containing the product List
        String[] columnNames = {"Product Name", "Product Price", "Product Quantity"};

        //Add the elements to the product panel
        productPanel.add(headerPanel);
        productPanel.add(dropdownPanel);

        createTable();


        // Add the panels to the frame
        frame.add(productPanel, BorderLayout.NORTH);
        frame.add(productListPanel, BorderLayout.CENTER);
        //frame.add(productListScrollPane, BorderLayout.CENTER);

        frame.pack();
        frame.setVisible(true);
    }

    //Create a table to display the product list
    private void createTable() {

        WestminsterShoppingManager wsm = new WestminsterShoppingManager();
        ArrayList<Product> productList = wsm.getProductList();

        String[][] data = new String[productList.size()][5];
        for (int i = 0; i < productList.size(); i++) {
            data[i][0] = productList.get(i).getProductID();
            data[i][1] = productList.get(i).getProductName();
            data[i][2] = getProductCategory(productList.get(i));
            data[i][3] = Double.toString(productList.get(i).getPrice());
            data[i][4] = getProductInfo(productList.get(i));
        }
        String[] columnNames = {"Product ID", "Product Name", "Category", "Price", "Info"};
        //String[][]

        JTable productTable = new JTable();
        TableModel tableModel = new DefaultTableModel(data, columnNames);

        productTable.setModel(tableModel);

        JScrollPane productListScrollPane = new JScrollPane(productTable);
        productTable.setGridColor(Color.BLACK);
        productListScrollPane.setPreferredSize(new Dimension(500, 500));

        productListPanel.add(productListScrollPane);

    }

}
