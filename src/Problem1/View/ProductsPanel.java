package Problem1.View;
import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import Problem1.Model.* ;
public class ProductsPanel extends JPanel {
    int id;
    private final MyFrame parentFrame;
    Products productsTree;
    Shipment shipmentTree;
    JPanel productsPanel;
    Map<String,Integer> shipmentProducts=new HashMap<>();
    private List<Products.Node> shipment=new ArrayList<>();
    private List<Shipment.Node> shipments=new ArrayList<>();
    ProductsPanel(MyFrame parentFrame){
        this.parentFrame=parentFrame;
        productsTree =new Products();
        shipmentTree=new Shipment();
        productsTree.insert(5,"test1",100,1,"./src/Problem1/View/pics/Berserk-Necklace.jpg");
        productsTree.insert(2,"test2",100,7,"./src/Problem1/View/pics/Berserk-Necklace.jpg");
        productsTree.insert(4,"test3",100,2,"./src/Problem1/View/pics/Berserk-Necklace.jpg");
        productsTree.insert(8,"test4",100,5,"./src/Problem1/View/pics/Berserk-Necklace.jpg");
        productsTree.insert(0,"test5",100,3,"./src/Problem1/View/pics/Berserk-Necklace.jpg");
        productsTree.insert(10,"test6",100,6,"./src/Problem1/View/pics/Berserk-Necklace.jpg");
        productsTree.insert(1,"test7",100,3,"./src/Problem1/View/pics/Berserk-Necklace.jpg");
        productsTree.insert(3,"test8",100,3,"./src/Problem1/View/pics/Berserk-Necklace.jpg");
        productsTree.insert(6,"test9",100,2,"./src/Problem1/View/pics/Berserk-Necklace.jpg");
        productsTree.insert(0,"test10",100,4,"./src/Problem1/View/pics/Berserk-Necklace.jpg");
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
        ArrayList<Products.Node> products = productsTree.getProducts(productsTree.getRoot());
        shipments = shipmentTree.getShipments(shipmentTree.getRoot());
        JPanel displayPanel = new JPanel(new GridLayout(0, 3, 10, 10));
        displayPanel.setBackground(Color.decode("#D4E6F1"));
        for (Products.Node product : products) {
            displayPanel.add(createProductPanel(product));
        }
        ButtonDesign addProduct=new ButtonDesign("Add Product",this::showProductDialog);
        productsPanel.add(addProduct,BorderLayout.SOUTH);
        productsPanel.add(new JScrollPane(displayPanel), BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    public JPanel createProductPanel(Products.Node product){
        JPanel productCard=new JPanel();
        productCard.setBackground(Color.decode("#F0F8FF"));
        productCard.setLayout(new BoxLayout(productCard, BoxLayout.Y_AXIS));
        productCard.setBorder(BorderFactory.createLineBorder(Color.decode("#2874A6"), 2));

        productCard.add(Box.createRigidArea(new Dimension(0,10)));
        JLabel imageLabel = new JLabel();
        ImageIcon imageIcon = new ImageIcon(product.getImagePath());
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

        JButton editButton = ButtonDesign("Edit", () -> editProduct(product));
        editButton.setBackground(Color.decode("#FFA500"));
        editButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        productCard.add(editButton);
        productCard.add(Box.createRigidArea(new Dimension(0,5)));
        JButton removeButton = ButtonDesign("Remove", () -> removeProduct(product));
        removeButton.setBackground(Color.decode("#FF4500"));
        removeButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        productCard.add(removeButton);
        productCard.add(Box.createRigidArea(new Dimension(0,5)));
        return  productCard;
    }

    private void addToShipment(Products.Node product,JSpinner spinner){
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
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.setBackground(Color.decode("#F0F8FF"));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel titleLabel = new JLabel("Add New Product");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setForeground(Color.decode("#2874A6"));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        inputPanel.add(titleLabel);
        inputPanel.add(Box.createRigidArea(new Dimension(0, 10)));

        JTextField nameField = new JTextField();
        JTextField idField = new JTextField();
        JTextField priceField = new JTextField();
        JTextField quantityField = new JTextField();
        JTextField imageField=new JTextField();

        nameField.setMaximumSize(new Dimension(300, 30));
        idField.setMaximumSize(new Dimension(300, 30));
        priceField.setMaximumSize(new Dimension(300, 30));
        quantityField.setMaximumSize(new Dimension(300, 30));
        imageField.setMaximumSize(new Dimension(300, 30));

        inputPanel.add(labeledField("Product Name:", nameField));
        inputPanel.add(labeledField("Product ID (integer):", idField));
        inputPanel.add(labeledField("Price (e.g. 99.99):", priceField));
        inputPanel.add(labeledField("Quantity (integer):", quantityField));
        inputPanel.add(labeledField("Image Path:",imageField));

        int result = JOptionPane.showConfirmDialog(
                this,
                inputPanel,
                "Add Product",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.PLAIN_MESSAGE
        );

        if (result == JOptionPane.OK_OPTION) {
            try {
                String name = nameField.getText().trim();
                int id = Integer.parseInt(idField.getText().trim());
                double price = Double.parseDouble(priceField.getText().trim());
                int quantity = Integer.parseInt(quantityField.getText().trim());
                String path=imageField.getText().trim();

                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Product name cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                productsTree.insert(id, name, price, quantity,path);
                JOptionPane.showMessageDialog(this, "Product added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                Refresh();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter valid numeric values for ID, Price, and Quantity.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private JPanel labeledField(String labelText, JTextField field) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.decode("#F0F8FF"));

        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        label.setForeground(Color.decode("#2C3E50"));
        label.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 0));

        panel.add(label, BorderLayout.NORTH);
        panel.add(field, BorderLayout.CENTER);
        panel.setMaximumSize(new Dimension(400, 60));

        return panel;
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
        for (Products.Node product : shipment) {
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

        String[] shipmentTypes = { "Important", "Special", "Normal" };
        JComboBox<String> shipmentTypeComboBox = new JComboBox<>(shipmentTypes);
        shipmentTypeComboBox.setMaximumSize(new Dimension(300, 30));
        shipmentTypeComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        shipmentTypeComboBox.setAlignmentX(Component.CENTER_ALIGNMENT);
        shipmentTypeComboBox.setBackground(Color.WHITE);
        shipmentTypeComboBox.setForeground(Color.decode("#2C3E50"));

        bottomPanel.add(shipmentTypeLabel);
        bottomPanel.add(Box.createRigidArea(new Dimension(0, 5)));
        bottomPanel.add(shipmentTypeComboBox);


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
        double finalTotalPrice1 = totalPrice;
        JButton submitButton = ButtonDesign("Submit Order", () -> {
            String shipmentType = (String) shipmentTypeComboBox.getSelectedItem();
            int priority=0;
            assert shipmentType != null;
            if(shipmentType.equals("Important")){
                priority=2;
            }
            if(shipmentType.equals("Special")){
                priority=1;
            }
            String location = locationField.getText();
            String date=shipmentDate.getText();
            if(date.trim().isEmpty()){
                JOptionPane.showMessageDialog(this,"Please enter a valid Date.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            if(location.trim().isEmpty()){
                JOptionPane.showMessageDialog(this,"Please enter a Location.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            System.out.println("Ordered "+shipmentProducts);
            shipmentTree.insert(id,  location, finalTotalPrice1,  date,priority,shipment);
            id++;
            JOptionPane.showMessageDialog(this,"Shipment Submitted!\n"
                    + "Type: " + shipmentType + "\n"
                    + "Location: " + location +"\n"
                    + "Total Price: $" + finalTotalPrice +"\n"
                    + "Shipping Date: "+date, "Success", JOptionPane.INFORMATION_MESSAGE);
            shipment.clear();
                Refresh();
        });
        submitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        bottomPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        bottomPanel.add(submitButton);
        orderPanel.add(bottomPanel, BorderLayout.SOUTH);
        productsPanel.add(orderPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }
    private JPanel createOrderedProductPanel(Products.Node product) {
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
    public void showShipments() {
        productsPanel.removeAll();

        JPanel shipmentPanel = new JPanel(new GridLayout(0, 2, 15, 15));
        shipmentPanel.setBackground(Color.decode("#D4E6F1"));
        shipmentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        ArrayList<Products.Node> products = productsTree.getProducts(productsTree.getRoot());
        if (shipments.isEmpty()) {
            JLabel noShipmentsLabel = new JLabel("No shipments available.");
            noShipmentsLabel.setFont(new Font("Arial", Font.BOLD, 16));
            noShipmentsLabel.setForeground(Color.decode("#2C3E50"));
            noShipmentsLabel.setHorizontalAlignment(SwingConstants.CENTER);
            productsPanel.add(noShipmentsLabel, BorderLayout.CENTER);
        } else {
            for (Shipment.Node node : shipments) {
                shipmentPanel.add(createShipmentCard(node));
            }
            JScrollPane scrollPane = new JScrollPane(shipmentPanel);
            scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            productsPanel.add(scrollPane, BorderLayout.CENTER);
        }
        revalidate();
        repaint();
    }
    private JPanel createShipmentCard(Shipment.Node shipment) {
        JPanel card = new JPanel();
        card.setLayout(new BoxLayout(card, BoxLayout.Y_AXIS));
        card.setBackground(Color.decode("#F0F8FF"));
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#2874A6"), 2),
                BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        card.add(Box.createRigidArea(new Dimension(0,10)));
        JLabel imageLabel = new JLabel();
        ImageIcon imageIcon = new ImageIcon("./src/Problem1/View/pics/shipment.png");
        Image shipmentImage = imageIcon.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(shipmentImage));
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(imageLabel);

        card.add(Box.createRigidArea(new Dimension(0,10)));
        JLabel title = new JLabel("Shipment ID: " + shipment.getId());
        title.setFont(new Font("Arial", Font.BOLD, 16));
        title.setForeground(Color.decode("#2C3E50"));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel location = new JLabel("Location: " + shipment.getLocation());
        location.setFont(new Font("Arial", Font.PLAIN, 14));
        location.setForeground(Color.decode("#2C3E50"));
        location.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel cost = new JLabel("Cost: $" + shipment.getCost());
        cost.setFont(new Font("Arial", Font.PLAIN, 14));
        cost.setForeground(Color.decode("#2C3E50"));
        cost.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel date = new JLabel("Date: " + shipment.getDate().toString());
        date.setFont(new Font("Arial", Font.ITALIC, 13));
        date.setForeground(Color.decode("#34495E"));
        date.setAlignmentX(Component.CENTER_ALIGNMENT);

        card.add(Box.createRigidArea(new Dimension(0,10)));
        String status;
        if(shipment.getPriority()==0){
            status="Normal";
        } else if (shipment.getPriority()==1) {
            status="Special";
        }
        else{
            status="Important";
        }
        JLabel statusLabel = new JLabel("Status: "+ status);
        statusLabel.setForeground(Color.decode("#2F4F4F"));
        statusLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        statusLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(statusLabel);
        card.add(Box.createRigidArea(new Dimension(0,5)));

        String[] shipmentTypes = { "Important", "Special", "Normal" };
        JComboBox<String> priorities=new JComboBox<>(shipmentTypes);
        priorities.setSelectedItem(shipment.getPriority());
        priorities.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(priorities);
        card.add(Box.createRigidArea(new Dimension(0,5)));

        shipmentTree.inOrder(shipmentTree.getRoot());
        JButton updateStatusButton =ButtonDesign("Update Status",() ->{
            String selectedStatus = (String) priorities.getSelectedItem();
            assert selectedStatus != null;
            shipment.setPriority(0);
            if(selectedStatus.equals("Important")){
                shipment.setPriority(2);
            }
            if(selectedStatus.equals("Special")){
                shipment.setPriority(1);
            }
            statusLabel.setText("Status: " + selectedStatus);
            JOptionPane.showMessageDialog(card, "Order status updated to " + selectedStatus, "Status Updated", JOptionPane.INFORMATION_MESSAGE);
        });
        updateStatusButton.setBackground(Color.decode("#32CD32"));
        updateStatusButton.setForeground(Color.WHITE);
        updateStatusButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        card.add(updateStatusButton);
        card.add(Box.createRigidArea(new Dimension(0,5)));

        card.add(title);
        card.add(Box.createRigidArea(new Dimension(0, 10)));
        card.add(location);
        card.add(cost);
        card.add(date);

        return card;
    }

    private void editProduct(Products.Node product) {
        String price = JOptionPane.showInputDialog(this, "Product's Price:", product != null ? product.getPrice() : "0");
        String quantitiy = JOptionPane.showInputDialog(this, "Product's Quantity:", product != null ? product.getQuantity() : "0");
        //String imagePath=JOptionPane.showInputDialog(this, "Product's Image Path:", product != null ? product.get_ImagePath() :"Product.jpg");
        if (price != null && quantitiy != null) {
            try {
                double priceValue = Double.parseDouble(price);
                int quantityValue = Integer.parseInt(quantitiy);

                if(priceValue!=product.getPrice() && quantityValue!=product.getQuantity()){
                    productsTree.Adjust(product.getId(),"Price & Quantity",priceValue,quantityValue);
                } else if (priceValue!=product.getPrice()) {
                    productsTree.Adjust(product.getId(),"Price",priceValue,quantityValue);
                }
                else if (quantityValue!=product.getQuantity()){
                    productsTree.Adjust(product.getId(),"Quantity",priceValue,quantityValue);
                } else{
                    productsTree.Adjust(product.getId(),"Price & Quantity",priceValue,quantityValue);
                }

                JOptionPane.showMessageDialog(this, "Meal saved successfully!");
                productsTree.inOrder(productsTree.getRoot());
                Refresh();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Invalid price or quantity format.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void removeProduct(Products.Node product) {
        int confirm=JOptionPane.showConfirmDialog(this,"Are you sure you want to remove " + product.getName() + "?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (confirm==JOptionPane.YES_OPTION) {
            productsTree.delete(product.getId());
            JOptionPane.showMessageDialog(this,product.getName()+" removed successfully.");
            Refresh();
        }
    }
}
