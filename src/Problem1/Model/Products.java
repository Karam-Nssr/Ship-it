package Problem1.Model;

import java.util.ArrayList;

public class Products {
    Node root;
    ArrayList<Node> products;
    AVLProduct avlProduct = new AVLProduct();

    public class Node {
        Node left, right;
        int id;
        String name;
        double price;
        int quantity;
        String imagePath;

        public Node(int id, String name, double price, int quantity,String imagePath) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.quantity = quantity;
            this.imagePath=imagePath;
            left = right = null;
        }
        public void SetNode(int id, String name, double price, int quantity){
            this.id = id;
            this.name = name;
            this.price = price;
            this.quantity = quantity;
        }
        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        public int getQuantity() {
            return quantity;
        }
        public String getImagePath(){
            return imagePath;
        }
    }
    public boolean LimitProducts(){
        int currSize = 0;
        for(Node node : products){
            currSize += node.quantity;
        }
        return currSize < 100;
    }


    public void insert(int id, String name, double price, int quantity,String imagePath) {
        avlProduct.insert(id, name, price, quantity);
        root = insert(root, id, name, price, quantity,imagePath);
    }

    private Node insert(Node root, int id, String name, double price, int quantity,String imagePath) {
        if (root == null) {
            root = new Node(id, name, price, quantity,imagePath);
            return root;
        }
        if (id < root.id) {
            root.left = insert(root.left, id, name, price, quantity,imagePath);
        } else if (id > root.id) {
            root.right = insert(root.right, id, name, price, quantity,imagePath);
        }
        return root;
    }

    public boolean search(int id){
        avlProduct.search(id);
        search(root, id);
        return root !=null;
    }
    Node search(Node node, int id) {
        if (node == null) {
            return null;
        }
        if (node.id == id) {
            return node;
        }
        if (id < node.id) {
            return search(node.left, id);
        } else {
            return search(node.right, id);
        }
    }
    public void Adjust(int id, String type, double price, int quantity){
        avlProduct.Adjust(id, type, price, quantity);
        Adjust(root, id, type, price, quantity);
    }
    void Adjust(Node node, int id, String type, double price, int quantity){
        Node wantedNode = search(node, id);
        if (wantedNode == null) return;
        if (type.equals("Price")){
            wantedNode.SetNode(wantedNode.id, wantedNode.name, price, wantedNode.quantity);
        }
        else if (type.equals("Quantity")){
            wantedNode.SetNode(wantedNode.id, wantedNode.name, wantedNode.price, quantity);

        }
        else if (type.equals("Price & Quantity")){
            wantedNode.SetNode(wantedNode.id, wantedNode.name, price, quantity);
        }
    }

    public ArrayList<Products.Node> getProducts(Node node) {
        products = new ArrayList<>();
        collectProducts(node, products);
        return products;
    }

    private void collectProducts(Node node, ArrayList<Products.Node> products) {
        if (node == null) {
            return;
        }
        collectProducts(node.left, products);
        products.add(new Products.Node(node.id, node.name, node.price, node.quantity,node.imagePath));
        collectProducts(node.right, products);
    }

    // M: to add items to the arrayList
    public void inOrder(Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.print(node.id + " " + node.name + " " + node.price + " " + node.quantity);
        System.out.println();
        inOrder(node.right);
    }

    public void delete(int id) {
        avlProduct.delete(id);
        root = deleteNode(root, id);
    }

    // M: Delete the node from the list or the file before making it null
    private Node deleteNode(Node node, int id) {
        if (node == null) {
            return null;
        }
        if (id < node.id) {
            node.left = deleteNode(node.left, id);
        } else if (id > node.id) {
            node.right = deleteNode(node.right, id);
        } else {
            if (node.left == null && node.right == null) {
                return null;
            }
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }
            Node k = findMin(node.right);
            node.id = k.id;
            node.right = deleteNode(node.right, k.id);
        }
        return node;
    }

    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public ArrayList<Products.Node> getProducts() {
        return products;
    }

    public Node getRoot() {
        return root;
    }


}