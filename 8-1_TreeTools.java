import java.util.ArrayList;

public class TreeTools {

    public static <T extends Comparable<T>, K extends Tree<T>> void insert(K tree, Node<T> node) {
        Node<T> tempNode = tree.root;

        while (tempNode != null) {
            node.parent = tempNode;
            if (node.key.compareTo(tempNode.key) < 0) {
                tempNode = tempNode.left;
            } else {
                tempNode = tempNode.right;
            }
        }

        if (node.parent == null) {
            tree.root = node;
        } else {
            if (node.key.compareTo(node.parent.key) < 0) {
                node.parent.left = node;
            } else {
                node.parent.right = node;
            }
        }
    }

    public static <T extends Comparable<T>, K extends Tree<T>> void delete(K tree, Node<T> z) {
        Node<T> y;
        Node<T> x;

        if (z.left == null || z.right == null) { y = z; }
        else { y = successor(z); }

        if (y.left != null) { x = y.left; }
        else { x = y.right; }

        if (x != null) { x.parent = y.parent; }

        if (y.parent == null) { tree.root = x; }
        else if (y == y.parent.left) {y.parent.left = x; }
        else { y.parent.right = x; }

        if (y != z) { z.key = y.key;}
    }

    public static <T extends Comparable<T>> Node<T> successor(Node<T> x) {
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

    public static <T extends Comparable<T>> Node<T> min(Node<T> x) {
        while (x.left != null) {
            x = x.left;
        }
        return x;
    }

    public static <T extends Comparable<T>> Node<T> max(Node<T> x) {
        while (x.right != null) {
            x = x.right;
        }
        return x;
    }

    public static <T extends Comparable<T>> Node<T> search(Node<T> x, T k) {
        if (x == null || x.key.equals(k)) {
            return x;
        }
        if (k.compareTo(x.key) < 0) {
            return search(x.left, k);
        } else {
            return search(x.right, k);
        }
    }

    public static <T extends Comparable<T>> ArrayList<T> toSortedArrayList(Node<T> min) {
        ArrayList<T> arrayList = new ArrayList<>();
        Node<T> current = min;
        while (current != null) {
            arrayList.add(current.key);
            current = successor(current);
        }
        return arrayList;
    }

    public static <T extends Comparable<T>, K extends Tree<T>> void leftRotate(K tree, Node<T> node) {
        Node<T> tempNode = node.right;
        node.right = tempNode.left;
        if (tempNode.left != null) {
            tempNode.left.parent = node;
        }
        tempNode.parent = node.parent;
        if (node.parent == null) {
            tree.root = tempNode;
        } else if (node == node.parent.left) {
            node.parent.left = tempNode;
        } else {
            node.parent.right = tempNode;
        }
        tempNode.left = node;
        node.parent = tempNode;
    }

    public static <T extends Comparable<T>, K extends Tree<T>> void rightRotate(K tree, Node<T> node) {
        Node<T> tempNode = node.left;
        node.left = tempNode.right;
        if (tempNode.right != null) {
            tempNode.right.parent = node;
        }
        tempNode.parent = node.parent;
        if (node.parent == null) {
            tree.root = tempNode;
        } else if (node == node.parent.left) {
            node.parent.left = tempNode;
        } else {
            node.parent.right = tempNode;
        }
        tempNode.right = node;
        node.parent = tempNode;
    }

    public static <T extends Comparable<T>, K extends Tree<T>> void doubleLeftRotate(K tree, Node<T> x) {
        Node<T> y = x.right;
        Node<T> z = y.left;

        x.right = z.left;
        if (z.left != null) {
            z.left.parent = x;
        }

        y.left = z.right;
        if (z.right != null) {
            z.right.parent = y;
        }

        z.left = x;
        z.right = y;
        z.parent = x.parent;

        if (x.parent == null) {
            tree.root = z;
        } else if (x == x.parent.left) {
            x.parent.left = z;
        } else {
            x.parent.right = z;
        }

        x.parent = z;
        y.parent = z;
    }

    public static <T extends Comparable<T>, K extends Tree<T>> void doubleRightRotate(K tree, Node<T> x) {
        Node<T> y = x.left;
        Node<T> z = y.right;

        x.left = z.right;
        if (z.right != null) {
            z.right.parent = x;
        }

        y.right = z.left;
        if (z.left != null) {
            z.left.parent = y;
        }

        z.right = x;
        z.left = y;
        z.parent = x.parent;

        if (x.parent == null) {
            tree.root = z;
        } else if (x == x.parent.left) {
            x.parent.left = z;
        } else {
            x.parent.right = z;
        }

        x.parent = z;
        y.parent = z;
    }

    public static <T extends Comparable<T>, K extends Tree<T>> void rebalancedTree(K tree, Node<T> current, double alpha) {
        while (current != null) {
            if (getRootBalance(current) < alpha) {
                if (getRootBalance(current.right) <= 1 / (2 - alpha)) {
                    leftRotate(tree, current);
                } else {
                    doubleLeftRotate(tree, current);
                }
            }
            if (getRootBalance(current) > 1 - alpha) {
                if (getRootBalance(current.left) >= 1 - 1 / (2 - alpha)) {
                    rightRotate(tree, current);
                } else {
                    doubleRightRotate(tree, current);
                }
            }
            current = current.parent;
        }
    }

    public static <T extends Comparable<T>, K extends Tree<T>> void insertArray(K tree, T[] arr) {
        for (T element : arr) {
            Node<T> node = new Node<>(element);
            tree.insertion(node);
        }
    }

    public static <T extends Comparable<T>> double getRootBalance(Node<T> node) {
        return (double) (countNodes(node.left) + 1) / (countNodes(node) + 1);
    }

    public static <T extends Comparable<T>> int countNodes(Node<T> node) {
        if (node == null) {
            return 0;
        } else {
            return (1 + countNodes(node.left) + countNodes(node.right));
        }
    }

    //for tests
    public static <T extends Comparable<T>> String inOrderTreeWalk(Node<T> node, String str) {
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
    public static <T extends Comparable<T>> String postOrderTreeWalk(Node<T> node, String str) {
        if (node != null) {
            String left = postOrderTreeWalk(node.left, str);
            String right = postOrderTreeWalk(node.right, str);
            String key = node.key.toString() + ", ";
            return left + right + key;
        } else {
            return "";
        }
    }
}
