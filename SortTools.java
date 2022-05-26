import java.util.ArrayList;
import java.util.Random;

public class SortTools {

    public static void main(String[] args) {
        MeasurementTools ms = new MeasurementTools(10, new int[] {100, 1_000, 10_000, 100_000, 200_000});

        ms.startMeasurement(MeasurementTools.Sorts.QUICKSORT, MeasurementTools.Creates.INC);
        ms.startMeasurement(MeasurementTools.Sorts.QUICKSORT_RANDOM, MeasurementTools.Creates.INC);
        ms.startMeasurement(MeasurementTools.Sorts.QUICKSORT_NEW_RANDOM, MeasurementTools.Creates.INC);
        ms.startMeasurement(MeasurementTools.Sorts.QUICKSORT_TRI_RANDOM, MeasurementTools.Creates.INC);
        ms.startMeasurement(MeasurementTools.Sorts.QUICKSORT_TRI_NEW_RANDOM, MeasurementTools.Creates.INC);

        ms.startMeasurement(MeasurementTools.Sorts.QUICKSORT, MeasurementTools.Creates.DEC);
        ms.startMeasurement(MeasurementTools.Sorts.QUICKSORT_RANDOM, MeasurementTools.Creates.DEC);
        ms.startMeasurement(MeasurementTools.Sorts.QUICKSORT_NEW_RANDOM, MeasurementTools.Creates.DEC);
        ms.startMeasurement(MeasurementTools.Sorts.QUICKSORT_TRI_RANDOM, MeasurementTools.Creates.DEC);
        ms.startMeasurement(MeasurementTools.Sorts.QUICKSORT_TRI_NEW_RANDOM, MeasurementTools.Creates.DEC);

        ms.startMeasurement(MeasurementTools.Sorts.QUICKSORT, MeasurementTools.Creates.RAND);
        ms.startMeasurement(MeasurementTools.Sorts.QUICKSORT_RANDOM, MeasurementTools.Creates.RAND);
        ms.startMeasurement(MeasurementTools.Sorts.QUICKSORT_NEW_RANDOM, MeasurementTools.Creates.RAND);
        ms.startMeasurement(MeasurementTools.Sorts.QUICKSORT_TRI_RANDOM, MeasurementTools.Creates.RAND);
        ms.startMeasurement(MeasurementTools.Sorts.QUICKSORT_TRI_NEW_RANDOM, MeasurementTools.Creates.RAND);

        /*
        QUICKSORT INC
            100:       22.267 ns
          1.000:       81.693 ns
         10.000:    6.944.144 ns
        StackOverFlowError for 100.000
        StackOverFlowError for 200.000

        QUICKSORT_RANDOM INC
            100:       40.762 ns
          1.000:      103.255 ns
         10.000:      578.275 ns
        100.000:    4.883.726 ns
        200.000:   10.220.544 ns

        QUICKSORT_NEW_RANDOM INC
            100:       16.808 ns
          1.000:       62.490 ns
         10.000:      661.566 ns
        100.000:    6.199.557 ns
        200.000:   13.614.256 ns

        QUICKSORT_TRI_RANDOM INC
            100:       13.404 ns
          1.000:       54.971 ns
         10.000:      618.488 ns
        100.000:    6.905.066 ns
        200.000:    9.636.795 ns

        QUICKSORT_TRI_NEW_RANDOM INC
            100:       23.499 ns
          1.000:       87.094 ns
         10.000:    1.092.305 ns
        100.000:    6.963.428 ns
        200.000:   13.180.686 ns

        QUICKSORT DEC
            100:        4.324 ns
          1.000:      388.811 ns
         10.000:   38.353.403 ns
        StackOverFlowError for 100.000
        StackOverFlowError for 200.000

        QUICKSORT_RANDOM DEC
            100:        4.751 ns
          1.000:       43.735 ns
         10.000:      525.356 ns
        100.000:    5.230.876 ns
        200.000:   10.631.826 ns

        QUICKSORT_NEW_RANDOM DEC
            100:        5.701 ns
          1.000:       58.509 ns
         10.000:      613.778 ns
        100.000:    6.550.093 ns
        200.000:   13.392.891 ns

        QUICKSORT_TRI_RANDOM DEC
            100:        4.828 ns
          1.000:       48.054 ns
         10.000:      503.256 ns
        100.000:    5.403.634 ns
        200.000:   10.677.690 ns

        QUICKSORT_TRI_NEW_RANDOM DEC
            100:        7.351 ns
          1.000:       69.410 ns
         10.000:      743.984 ns
        100.000:    7.403.517 ns
        200.000:   15.014.321 ns

        QUICKSORT RAND
            100:        2.871 ns
          1.000:       35.709 ns
         10.000:      465.235 ns
        100.000:    5.714.929 ns
        200.000:   11.908.638 ns

        QUICKSORT_RANDOM RAND
            100:        4.999 ns
          1.000:       59.233 ns
         10.000:      701.531 ns
        100.000:    8.283.943 ns
        200.000:   17.139.677 ns

        QUICKSORT_NEW_RANDOM RAND
            100:        6.358 ns
          1.000:       72.602 ns
         10.000:      839.083 ns
        100.000:    9.653.076 ns
        200.000:   19.766.038 ns

        QUICKSORT_TRI_RANDOM RAND
            100:        4.901 ns
          1.000:       59.806 ns
         10.000:      728.390 ns
        100.000:    8.236.955 ns
        200.000:   17.173.160 ns

        QUICKSORT_TRI_NEW_RANDOM RAND
            100:        7.918 ns
          1.000:       85.771 ns
         10.000:      967.259 ns
        100.000:   10.582.774 ns
        200.000:   21.506.768 ns
         */
    }

    public static void quickSort(int[] arr, int l, int r) {
        if (r > l) {
            int q = partition(arr, l, r);
            quickSort(arr, l, q - 1);
            quickSort(arr, q + 1, r);
        }
    }

    public static void quickSortRandom(int[] arr, int l, int r) {
        if (r > l) {
            Random rn = new Random();
            swap(arr, l, rn.nextInt(l,r + 1));
            int q = partition(arr, l, r);

            quickSortRandom(arr, l, q - 1);
            quickSortRandom(arr, q + 1, r);
        }
    }

    public static void quickSortNewRandom(int[] arr, int l, int r) {
        if (r > l) {
            Random rn = new Random();
            int a = rn.nextInt(l, r + 1);
            int b = rn.nextInt(l, r + 1);
            int c = rn.nextInt(l, r + 1);
            int medianIndex = getMedianIndex(arr, a, b, c);
            swap(arr, l, medianIndex);

            int q = partition(arr, l, r);

            quickSortNewRandom(arr, l, q - 1);
            quickSortNewRandom(arr, q + 1, r);
        }
    }

    public static int partition(int[] arr, int l, int r) {
        int x = arr[l];
        int i = l;
        for (int j = l + 1; j <= r; j++) {
            if (arr[j] <= x) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i, l);
        return i;
    }

    public static void quickSortTriRandom(int[] arr, int l, int r) {
        if (r > l) {
            Random rn = new Random();
            int a = rn.nextInt(l, r);
            int b = rn.nextInt(l, r);

            int[] q = partitionTriRandom(arr, l ,r, a, b);

            quickSortTriRandom(arr, l, q[0] - 1);
            quickSortTriRandom(arr, q[0] + 1, q[1] - 1);
            quickSortTriRandom(arr, q[1] + 1, r);
        }
    }

    public static void quickSortTriNewRandom(int[] arr, int l, int r) {
        if (r > l) {
            Random rn = new Random();
            int[] pivots = new int[5];
            for (int i = 0; i < 5; i++) {
                pivots[i] = arr[rn.nextInt(l, r)];
            }

            quickSort(pivots, 0, pivots.length - 1);
            int a = getIndexOfElement(arr, l, r, pivots[2]);
            int b = getIndexOfElement(arr, l, r, pivots[4]);
            int[] q = partitionTriRandom(arr, l, r, a, b);

            quickSortTriNewRandom(arr, l, q[0] - 1);
            quickSortTriNewRandom(arr, q[0] + 1, q[1] - 1);
            quickSortTriNewRandom(arr, q[1] + 1, r);
        }
    }

    public static int[] partitionTriRandom(int[] arr, int l, int r, int pIndex1, int pIndex2) {
        int[] q = new int[2];

        if (arr[pIndex1] == arr[pIndex2]) {
            swap(arr, l, pIndex1);
            q[0] = partition(arr, l, r);
            q[1] = q[0];
        } else {
            if (arr[pIndex1] > arr[pIndex2]) {
                swap(arr, r, pIndex1);
                swap(arr, l, pIndex2);
            } else {
                swap(arr, r, pIndex2);
                swap(arr, l, pIndex1);
            }
            q[0] = partition(arr, l, r - 1);
            swap(arr, q[0] + 1, r);
            q[1] = partition(arr, q[0] + 1, r);
        }
        return q;
    }

    public static int getIndexOfElement(int[] arr, int l, int r, int x) {
        for (int i = l; i <= r; i++) {
            if (arr[i] == x) {
                return i;
            }
        }
        return -1;
    }

    public static int getMedianIndex(int[] arr, int a, int b, int c) {
        if ((arr[a] > arr[b]) ^ (arr[a] > arr[c])) {
            return a;
        } else if ((arr[b] < arr[a]) ^ (arr[b] < arr[c])) {
            return b;
        } else {
            return c;
        }
    }

    public static void swap(int[] arr, int i, int j) {
        int s = arr[i];
        arr[i] = arr[j];
        arr[j] = s;
    }






    //3.1.c)
    /*
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
