import java.util.ArrayList;

public class AVL {
    protected Node root;

    public AVL() {
        root = null;
    }

    public Node getRoot() {
        return this.root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }

    public void insert(Student key) {
        this.root = insert(this.root, key);
    }

    private Node insert(Node node, Student key) {
        if (node == null){
            return (new Node(key));
        }
        else if (key.compareTo(node.getData()) == -1) {
            node.setLeft(insert(node.getLeft(),key));
        }
        else if (key.compareTo(node.getData()) == 1)
            node.setRight(insert(node.getRight(),key));
        else
            return null;
        node.setHeight(1+Math.max(height(node.getLeft()),height(node.getRight())));
        return balance(node);
    }

    public Node search(int key) {
        return search(root, key);
    }

    private Node search(Node node, int key) {
        if (node == null) {
            return null;
       } else if (key < node.getData().getId()) {
   
            return search(node.getLeft(), key);
   
       } else if (key > node.getData().getId()) {
   
            return search(node.getRight(), key);

       } else {
            return node;  // found
       }
    }

    public void delete(Student key) {
        root = delete(root, key);
    }

    private Node delete(Node x, Student key) {
        if (x == null) {
            return x;
        }
        else if (key.compareTo(x.getData()) == -1) {
            x.setLeft(delete(x.getLeft(), key));
        } 
        else if (key.compareTo(x.getData()) == 1) {
            x.setRight(delete(x.getRight(), key));
        }
        else {
            if (x.getLeft() == null || x.getRight() == null) {
              x = (x.getLeft() == null) ? x.getRight() : x.getLeft();
            }
            else{
                Node temp = x;
                x = findMin(temp.getRight());
                x.setRight(deleteMin(temp.getRight()));
                x.setLeft(temp.getLeft());
            }
        }
        if (x != null) {
            x.setHeight(1+Math.max(height(x.getLeft()),height(x.getRight())));
            x = balance(x);
        }
        return x;
    }

    // ------------------Supported methods------------------

    public int height() {
        return height(root);
    }

    protected int height(Node node) {
        if (node == null)
            return -1;
        return node.getHeight();
    }

    private Node rotateLeft(Node x) {
        Node y = x.getRight();
        x.setRight(y.getLeft());
        y.setLeft(x);
        x.setHeight(1 + Math.max(height(x.getLeft()), height(x.getRight())));
        y.setHeight(1 + Math.max(height(y.getLeft()), height(y.getRight())));
        return y;
    }

    private Node rotateRight(Node x) {
        Node y = x.getLeft();
        x.setLeft(y.getRight());
        y.setRight(x);
        x.setHeight(1 + Math.max(height(x.getLeft()), height(x.getRight())));
        y.setHeight(1 + Math.max(height(y.getLeft()), height(y.getRight())));
        return y;
    }

    private int checkBalance(Node x) {
        return height(x.getLeft()) - height(x.getRight());
    }

    protected Node balance(Node x) {
        if (checkBalance(x) < -1) {
            if (checkBalance(x.getRight()) > 0) {
                x.setRight(rotateRight(x.getRight()));
            }
            x = rotateLeft(x);
        } else if (checkBalance(x) > 1) {
            if (checkBalance(x.getLeft()) < 0) {
                x.setLeft(rotateLeft(x.getLeft()));
            }
            x = rotateRight(x);
        }
        return x;
    }

    public ArrayList<Node> NLR() {
        ArrayList<Node> result = new ArrayList<Node>();
        NLR(this.root, result);
        return result;
    }

    private void NLR(Node node, ArrayList<Node> result) {
        if (node != null) {
            result.add(node);
            NLR(node.getLeft(), result);
            NLR(node.getRight(), result);
        }
    }

    private Node deleteMin(Node x) {
        if (x.getLeft() == null)
            return x.getRight();
        x.setLeft(deleteMin(x.getLeft()));
        return x;
    }

    private Node findMin(Node x) {
        if (x.getLeft() == null)
            return x;
        else
            return findMin(x.getLeft());
    }

    public boolean contain(int id) {
        return search(root, id) == null ? false : true;
    }
    public Node cloneTree(Node root) {
        if(root==null)
            return null;
        Node n1 = new Node();
        n1.setData(root.getData());
        cloneTree(root, n1);
        return n1;
    }

    public void cloneTree(Node root, Node newNode) {
        if (root == null) {
            return;
        }
        if (root.getLeft() != null) {
            newNode.setLeft(new Node());
            newNode.getLeft().setData(root.getLeft().getData());
            cloneTree(root.getLeft(), newNode.getLeft());
        }
        if (root.getRight() != null) {
            newNode.setRight(new Node());
            newNode.getRight().setData(root.getRight().getData());
            cloneTree(root.getRight(), newNode.getRight());
        }

    }
}
