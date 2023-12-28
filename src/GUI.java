import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {

    JPanel panel = new JPanel();

    JPanel view = new JPanel();
    JPanel productListing = new JPanel();

    public void screen() {

        // Create and set up the window.
        JFrame frame = new JFrame("Westminster Shopping Center");

        // Set the layout of the main frame
        frame.setLayout(new BorderLayout());

//        this.getContentPane().add(panel, BorderLayout.CENTER);
//        panel.setLayout(new GridLayout(2, 1));
//        panel.add(view);
//        panel.add(productListing);
//
//        view.setBackground(Color.blue);
//        productListing.setBackground(Color.red);


        // Create a panel for the label and dropdown
        JPanel labelPanel = new JPanel();
        labelPanel.setLayout(new FlowLayout());

        //Add shopping cart button to the EAST position of the main frame
        JButton shoppingCartButton = new JButton("Shopping Cart");
        view.add(shoppingCartButton, BorderLayout.EAST);
        //frame.add(shoppingCartButton, BorderLayout.NORTH);
        shoppingCartButton.setPreferredSize(new Dimension(100, 30));

        // Add Label
        JLabel label = new JLabel("Select Product Category");
        labelPanel.add(label);

        // Create combo box
        String[] productCategory = {"All", "Clothing", "Electronics"};
        JComboBox comboBox = new JComboBox(productCategory);
        labelPanel.add(comboBox);

        // Add the labelPanel to the NORTH position of the main frame
        frame.add(labelPanel, BorderLayout.CENTER);


        // Set frame properties
        frame.setSize(600, 700);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
