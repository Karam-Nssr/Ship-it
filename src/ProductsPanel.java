import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductsPanel extends JPanel {
    private final MyFrame parentFrame;
    Products productsTree;
    JPanel productsPanel;
    Map<String,Integer> shipmentProducts=new HashMap<>();
    private List<Products> shipment=new ArrayList<>();
    ProductsPanel(MyFrame parentFrame){
        this.parentFrame=parentFrame;
        productsTree =new Products();
        productsTree.insert(5,"test1",100,1);
        productsTree.insert(2,"test2",100,7);
        productsTree.insert(4,"test3",100,2);
        productsTree.insert(8,"test4",100,5);
        productsTree.insert(0,"test5",100,3);
        productsTree.insert(10,"test6",100,6);
        productsTree.insert(1,"test7",100,3);
        productsTree.insert(3,"test8",100,3);
        productsTree.insert(6,"test9",100,2);
        productsTree.insert(0,"test10",100,4);
        productsTree.inOrder(productsTree.root);
        UI();
    }
    private void UI() {
        setLayout(new BorderLayout());
        setBackground(Color.decode("#D4E6F1"));
        JPanel toolbar=Toolbar();
        toolbar.setBackground(Color.decode("#B0C4DE"));
        add(toolbar,BorderLayout.NORTH);
        productsPanel = new JPanel(new BorderLayout());
        productsPanel.setBackground(Color.decode("#D4E6F1"));
        productsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(productsPanel,BorderLayout.CENTER);
        ButtonDesign addProduct=new ButtonDesign("Add Product");
        add(addProduct,BorderLayout.SOUTH);
        Refresh();
    }
    private void Refresh() {
        productsPanel.removeAll();
        ArrayList<Products> products = productsTree.getProducts(productsTree.root);
        JPanel displayPanel = new JPanel(new GridLayout(0, 3, 10, 10));
        displayPanel.setBackground(Color.decode("#D4E6F1"));
        for (Products product : products) {
            displayPanel.add(createProductPanel(product));
        }
        productsPanel.add(new JScrollPane(displayPanel), BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public JPanel createProductPanel(Products product){
        JPanel productCard=new JPanel();
        productCard.setBackground(Color.decode("#F0F8FF"));
        productCard.setLayout(new BoxLayout(productCard, BoxLayout.Y_AXIS));
        productCard.setBorder(BorderFactory.createLineBorder(Color.decode("#2874A6"), 2));

        productCard.add(Box.createRigidArea(new Dimension(0,10)));
        JLabel imageLabel = new JLabel();
        ImageIcon imageIcon = new ImageIcon("./src/Berserk-Necklace.jpg");
        Image foodImage = imageIcon.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(foodImage));
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        productCard.add(imageLabel);

        JLabel productNameLabel = new JLabel(product.name);
        productNameLabel.setForeground(Color.decode("#2C3E50"));
        productNameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        productNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        productCard.add(productNameLabel);
        productCard.add(Box.createRigidArea(new Dimension(0,10)));

        JLabel productPriceLabel = new JLabel(" ($" + product.price + ")");
        productPriceLabel.setForeground(Color.decode("#2C3E50"));
        productPriceLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        productPriceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        productCard.add(productPriceLabel);
        productCard.add(Box.createRigidArea(new Dimension(0,5)));

        JLabel productQuantityLabel = new JLabel(" (" + product.id+") ");
        productQuantityLabel.setForeground(Color.decode("#2C3E50"));
        productQuantityLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        productQuantityLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        productCard.add(productQuantityLabel);
        productCard.add(Box.createRigidArea(new Dimension(0,5)));

        JLabel spinnerLabel=new JLabel("Select How Many You Want:");
        spinnerLabel.setForeground(Color.decode("#2C3E50"));
        spinnerLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        spinnerLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        productCard.add(Box.createRigidArea(new Dimension(0,5)));
        JSpinner spinner=new JSpinner(new SpinnerNumberModel(1,1,product.quantity,1));
        Dimension size = spinner.getPreferredSize();
        spinner.setModel(new SpinnerNumberModel(1, 1, product.quantity, 1));
        spinner.setPreferredSize(size);
        productCard.add(spinnerLabel);
        productCard.add(spinner);

        JButton addToOrderButton = ButtonDesign("Add to Shipment", () -> addToShipment(product,spinner));
        addToOrderButton.setBackground(Color.decode("#2874A6"));
        addToOrderButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        productCard.add(addToOrderButton);
        productCard.add(Box.createRigidArea(new Dimension(0,5)));

        /*JButton editButton = ButtonDesign("Edit", () -> showMealDialog(product));
        editButton.setBackground(Color.decode("#FFA500"));
        editButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        productCard.add(editButton);
        productCard.add(Box.createRigidArea(new Dimension(0,5)));
        JButton removeButton = ButtonDesign("Remove", () -> removeMeal(product));
        removeButton.setBackground(Color.decode("#FF4500"));
        removeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        productCard.add(removeButton);*/

        return  productCard;
    }

    private void addToShipment(Products product,JSpinner spinner){
        int count=((SpinnerNumberModel)spinner.getModel()).getNumber().intValue();
        for (int i=0;i<count;i++){
            shipmentProducts.put(product.name,count);
            shipment.add(product);
        }
        JOptionPane.showMessageDialog(this,count+" x "+product.name+" added to your Shipment!");
    }
    private JPanel Toolbar() {
        JPanel toolbar=new JPanel(new FlowLayout(FlowLayout.CENTER));
        toolbar.setBackground(Color.decode("#F5DEB3"));
        toolbar.add(ButtonDesign("View Products",this::showProducts));
        toolbar.add(ButtonDesign("Order",this::createOrderPanel));
        toolbar.add(ButtonDesign("View Shipments",this::showShipments));
        toolbar.add(ButtonDesign("Report", this::showReport));
        toolbar.add(ButtonDesign("Home",() -> parentFrame.switchToPanel("HomePanel")));
        return toolbar;
    }
    private JButton ButtonDesign(String text, Runnable action) {
        JButton button=new JButton(text);
        button.setForeground(Color.WHITE);
        button.setBackground(Color.decode("#2874A6"));
        button.setFont(new Font("Arial", Font.BOLD, 12));
        button.setFocusable(false);
        button.setBorder(BorderFactory.createLineBorder(Color.decode("#FFA500"), 1));
        button.addActionListener(e -> action.run());
        return button;
    }
    public void showProducts(){
        System.out.println("Nigga");
        System.out.println(shipmentProducts);
        for(Products p:shipment){
            System.out.println(p.name+" "+p.id);
        }
    }
    public void createOrderPanel(){
        System.out.println("Nigga");
    }
    public void showShipments(){
        System.out.println("Nigga");
    }
    public void showReport(){
        System.out.println("Nigga");
    }
}
