import java.util.Arrays;
import java.util.Collections;

public class StockProfit {

    public static void main(String[] args) {
        int[] arr = {5,2,9,1,6,4,7,8};
        System.out.println(trade(arr, 0, arr.length-1));
    }

    public static int trade(int[] arr, int p, int r) {
        if (p == r) {
            return 0;
        } else {
            int q = (p+r) / 2;
            int t1 = trade(arr, p, q);
            int t2 = trade(arr, q +1, r);
            return maxWin(arr, p, r, q, t1, t2);
        }
    }

    private static int maxWin(int[] arr, int p, int r, int q, int t1, int t2) {
        int minValueLeft = findMin(Arrays.copyOfRange(arr, p,q + 1));
        int maxValueRight = findMax(Arrays.copyOfRange(arr, q +1,r + 1));
        int s = maxValueRight - minValueLeft;
        return Integer.max(Integer.max(t1, t2), s);
    }

    private static int findMax(int[] arr) {
        int max = arr[0];
        for (int value : arr) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }

    private static int findMin(int[] arr) {
        int min = arr[0];
        for (int value : arr) {
            if (value < min) {
                min = value;
            }
        }
        return min;
    }
}
