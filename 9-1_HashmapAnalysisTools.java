import java.util.ArrayList;

public class HashmapAnalysisTools {
    public static <T> long getInsertTime(LinkedHashmap<T> hashmap, ArrayList<T> arrayList) {
        long start = System.nanoTime();
        hashmap.insertArrayList(arrayList);
        return System.nanoTime() - start;
    }

    public static <T> long getSearchTime(LinkedHashmap<T> hashmap, ArrayList<T> arrayList) {
        long start = System.nanoTime();
        hashmap.getNumberOfElementsNotFound(arrayList);
        return System.nanoTime() - start;
    }

    public static <T> void printData(LinkedHashmap<T> hashmap) {
        System.out.println(new HashmapData<>(hashmap));
    }

    public static <T> IndexAndLength getMaxListLength(LinkedHashmap<T> hashmap) {
        int index = -1;
        int max = 0;

        for (int i = 0; i < hashmap.bucket.size(); i++) {
            int length = hashmap.bucket.get(i).getLength();
            if (length > max) {
                max = length;
                index = i;
            }
        }
        return new IndexAndLength(index, max);
    }

    public static <T> int getAverageListLength(LinkedHashmap<T> hashmap) {
        int sum = 0;
        int count = 0;

        for (LinkedList<T> list : hashmap.bucket) {
            if (list.head != null) {
                sum += list.getLength();
                count++;
            }
        }

        if (count == 0) {
            return -1;
        } else {
            return sum / count;
        }
    }

    public static <T> int getNumberOfElements(LinkedHashmap<T> hashmap) {
        int count = 0;
        for (LinkedList<T> list : hashmap.bucket) {
            if (list.head != null) {
                count += list.getLength();
            }
        }
        return count;
    }

    public static <T> int getNumberOfEmptyLists(LinkedHashmap<T> hashmap) {
        int count = 0;
        for (LinkedList<T> list : hashmap.bucket) {
            if (list.head == null) {
                count++;
            }
        }
        return count;
    }

    public static class HashmapData<T> {
        int m;
        int elements;
        double emptyListPercent;
        int maxIndex;
        int maxListLength;
        int averageListLength;

        HashmapData(LinkedHashmap<T> hashmap) {
            this.m = hashmap.m;
            this.elements = getNumberOfElements(hashmap);
            this.emptyListPercent = (double) Math.round((double) getNumberOfEmptyLists(hashmap) / m * 10000) / 100;
            this.maxIndex = getMaxListLength(hashmap).index;
            this.maxListLength = getMaxListLength(hashmap).length;
            this.averageListLength = getAverageListLength(hashmap);
        }

        @Override
        public String toString() {
            return "HashmapData{" +
                    "m=" + m +
                    ", elements=" + elements +
                    ", emptyListPercent=" + emptyListPercent +
                    ", maxIndex=" + maxIndex +
                    ", maxListLength=" + maxListLength +
                    ", averageListLength=" + averageListLength +
                    '}';
        }
    }

    public static class IndexAndLength {
        int index;
        int length;

        IndexAndLength(int index, int length) {
            this.index = index;
            this.length = length;
        }

        @Override
        public String toString() {
            return "IndexAndLength{" +
                    "index=" + index +
                    ", length=" + length +
                    '}';
        }
    }
}
