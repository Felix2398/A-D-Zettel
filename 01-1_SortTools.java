import java.util.Random;

public class SortTools {

    //Aufgabe 1.c)
    public static void main(String[] args) {
        int[] numbersOfElements = {100, 1_000, 10_000, 100_000, 200_000};

        long sum = 0;
        int repeats = 20;
        long startTime;
        long endTime;

        for (int numbersOfElement : numbersOfElements) {
            for (int j = 0; j < repeats; j++) {
                int[] arr = createSequenceDec(numbersOfElement);
                startTime = System.nanoTime();
                insertionSort(arr);
                endTime = System.nanoTime();
                sum += endTime - startTime;
            }
            System.out.println("Time for " + numbersOfElement + " elements: " + sum / repeats + " Nanoseconds");
        }

        /*
        Time for 100 elements: 58175 Nanoseconds
        Time for 1000 elements: 362405 Nanoseconds
        Time for 10000 elements: 13574970 Nanoseconds
        Time for 100000 elements: 1403220590 Nanoseconds
        Time for 200000 elements: 7033564700 Nanoseconds
         */
    }

    //Aufgabe 1.a)
    public static int[] createSequenceInc(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i + 1;
        }
        return arr;
    }

    public static int[] createSequenceDec(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = arr.length - i;
        }
        return arr;
    }

    public static int[] createSequenceRand(int n) {
        Random rn = new Random();
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rn.nextInt(1, n + 1);
        }
        return arr;
    }

    public static int[] createSequenceAlt(int n) {
        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i % 2 + 1;
        }
        return arr;
    }

    //Aufgabe 1.b)
    public static void insertionSort(int[] a) {
        for (int j = 1; j < a.length; j++) {
            int s = a[j];
            int i = j - 1;
            while (i >= 0 && a[i] > s) {
                a[i + 1] = a[i];
                i = i - 1;
            }
            a[i + 1] = s;
        }
    }

    //Aufgabe 1.d)
    public static <T extends Comparable<T>> void insertionSortGen(T[] a) {
        for (int j = 1; j < a.length; j++) {
            T s = a[j];
            int i = j - 1;
            while (i >= 0 && a[i].compareTo(s) > 0) {
                a[i + 1] = a[i];
                i = i - 1;
            }
            a[i + 1] = s;
        }
    }

}
