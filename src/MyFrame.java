import javax.swing.*;
import java.awt.*;
public class MyFrame extends JFrame {
    private final CardLayout cardLayout;
    JPanel mainPanel;
    HomePanel homePanel;
    ProductsPanel productsPanel;
    MyFrame(){
        this.setTitle("Ship-it");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        cardLayout=new CardLayout();
        mainPanel=new JPanel(cardLayout);
        this.setSize(new Dimension(1080,600));
        homePanel=new HomePanel(this);
        mainPanel.add(homePanel,"HomePanel");
        productsPanel=new ProductsPanel(this);
        mainPanel.add(productsPanel,"ProductsPanel");
        mainPanel.setName("mainPanel");
        this.add(mainPanel);

        setLocationRelativeTo(null);
        this.setVisible(true);
    }
    public void switchToPanel(String panelName) {
        cardLayout.show(mainPanel, panelName);
    }
}

