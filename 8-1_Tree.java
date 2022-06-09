import java.util.ArrayList;

public abstract class Tree <T extends Comparable<T>> {
    Node<T> root;
    public abstract void insertion(Node<T> node);
    public abstract void deletion(Node<T> node);
    public abstract Node<T> search (T key);
    public abstract Node<T> min();
    public abstract Node<T> max();
    public abstract ArrayList<T> toSortedArrayList();
}
