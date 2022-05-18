import java.util.ArrayList;

public class PriorityQueueMinHeap<K extends Comparable<K>> implements PriorityQueue<K> {

    private final ArrayList<K> a = new ArrayList<>();

    @Override
    public void addElement(K elem) {
        HeapTools.insert(a, elem);
    }

    @Override
    public K getFirst() {
        return a.get(0);
    }

    @Override
    public void deleteFirst() {
        a.remove(0);
        HeapTools.buildMinHeap(a);
    }

    //für tests
    @Override
    public String toString() {
        return a.toString();
    }

    //für tests
    public void setHeap(ArrayList<K> arrList) {
        a.clear();
        a.addAll(arrList);
        HeapTools.buildMinHeap(a);
    }
}
