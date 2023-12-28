import javax.swing.*;

public class CartGUI extends JFrame {

    private static CartGUI instance;


    JPanel cartPanel = new JPanel();
    JTable productsInCart = new JTable();

    private CartGUI() {
        setTitle("Shopping Cart");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(500, 500);
        setVisible(true);

    }

    public static CartGUI getInstance() {
        if (instance == null) {
            instance = new CartGUI();
        }
        return instance;
    }

    public void cartDetails() {

    }


}
