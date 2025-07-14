package Problem1.Model;
public class AVLProduct {
    Node root;
    public class Node {
        Node left, right;
        int id;
        String name;
        double price;
        int quantity;
        int height;

        public Node(int id, String name, double price, int quantity) {
            this.id = id;
            this.name = name;
            this.price = price;
            this.quantity = quantity;
            left = right = null;
            height = 1;
        }
        public void SetNode(int id, String name, double price, int quantity){
            this.id = id;
            this.name = name;
            this.price = price;
            this.quantity = quantity;
        }
    }

    int height(Node N) {
        if (N == null)
            return 0;
        return N.height;
    }

    int max(int a, int b) {
        return Math.max(a, b);
    }

    int getBalance(Node N) {
        if (N == null)
            return 0;
        return height(N.left) - height(N.right);
    }

    Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        //
        x.right = y;
        y.left = T2;

        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        return x;
    }

    Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        return y;
    }

    Node insert(Node node, int id, String name, double price, int quantity) {
        if (node == null)
            return (new Node(id, name, price, quantity));

        if (id < node.id)
            node.left = insert(node.left, id, name, price, quantity);
        else if (id > node.id)
            node.right = insert(node.right, id, name, price, quantity);
        else
            return node;

        node.height = 1 + max(height(node.left), height(node.right));

        int balance = getBalance(node);

        if (balance > 1 && id < node.left.id) {
            System.out.println("RR");
            return rightRotate(node);
        }
        if (balance < -1 && id > node.right.id) {
            System.out.println("LL");
            return leftRotate(node);
        }

        if (balance > 1 && id > node.left.id) {
            System.out.println("LR");
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && id < node.right.id) {
            System.out.println("RL");
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }
        return node;
    }

    public void insert(int id, String name, double price, int quantity) {
        root = insert(root, id, name, price, quantity);
    }
    Node deleteNode(Node root, int id) {
        if (root == null)
            return root;
        if (id < root.id)
            root.left = deleteNode(root.left, id);
        else if (id > root.id)
            root.right = deleteNode(root.right, id);
        else {
            if ((root.left == null) || (root.right == null)) {
                Node temp = null;
                if (root.left == null)
                    temp = root.right;
                else
                    temp = root.left;
                if (temp == null) {
                    return null;
                } else {
                    root = temp;
                }
            } else {
                Node temp = minValueNode(root.right);

                root.id = temp.id;

                root.right = deleteNode(root.right, temp.id);
            }
        }

        root.height = max(height(root.left), height(root.right)) + 1;
        int balance = getBalance(root);

        if (balance > 1 && getBalance(root.left) >= 0) {
            return rightRotate(root);
        }

        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }
        if (balance < -1 && getBalance(root.right) <= 0) {
            return leftRotate(root);
        }

        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    public void delete(int id) {
        root = deleteNode(root, id);
    }
    private Node search(Node currentRoot, int id) {
        if (currentRoot == null || currentRoot.id == id)
            return currentRoot;

        if (id > currentRoot.id)
            return search(currentRoot.right, id);

        return search(currentRoot.left, id);
    }

    public boolean search(int id) {
        return search(root, id) != null;
    }


    void preOrder(Node node) {
        if (node != null) {
            System.out.print(node.id + "(H:" + node.height + ")");
            if (node.id % 2 == 0) System.out.print("Even");
            preOrder(node.left);
            preOrder(node.right);
        }
    }

    public void preOrder() {
        System.out.print("PreOrder: ");
        preOrder(root);
        System.out.println();
        System.out.println(Max(root).id);
    }

    public Node Max(Node root) {
        Node curr = root;
        while (curr.right != null) {
            curr = curr.right;
        }
        return curr;
    }
    Node minValueNode(Node node) {
        Node current = node;
        while (current.left != null)
            current = current.left;

        return current;
    }
    public void Adjust(int id, String type, double price, int quantity){
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
}
