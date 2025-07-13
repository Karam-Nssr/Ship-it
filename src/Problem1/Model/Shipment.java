package Problem1.Model;

import java.util.ArrayList;
import java.util.Date;

public class Shipment {
    Node root;
    ArrayList<Node> shipments;
    MaxHeapShipment maxHeapShipment = new MaxHeapShipment(50);

    class Node {
        Node left, right;
        int id;
        String location;
        double cost;
        Date date;

        public Node(int id, String location, double cost, Date date) {
            this.id = id;
            this.location = location;
            this.cost = cost;
            this.date = date;
            left = right = null;
        }
    }
    public void insert(int id, String location, double cost, Date date, int priority) {
        root = insert(root, id, location, cost, date);
        maxHeapShipment.insert(id, location, cost, date, priority);

    }

    private Node insert(Node root, int id, String location, double cost, Date date) {
        if (root == null) {
            root = new Node(id, location, cost, date);
            return root;
        }
        if (id < root.id) {
            root.left = insert(root.left, id, location, cost, date);
        } else if (id > root.id) {
            root.right = insert(root.right, id, location, cost, date);
        }
        return root;
    }
    public void search(int id){
        root = search(root, id);
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
    public void Adjust(int id, Date date, int priority){
        root = Adjust(root, id, date);
        if (priority != 0) maxHeapShipment.Adjust(id, priority);

    }
    Node Adjust(Node node, int id, Date date){
        Node wantedNode = search(node, id);
        if (wantedNode == null) return null;
        wantedNode = new Node(wantedNode.id, wantedNode.location, wantedNode.cost, date);
        // M: Make sure what to return
        return wantedNode;
    }
}
