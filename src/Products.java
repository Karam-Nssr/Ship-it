import java.util.ArrayList;

public class Products{
    ArrayList<Products> products;
    Node root;
    int id;
    String name;
    double price;
    int quantity;
    public Products(int id, String name, double price, int quantity) {
        this.id=id;
        this.name=name;
        this.price=price;
        this.quantity=quantity;
    }

    class Node{
        Node left,right;
        int id;
        String name;
        double price;
        int quantity;
        public Node(int id,String name,double price,int quantity){
            this.id=id;
            this.name=name;
            this.price=price;
            this.quantity=quantity;
            left=right=null;
        }
    }
    public Products(){
        root=null;
    }
    public void insert(int id,String name,double price,int quantity){
        root=insert(root,id,name,price,quantity);
    }
    private Node insert(Node root,int id,String name,double price,int quantity){
        if(root==null){
            root=new Node(id,name,price,quantity);
            return root;
        }
        if(id<root.id){
            root.left=insert(root.left,id,name,price,quantity);
        }
        else if(id>root.id){
            root.right=insert(root.right,id,name,price,quantity);
        }
        return root;
    }
    boolean search(Node node,int id){
        if(node==null){
            return false;
        }
        if(node.id==id){
            return true;
        }
        if(id<node.id){
            return search(node.left,id);
        }
        else {
            return search(node.right,id);
        }
    }
    public  ArrayList<Products> getProducts(Node node) {
        products = new ArrayList<>();
        collectProducts(node, products);
        return products;
    }
    private  void collectProducts(Node node, ArrayList<Products> products) {
        if (node == null) {
            return;
        }
        collectProducts(node.left, products);
        products.add(new Products(node.id, node.name, node.price, node.quantity));
        collectProducts(node.right, products);
    }

    void inOrder(Node node){
        if(node==null){
            return;
        }
        inOrder(node.left);
        System.out.print(node.id+" "+node.name+" "+node.price+" "+node.quantity);
        System.out.println();
        inOrder(node.right);
    }
    public void delete(int id){
        root=deleteNode(root,id);
    }
    private Node deleteNode(Node node,int id){
        if(node==null)
        {
            return null;
        }
        if(id<node.id){
            node.left=deleteNode(node.left,id);
        }
        else if(id>node.id){
            node.right=deleteNode(node.right,id);
        }
        else
        {
            if(node.left==null && node.right==null){
                return null;
            }
            if(node.left == null)
            {
                return node.right;
            }
            if(node.right==null){
                return node.left;
            }
            Node k=findMin(node.right);
            node.id=k.id;
            node.right=deleteNode(node.right,k.id);
        }
        return node;
    }
    private Node findMin(Node node){
        while(node.left != null){
            node=node.left;
        }
        return node;
    }
}