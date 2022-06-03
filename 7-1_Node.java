public class Node<T extends Comparable<T>> {

    public Node(T key) {
        this.key = key;
        this.left = null;
        this.right = null;
        this.parent = null;
    }

    T key;
    Node<T> left;
    Node<T> right;
    Node<T> parent;

    //for tests
    @Override
    public String toString() {
        return "Node{" + "key=" + key + '}';
    }
}
