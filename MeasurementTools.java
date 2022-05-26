import java.util.Arrays;

public class MeasurementTools {

    final int repeats;
    final int[] elements;

    MeasurementTools(int repeats, int[] elements) {
        this.repeats = repeats;
        this.elements = Arrays.copyOf(elements, elements.length);
    }

    public enum Sorts {
        QUICKSORT, QUICKSORT_RANDOM, QUICKSORT_NEW_RANDOM, QUICKSORT_TRI_RANDOM, QUICKSORT_TRI_NEW_RANDOM
    }

    public enum Creates {
        INC, DEC, RAND
    }

    public void startMeasurement(Sorts sorts, Creates creates) {
        System.out.println(sorts.name() + " " + creates.name());
        for (int element : elements) {
            try {
                long time = measureTime(sorts, creates, element);
                System.out.format("%,7d: %,12d ns\n", element, time);
            } catch (StackOverflowError stackOverflowError) {
                System.out.format("StackOverFlowError for %,7d\n", element);
            }
        }
        System.out.println();
    }

    private long measureTime(Sorts sorts, Creates creates, int elements) throws StackOverflowError{
        long[] times = new long[repeats];
        for (int i = 0; i < repeats; i++) {
            int[] arr = createArray(creates, elements);
            long start = System.nanoTime();
            callMethod(sorts, arr);
            times[i] = System.nanoTime() - start;
        }

        long average = times[0];
        for (int i = 1; i < times.length; i++) {
            average = (average + times[i]) / 2;
        }
        return average;
    }

    private void callMethod(Sorts sorts, int[] arr) {
        switch (sorts) {
            case QUICKSORT -> SortTools.quickSort(arr, 0, arr.length - 1);
            case QUICKSORT_RANDOM -> SortTools.quickSortRandom(arr, 0, arr.length - 1);
            case QUICKSORT_NEW_RANDOM -> SortTools.quickSortNewRandom(arr, 0, arr.length - 1);
            case QUICKSORT_TRI_RANDOM -> SortTools.quickSortTriRandom(arr, 0, arr.length - 1);
            case QUICKSORT_TRI_NEW_RANDOM -> SortTools.quickSortTriNewRandom(arr, 0, arr.length - 1);
        }
    }

    private int[] createArray(Creates creates, int elements) {
        return switch (creates) {
            case INC -> SortTools.createSequenceInc(elements);
            case DEC -> SortTools.createSequenceDec(elements);
            case RAND -> SortTools.createSequenceRand(elements);
        };
    }
}
