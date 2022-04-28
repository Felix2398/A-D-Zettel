import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Random;

public class SortTools {

    public static void main(String[] args) {
        int n = 100000;

        long[] bubbleTimes = new long[10];
        long[] bubbleNewTimes = new long[10];
        long[] insertionTimes = new long[10];
        long averageBubbleTime = 0;
        long averageBubbleNewTime = 0;
        long averageInsertionTime = 0;

        long starTime;
        long endTime;

        for (int i = 0; i < 10; i++) {
            int[] arr = createSequenceDec(n);
            starTime = System.nanoTime();
            bubbleSort(arr);
            endTime = System.nanoTime();
            bubbleTimes[i] = endTime - starTime;
        }

        for (int i = 0; i < 10; i++) {
            int[] arr = createSequenceDec(n);
            starTime = System.nanoTime();
            bubbleSortNew(arr);
            endTime = System.nanoTime();
            bubbleNewTimes[i] = endTime - starTime;
        }

        for (int i = 0; i < 10; i++) {
            int[] arr = createSequenceDec(n);
            starTime = System.nanoTime();
            insertionSort(arr);
            endTime = System.nanoTime();
            insertionTimes[i] = endTime - starTime;
        }

        for (int i = 0; i < 10; i++) {
            averageBubbleTime += bubbleTimes[i];
            averageBubbleNewTime += bubbleNewTimes[i];
            averageInsertionTime += insertionTimes[i];
        }

        System.out.println("BubbleSort");
        System.out.println(Arrays.toString(bubbleTimes));
        System.out.println(averageBubbleTime / 10);
        System.out.println();
        System.out.println("BubbleSortNew");
        System.out.println(Arrays.toString(bubbleNewTimes));
        System.out.println(averageBubbleNewTime / 10);
        System.out.println();
        System.out.println("InsertionSort");
        System.out.println(Arrays.toString(insertionTimes));
        System.out.println(averageInsertionTime / 10);
        System.out.println();

        /*
        n = 100
        BubbleSort:     114.960 ns
        BubbleSortNew:  158.900 ns
        InsertionSort:   72.040 ns

        n = 1.000
        BubbleSort:       714.410 ns
        BubbleSortNew:  2.051.670 ns
        InsertionSort:    742.680 ns

        n = 10.000
        BubbleSort:      50.673.700 ns
        BubbleSortNew:  110.391.770 ns
        InsertionSort:   13.925.210 ns

        n = 100.000
        BubbleSort:      2.962.592.200 ns
        BubbleSortNew:  10.540.190.930 ns
        InsertionSort:   1.364.901.360 ns
        */
    }

    //Aufgabe 1.a)
    public static void bubbleSort(int[] arr) {
        for (int i = arr.length - 1; i >= 1; i--) {
            for (int j = 0; j <= i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int s = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = s;
                }
            }
        }
    }

    //Aufgabe 1.b)
    public static void bubbleSortNew(int[] arr) {
        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j <= i; j += 10) {

                int end = j + 10;
                if (j + 10 > i) {
                    end = i;
                }

                for (int k = j; k <= end; k++) {
                    int s = arr[k];
                    int l = k - 1;
                    while (l >= j && arr[l] > s) {
                        arr[l + 1] = arr[l];
                        l = l - 1;
                    }
                    arr[l + 1] = s;
                }
            }
        }
    }

    //Aufgabe 1.d)
    public static <T extends Comparable<T>> void bubbleSortGen(T[] arr) {
        for (int i = arr.length - 1; i >= 1; i--) {
            for (int j = 0; j <= i - 1; j++) {
                if (arr[j].compareTo(arr[j + 1]) > 0) {
                    T s = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = s;
                }
            }
        }
    }

    //Aufgaben von Zettel 01
    /*
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


        //Time for 100 elements: 58175 Nanoseconds
        //Time for 1000 elements: 362405 Nanoseconds
        //Time for 10000 elements: 13574970 Nanoseconds
        //Time for 100000 elements: 1403220590 Nanoseconds
        //Time for 200000 elements: 7033564700 Nanoseconds

    }
    */

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
