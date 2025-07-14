package Problem1.Model;

import java.util.ArrayList;
import java.util.List;

public class Shipment {
    Node root;
    ArrayList<Node> shipments;
    MaxHeapShipment maxHeapShipment = new MaxHeapShipment(50);

    public MaxHeapShipment getMaxHeapShipment() {
        return maxHeapShipment;
    }

    public class Node {
        Node left, right;
        int id;
        String location;
        double cost;
        String date;
        List<Products.Node> products;
        int priority;

        public Node(int id, String location, double cost, String date,List<Products.Node> products,int priority) {
            this.id = id;
            this.location = location;
            this.cost = cost;
            this.date = date;
            this.products=products;
            this.priority = priority;
            left = right = null;
        }

        public void SetShipment(int id, String location, double cost, String date,List<Products.Node> products,int priority){
            this.id = id;
            this.location = location;
            this.cost = cost;
            this.date = date;
            this.products=products;
            this.priority=priority;
        }

        public int getId() {
            return id;
        }

        public String getLocation() {
            return location;
        }

        public double getCost() {
            return cost;
        }

        public String getDate() {
            return date;
        }
        public int getPriority(){
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }
        public void setDate(String date) {
            this.date = date;
        }
    }
    public void insert(int id, String location, double cost, String date, int priority,List<Products.Node> products) {
        root = insert(root, id, location, cost, date,products,priority);
        maxHeapShipment.insert(id, location, cost, date, priority);

    }
    public ArrayList<Shipment.Node> getShipments(Shipment.Node node) {
        shipments = new ArrayList<>();
        collectShipments(node, shipments);
        return shipments;
    }

    private void collectShipments(Shipment.Node node, ArrayList<Shipment.Node> products) {
        if (node == null) {
            return;
        }
        collectShipments(node.left, products);
        products.add(new Shipment.Node(node.id, node.location, node.cost, node.date,node.products, node.priority));
        collectShipments(node.right, products);
    }

    private Node insert(Node root, int id, String location, double cost, String date,List<Products.Node> products,int priority) {
        if (root == null) {
            root = new Node(id, location, cost, date,products,priority);
            return root;
        }
        if (id < root.id) {
            root.left = insert(root.left, id, location, cost, date,products,priority);
        } else if (id > root.id) {
            root.right = insert(root.right, id, location, cost, date,products,priority);
        }
        return root;
    }
    public boolean search(int id){
        return search(root, id) != null;
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
    public void Adjust(int id, String date, int priority){
        Adjust(root, id, date);
        if (priority != 0) maxHeapShipment.Adjust(id, priority,date);

    }
    void Adjust(Node node, int id, String date){
        Node wantedNode = search(node, id);
        if (wantedNode == null) return;
        wantedNode.SetShipment(wantedNode.id, wantedNode.location, wantedNode.cost, date,wantedNode.products, wantedNode.priority);
        // M: Make sure what to return
    }
        public void inOrder(Shipment.Node node) {
        if (node == null) {
            return;
        }
        inOrder(node.left);
        System.out.print(node.id + " " + node.priority);
        System.out.println();
        inOrder(node.right);
    }
    public Node getRoot() {
        return root;
    }
}
