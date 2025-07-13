package Problem1.View;
import javax.swing.*;
import java.awt.*;

public class HomePanel extends JPanel {
    private final MyFrame parentFrame;
    ButtonDesign viewProductsButton;
    ButtonDesign viewShipmentsButton;

    HomePanel(MyFrame parentFrame){
        this.parentFrame = parentFrame;
        setLayout(new GridBagLayout());
        setBackground(Color.decode("#D4E6F1"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 100, 200, 100);

        JLabel nameLabel = new JLabel(new ImageIcon(new ImageIcon("./src/Problem1/View/pics/Shipit-1.png").getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        add(nameLabel, gbc);

        viewProductsButton = new ButtonDesign("View Products",this::productAction);
        viewShipmentsButton = new ButtonDesign("View Shipments",this::shipmentAction);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(viewProductsButton, gbc);

        JLabel imageLabel = new JLabel(new ImageIcon(new ImageIcon("./src/Problem1/View/pics/Logo.png").getImage().getScaledInstance(200, 200, Image.SCALE_SMOOTH)));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(imageLabel, gbc);

        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        add(viewShipmentsButton, gbc);

    }
   public void productAction(){
       viewProductsButton.addActionListener(e -> parentFrame.switchToPanel("ProductsPanel"));

   }
    public void shipmentAction(){
        viewShipmentsButton.addActionListener(e -> parentFrame.switchToPanel("ShipmentsPanel"));
    }
}
