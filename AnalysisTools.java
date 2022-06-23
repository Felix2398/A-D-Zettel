import java.util.ArrayList;

public class AnalysisTools {
    public static <T> long getInsertTime(OpenHashmap<T> hashmap, ArrayList<T> arrayList) {
        long start = System.nanoTime();
        hashmap.insertArrayList(arrayList);
        return System.nanoTime() - start;
    }

    public static <T> long getSearchTime(OpenHashmap<T> hashmap, ArrayList<T> arrayList) {
        long start = System.nanoTime();
        hashmap.numberElementsNotFound(arrayList);
        return System.nanoTime() - start;
    }

    public static <T> int getNotInList(OpenHashmap<T> hashmap, ArrayList<T> arrayList) {
        return hashmap.numberElementsNotFound(arrayList);
    }
}
