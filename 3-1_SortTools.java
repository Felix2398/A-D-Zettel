import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class SortTools {

    //3.1.c)
    public static void main(String[] args) {
        int n = 100;

        long[] insertionTimes = new long[10];
        long[] mergeTimes = new long[10];
        long[] mergeNewTimes = new long[10];
        long averageInsertionTime = 0;
        long averageMergeTime = 0;
        long averageMergeNewTime = 0;

        long starTime;
        long endTime;

        for (int i = 0; i < 10; i++) {
            int[] arr = createSequenceInc(n);
            starTime = System.nanoTime();
            insertionSort(arr);
            endTime = System.nanoTime();
            insertionTimes[i] = endTime - starTime;
        }

        for (int i = 0; i < 10; i++) {
            int[] arr = createSequenceInc(n);
            starTime = System.nanoTime();
            mergeSort(arr, 0 , arr.length - 1);
            endTime = System.nanoTime();
            mergeTimes[i] = endTime - starTime;
        }

        for (int i = 0; i < 10; i++) {
            int[] arr = createSequenceInc(n);
            starTime = System.nanoTime();
            mergeSortNew(arr, 0 , arr.length - 1);
            endTime = System.nanoTime();
            mergeNewTimes[i] = endTime - starTime;
        }

        for (int i = 0; i < 10; i++) {
            averageInsertionTime += insertionTimes[i];
            averageMergeTime += mergeTimes[i];
            averageMergeNewTime += mergeNewTimes[i];

        }

        System.out.println("insertionSort");
        System.out.println(Arrays.toString(insertionTimes));
        System.out.println(averageInsertionTime / 10);
        System.out.println();
        System.out.println("mergeSort");
        System.out.println(Arrays.toString(mergeTimes));
        System.out.println(averageMergeTime / 10);
        System.out.println();
        System.out.println("mergeSortNew ");
        System.out.println(Arrays.toString(mergeNewTimes));
        System.out.println(averageMergeNewTime / 10);
        System.out.println();

        /*
                          1. Ascending          2. Descending
        n = 100
        insertionSort:           2.070                 72.140
        mergeSort:              43.290                 42.980
        mergeSortNew:           44.520                 42.450

        n = 1.000
        insertionSort:          19.680                900.200
        mergeSort:             127.570                130.870
        mergeSortNew:          128.370                124.060

        n = 10.000
        insertionSort:         151.460             13.997.140
        mergeSort:           1.086.450              1.118.620
        mergeSortNew:        1.043.480              1.026.510

        n = 100.000
        insertionSort:         466.480          1.398.396.880
        mergeSort:           6.218.630              6.001.270
        mergeSortNew:        7.711.370              6.550.530

        n = 200.000
        insertionSort:         383.240          5.582.463.560
        mergeSort:          14.540.780             12.716.900
        mergeSortNew:        7.365.550              7.004.810
        */
    }

    //3.1.a)
    public static void mergeSort(int[] arr, int startIndex, int endIndex) {
        if (startIndex < endIndex) {
            int middleIndex = (startIndex + endIndex) / 2;
            mergeSort(arr, startIndex, middleIndex);
            mergeSort(arr, middleIndex + 1, endIndex);
            merge(arr, startIndex, middleIndex, endIndex);
        }
    }

    public static void merge(int[] arr, int startIndex, int middleIndex, int endIndex) {
        int[] lefArr = new int[middleIndex - startIndex + 2];
        int[] rigArr = new int[endIndex - middleIndex + 1];

        for (int i = 0; i < lefArr.length - 1; i++) {
            lefArr[i] = arr[startIndex + i];
        }
        for (int i = 0; i < rigArr.length -1; i++) {
            rigArr[i] = arr[middleIndex + 1 + i];
        }

        lefArr[lefArr.length - 1] = Integer.MAX_VALUE;
        rigArr[rigArr.length - 1] = Integer.MAX_VALUE;

        int i = 0;
        int j = 0;

        for (int k = startIndex; k <= endIndex; k++) {
            if (lefArr[i] <= rigArr[j]) {
                arr[k] = lefArr[i];
                i++;
            } else {
                arr[k] = rigArr[j];
                j++;
            }
        }
    }

    //3.1.b)
    public static void mergeSortNew(int[] arr, int startIndex, int endIndex) {
        if (startIndex < endIndex) {
            int leftIndex = (endIndex - startIndex) / 3 + startIndex;
            int rightIndex = leftIndex + ((endIndex - startIndex) / 3) + 1;
            mergeSortNew(arr, startIndex, leftIndex);
            mergeSortNew(arr, leftIndex + 1, rightIndex);
            mergeSortNew(arr, rightIndex + 1, endIndex);
            mergeNew(arr, startIndex, leftIndex, rightIndex, endIndex);
        }
    }

    public static void mergeNew(int[] arr, int startIndex, int leftIndex, int rightIndex, int endIndex) {
        int[] lefArr = new int[leftIndex - startIndex + 2];
        int[] midArr = new int[rightIndex - leftIndex + 1];
        int[] rigArr = new int[endIndex - rightIndex +1];

        for (int i = 0; i < lefArr.length - 1; i++) {
            lefArr[i] = arr[startIndex + i];
        }
        for (int i = 0; i < midArr.length - 1; i++) {
            midArr[i] = arr[leftIndex + i + 1];
        }
        for (int i = 0; i < rigArr.length -1; i++) {
            rigArr[i] = arr[rightIndex + i + 1];
        }

        lefArr[lefArr.length - 1] = Integer.MAX_VALUE;
        midArr[midArr.length - 1] = Integer.MAX_VALUE;
        rigArr[rigArr.length - 1] = Integer.MAX_VALUE;

        int i = 0;
        int j = 0;
        int k = 0;

        for (int l = startIndex; l <= endIndex; l++) {
            if (lefArr[i] <= midArr[j] && lefArr[i] <= rigArr[k]) {
                arr[l] = lefArr[i];
                i++;
            } else if (midArr[j] <= lefArr[i] && midArr[j] <= rigArr[k]) {
                arr[l] = midArr[j];
                j++;
            } else {
                arr[l] = rigArr[k];
                k++;
            }
        }
    }

    //3.1.d)
    public static <T extends Comparable<T>> void mergeSortGen(T[] arr, int startIndex, int endIndex) {
        if (startIndex < endIndex) {
            int middleIndex = (startIndex + endIndex) / 2;
            mergeSortGen(arr, startIndex, middleIndex);
            mergeSortGen(arr, middleIndex + 1, endIndex);
            mergeGen(arr, startIndex, middleIndex, endIndex);
        }
    }

    public static <T extends Comparable<T>> void mergeGen(T[] arr, int startIndex, int middleIndex, int endIndex) {
        ArrayList<T> arrList = new ArrayList<>();

        for (int i = startIndex; i <= middleIndex; i++) {
            arrList.add(arr[i]);
        }
        for (int i = endIndex; i >= middleIndex + 1; i--) {
            arrList.add(arr[i]);
        }

        int i = 0;
        int j = arrList.size() - 1;

        for (int k = startIndex; k <= endIndex; k++) {
            if (arrList.get(i).compareTo(arrList.get(j)) <= 0) {
                arr[k] = arrList.get(i);
                i++;
            } else {
                arr[k] = arrList.get(j);
                j--;
            }
        }
    }


    /*
    2.1.c
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

    }
    */

    //2.1.a)
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

    //2.1.b)
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

    //2.1.d)
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


    /*
    1.1.c
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

    //1.1.a)
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

    //1.1.b)
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

    //1.1.d)
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
