import javax.swing.*;
import java.awt.*;

public class GUI extends JFrame {

    public void screen() {

        //Create and set up the window.
        JFrame frame = new JFrame("Westminster Shopping Center");

        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));


        //Add Label
        JLabel label = new JLabel("Select Product Category");
        label.setBounds(300, 100, 200, 50);
        frame.add(label);

        //Add shopping cart button
        JButton shoppingCartButton = new JButton("Shopping Cart");
        shoppingCartButton.setBounds(200, 500, 200, 30);
        frame.add(shoppingCartButton);

        //Create combo box
        String[] productCategory = {"All", "Clothing", "Electronics"};
        JComboBox comboBox = new JComboBox(productCategory);
        comboBox.setBounds(200, 150, 100, 30);
        frame.add(comboBox);


        frame.setSize(600, 700);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }
}
