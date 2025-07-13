package Problem1.View;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import Problem1.Model.* ;


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
        productsTree.inOrder(productsTree.getRoot());
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

        Refresh();
    }
    private void Refresh() {
        productsPanel.removeAll();
        ArrayList<Products> products = productsTree.getProducts(productsTree.getRoot());
        JPanel displayPanel = new JPanel(new GridLayout(0, 3, 10, 10));
        displayPanel.setBackground(Color.decode("#D4E6F1"));
        for (Products product : products) {
            displayPanel.add(createProductPanel(product));
        }
        ButtonDesign addProduct=new ButtonDesign("Add Product",this::showProductDialog);
        productsPanel.add(addProduct,BorderLayout.SOUTH);
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
        ImageIcon imageIcon = new ImageIcon("./src/Problem1/View/pics/Berserk-Necklace.jpg");
        Image foodImage = imageIcon.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(foodImage));
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        productCard.add(imageLabel);

        JLabel productNameLabel = new JLabel(product.getName());
        productNameLabel.setForeground(Color.decode("#2C3E50"));
        productNameLabel.setFont(new Font("Arial", Font.BOLD, 16));
        productNameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        productCard.add(productNameLabel);
        productCard.add(Box.createRigidArea(new Dimension(0,10)));

        JLabel productPriceLabel = new JLabel(" ($" + product.getPrice() + ")");
        productPriceLabel.setForeground(Color.decode("#2C3E50"));
        productPriceLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        productPriceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        productCard.add(productPriceLabel);
        productCard.add(Box.createRigidArea(new Dimension(0,5)));

        JLabel productQuantityLabel = new JLabel(" (" + product.getId()+") ");
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
        JSpinner spinner=new JSpinner(new SpinnerNumberModel(1,1,product.getQuantity(),1));
        Dimension size = spinner.getPreferredSize();
        spinner.setModel(new SpinnerNumberModel(1, 1, product.getQuantity(), 1));
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
            shipmentProducts.put(product.getName(),count);
            shipment.add(product);
        }
        JOptionPane.showMessageDialog(this,count+" x "+product.getName()+" added to your Shipment!");
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
        if (productsTree==null ) {
            //productsTree=Products.getProducts();
            JOptionPane.showMessageDialog(this, "Sorry,No Products are available.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        Refresh();
    }
    private void showProductDialog() {
        System.out.println("Nigga");
    }
    private void createOrderPanel() {
        productsPanel.removeAll();

        JPanel orderPanel = new JPanel(new BorderLayout());
        orderPanel.setBackground(Color.decode("#F0F8FF"));
        orderPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel orderLabel = new JLabel("Your Shipment:");
        orderLabel.setFont(new Font("Arial", Font.BOLD, 18));
        orderLabel.setHorizontalAlignment(SwingConstants.CENTER);
        orderPanel.add(orderLabel, BorderLayout.NORTH);

        JPanel mealListPanel = new JPanel(new GridLayout(0, 1, 10, 10));
        mealListPanel.setBackground(Color.LIGHT_GRAY);

        double totalPrice = 0;
        for (Products product : shipment) {
            mealListPanel.add(createOrderedProductPanel(product));
            totalPrice += product.getPrice();
        }

        JScrollPane productScrollPane = new JScrollPane(mealListPanel);
        productScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        productScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        orderPanel.add(productScrollPane, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new BoxLayout(bottomPanel, BoxLayout.Y_AXIS));
        bottomPanel.setBackground(Color.decode("#F0F8FF"));

        JLabel totalPriceLabel = new JLabel("Products Price: $" + totalPrice);
        totalPriceLabel.setFont(new Font("Arial", Font.BOLD, 16));
        totalPriceLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        bottomPanel.add(totalPriceLabel);
        bottomPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JLabel shipmentTypeLabel = new JLabel("Shipment Type:");
        shipmentTypeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        shipmentTypeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        bottomPanel.add(shipmentTypeLabel);

        /*JComboBox<Order.enOrderType> orderTypeComboBox = new JComboBox<>(Order.enOrderType.values());
        orderTypeComboBox.setMaximumSize(new Dimension(200, 30));
        orderTypeComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        bottomPanel.add(orderTypeComboBox);*/

        JLabel locationLabel = new JLabel("Shipment Location:");
        locationLabel.setFont(new Font("Arial", Font.BOLD, 16));
        locationLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextField locationField = new JTextField(20);
        locationField.setMaximumSize(new Dimension(300, 30));

        JLabel shippingDateLabel = new JLabel("Shipment's Date:");
        shippingDateLabel.setFont(new Font("Arial", Font.BOLD, 16));
        shippingDateLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JTextField shipmentDate = new JTextField(20);
        shipmentDate.setMaximumSize(new Dimension(300, 30));

        bottomPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        bottomPanel.add(locationLabel);
        bottomPanel.add(locationField);

        bottomPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        bottomPanel.add(shippingDateLabel);
        bottomPanel.add(shipmentDate);

        double finalTotalPrice = totalPrice;
        JButton submitButton = ButtonDesign("Submit Order", () -> {
            //Order.enOrderType orderType = (Order.enOrderType) orderTypeComboBox.getSelectedItem();
            String location = locationField.getText();
            String date=shipmentDate.getText();
            if(date.trim().isEmpty()){
                JOptionPane.showMessageDialog(this,"Please enter a valid Date.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            /*if (orderType==Order.enOrderType.Delivery && location.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this,"Please enter a delivery location.", "Error", JOptionPane.ERROR_MESSAGE);
            } */else {
                //assert orderType != null;
                System.out.println("Ordered "+shipmentProducts);
                /*Order o;
                o=new Order(shipmentProducts,finalTotal,orderType,Order.enOrderStatus.Preparing, employee.get_UserName(),date,location);

                Order.SaveToFile(o);
                orders.add(o);*/
                JOptionPane.showMessageDialog(this,"Shipment Submitted!\n"
                        //+ "Type: " + shipmentType +"\n"
                        + "Location: " + location +"\n"
                        + "Total Price: $" + finalTotalPrice +"\n"
                        + "Shipping Date: "+date, "Success", JOptionPane.INFORMATION_MESSAGE);
                shipment.clear();
                Refresh();
            }
        });
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        bottomPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        bottomPanel.add(submitButton);
        orderPanel.add(bottomPanel, BorderLayout.SOUTH);
        productsPanel.add(orderPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
    private JPanel createOrderedProductPanel(Products product) {
        JPanel productPanel=new JPanel();
        productPanel.setLayout(new BoxLayout(productPanel,BoxLayout.X_AXIS));
        productPanel.setBackground(Color.decode("#FFF5EE"));
        productPanel.setBorder(BorderFactory.createLineBorder(Color.decode("#8B4513"), 2));

        JLabel productNameLabel = new JLabel(product.getName()+" - $"+product.getPrice());
        productNameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        productPanel.add(productNameLabel);

        JButton removeButton = ButtonDesign("Remove", () -> {
            shipment.remove(product);
            createOrderPanel();
        });
        removeButton.setBackground(Color.decode("#FF6347"));
        productPanel.add(Box.createHorizontalGlue());
        productPanel.add(removeButton);

        return productPanel;
    }
    public void showShipments(){
        System.out.println("Nigga");
    }
    public void showReport(){
        System.out.println("Nigga");
    }
}
