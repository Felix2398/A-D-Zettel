import java.util.ArrayList;

public class SearchTree<T extends Comparable<T>> {
    Node<T> root;

    public SearchTree(Node<T> node) {
        this.root = node;
    }

    public SearchTree() {
         this.root = null;
    }

    public void insert(Node<T> node) {
        Node<T> y = root;

        while (y != null) {
            node.parent = y;
            if (node.key.compareTo(y.key) < 0) {
                y = y.left;
            } else {
                y = y.right;
            }
        }

        if (node.parent == null) {
            this.root = node;
        } else {
            if (node.key.compareTo(node.parent.key) < 0) {
                node.parent.left = node;
            } else {
                node.parent.right = node;
            }
        }
    }

    public void delete(Node<T> z) {
        Node<T> y;
        Node<T> x;

        if (z.left == null || z.right == null) { y = z; }
        else { y = successor(z); }

        if (y.left != null) { x = y.left; }
        else { x = y.right; }

        if (x != null) { x.parent = y.parent; }

        if (y.parent == null) { root = x; }
        else if (y == y.parent.left) {y.parent.left = x; }
        else { y.parent.right = x; }

        if (y != z) { z.key = y.key;}
    }

    public Node<T> search(T k) {
        return search(root, k);
    }

    public Node<T> search(Node<T> x, T k) {
        if (x == null || x.key.equals(k)) {
            return x;
        }
        if (k.compareTo(x.key) < 0) {
            return search(x.left, k);
        } else {
            return search(x.right, k);
        }
    }

    public Node<T> min() {
        return min(root);
    }

    public Node<T> min(Node<T> x) {
        while (x.left != null) {
            x = x.left;
        }
        return x;
    }

    public Node<T> max() {
        return max(root);
    }

    public Node<T> max(Node<T> x) {
        while (x.right != null) {
            x = x.right;
        }
        return x;
    }

    public Node<T> successor(Node<T> x) {
        Node<T> y;
        if (x.right != null) {
            return min(x.right);
        }
        y = x.parent;
        while (y != null && x == y.right) {
            x = y;
            y = x.parent;
        }
        return y;
    }


    public ArrayList<T> toSortedArrayList() {
        ArrayList<T> arrayList = new ArrayList<>();
        Node<T> current = min();
        while (current != null) {
            arrayList.add(current.key);
            current = successor(current);
        }
        return arrayList;
    }



    //for tests
    private String inOrderTreeWalk(Node<T> node, String str) {
         if (node != null) {
             String left = inOrderTreeWalk(node.left, str);
             String key = node.key.toString() + ", ";
             String right = inOrderTreeWalk(node.right, str);
             return left + key + right;
         } else {
             return "";
         }
    }

    //for tests
    private String postOrderTreeWalk(Node<T> node, String str) {
        if (node != null) {
            String left = postOrderTreeWalk(node.left, str);
            String right = postOrderTreeWalk(node.right, str);
            String key = node.key.toString() + ", ";
            return left + right + key;
        } else {
            return "";
        }
    }

    //for tests
    public String toString() {
         String str = "Root: " + root.key.toString();
         String str1 = inOrderTreeWalk(root, "");
         String str2 = postOrderTreeWalk(root, "");
         return str + "\nInorder: " + str1 + "\nPostorder: " + str2;
    }

    //for tests
    public void insertArray(T[] arr) {
        for (T element : arr) {
            Node<T> node = new Node<>(element);
            insert(node);
        }
    }
}

