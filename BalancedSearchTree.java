import java.util.ArrayList;

public class BalancedSearchTree<T extends Comparable<T>> extends Tree<T> {
    double alpha;

    BalancedSearchTree() {
        this(((2d / 11d) + (1d - (1d / Math.sqrt(2)))) / 2);
    }

    BalancedSearchTree(double alpha) {
        this.root = null;
        this.alpha = alpha;
    }

    @Override
    public void insertion(Node<T> node) {
        TreeTools.insert(this, node);
        double rootBalance = TreeTools.getRootBalance(root);
        if (alpha > rootBalance || 1 - alpha < rootBalance) {
            TreeTools.rebalancedTree(this, node, alpha);
        }
    }

    @Override
    public void deletion(Node<T> node) {
        TreeTools.delete(this, node);
        double rootBalance = TreeTools.getRootBalance(root);
        if (alpha > rootBalance || 1 - alpha < rootBalance) {
            TreeTools.rebalancedTree(this, node, alpha);
        }
    }

    @Override
    public Node<T> search(T key) {
        return TreeTools.search(root, key);
    }

    @Override
    public Node<T> min() {
        return TreeTools.min(root);
    }

    @Override
    public Node<T> max() {
        return TreeTools.max(root);
    }

    @Override
    public ArrayList<T> toSortedArrayList() {
        return TreeTools.toSortedArrayList(min());
    }
}
