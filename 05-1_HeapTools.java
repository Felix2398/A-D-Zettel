import java.util.ArrayList;

public class HeapTools {

    public static <T extends Comparable<T>> void buildMinHeap(ArrayList<T> arrList) {
        for (int i = arrList.size() / 2; i >= 0; i--) {
            minHeapify(arrList, i);
        }
    }

    public static <T extends Comparable<T>> void decreaseKey(ArrayList<T> arrList, int i, T key) {
        if (i == arrList.size()) {
            arrList.add(key);
        } else if (key.compareTo(arrList.get(i)) > 0) {
            System.out.println("Error");
            return;
        } else {
            arrList.set(i, key);
        }

        arrList.set(i, key);

        while (i > 0 && arrList.get(getParentIndex(i)).compareTo(arrList.get(i)) > 0) {
            int parentIndex = getParentIndex(i);
            swap(arrList, i, parentIndex);
            i = parentIndex;
        }
    }

    public static <T extends Comparable<T>> void insert(ArrayList<T> arrayList, T key) {
        decreaseKey(arrayList, arrayList.size(), key);
    }

    public static <T extends Comparable<T>> void minHeapify(ArrayList<T> arrList, int i) {
        int min;
        int left = 2 * i + 1;
        int right = 2 * i + 2;

        if (left <= arrList.size() - 1 && arrList.get(left).compareTo(arrList.get(i)) < 0) {
            min = left;
        } else {
            min = i;
        }

        if (right <= arrList.size() - 1 && arrList.get(right).compareTo(arrList.get(min)) < 0) {
            min = right;
        }

        if (min != i) {
            swap(arrList, i, min);
            minHeapify(arrList, min);
        }
    }

    public static <T> void swap(ArrayList<T> arrList, int a, int b) {
        T elem = arrList.get(a);
        arrList.set(a, arrList.get(b));
        arrList.set(b, elem);
    }

    public static int getParentIndex(int childIndex) {
        if (childIndex % 2 == 0) {
            return childIndex / 2 - 1;
        } else {
            return childIndex / 2;
        }
    }
}
