import java.util.ArrayList;
import java.util.Arrays;

public abstract class LinkedHashmap<T> {
    int m;
    ArrayList<LinkedList<T>> bucket;

    LinkedHashmap(int m){
        this.m = m;
        this.bucket = new ArrayList<>(m);
        for (int i = 0; i < m; i++) {
            bucket.add(new LinkedList<>());
        }
    }

    LinkedHashmap(int m, ArrayList<T> arrayList) {
        this(m);
        insertArrayList(arrayList);
    }

    public abstract int hashFunction(T key);

    public void insert(T key) {
        bucket.get(hashFunction(key)).add(key);
    }

    public void delete(T key) {
        bucket.get(hashFunction(key)).delete(key);
    }

    public  boolean search(T key) {
        return bucket.get(hashFunction(key)).search(key);
    }

    public void insertArrayList(ArrayList<T> arrayList) {
        arrayList.forEach(this::insert);
    }

    public void insertArrayList(T[] array) {
        insertArrayList(new ArrayList<>(Arrays.asList(array)));
    }

    public int getNumberOfElementsNotFound(ArrayList<T> arrayList) {
        int sum = 0;
        for (T element : arrayList) {
            if (!search(element)) {
                sum++;
            }
        }
        return sum;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            sb.append(i).append(": ");
            if (bucket.get(i) != null) {
                sb.append(bucket.get(i).toString()).append("\n");
            }
        }
        return sb.toString();
    }
}
