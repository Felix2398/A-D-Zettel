import java.util.Random;

public class SearchTools {

    public static void main(String[] args) {
        int[] arr = SortTools.createSequenceInc(100_000);
        Random rn = new Random();

        long time = 0;
        for (int i = 0; i < 500; i++) {
            int value = rn.nextInt(1, arr.length + 1);
            long start = System.nanoTime();
            binSearchNew(arr, value, 0, arr.length - 1);
            time += System.nanoTime() - start;
        }
        System.out.println(time);

        /*
        Search for -5
                        LinSearch             BinSearch       BinSearchNew
            100.000:        12.452.813        192.905         195.308
          1.000.000:        97.259.509        195.904         213.210
        100.000.000:    13.425.821.486        220.502         294.005
        685.154.321:    91.884.438.294        279.994         269.885

        Search a random number
                        LinSearch             BinSearch       BinSearchNew
            100.000:        12.203.201        231.607           337.198
          1.000.000:       132.186.012        222.096           419.909
        100.000.000:    13.259.535.704        308.591         1.144.683
        685.154.321:    82.477.213.696        786.302         1.221.615

         */
    }

    public static int linSearch(int[] arr, int x) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == x) {
                return i;
            }
        }
        return -1;
    }

    public static int binSearch(int[] arr, int x, int l, int r) {
        if (l > r) { return -1; }

        int a = (l + r) / 2;
        if (arr[a] == x) { return a; }
        else if (arr[a] > x) { return binSearch(arr, x, l, a - 1); }
        else { return binSearch(arr, x, a + 1, r); }
    }

    public static int binSearchNew(int[] arr, int x, int l, int r) {
        if (r <= l) {
            return -1;
        }
        int ml = (r - l) / 3 + l;
        int mr = l + ((r - l) / 3) + 1;

        if (arr[ml] == x) {
            return ml;
        } else if (arr[mr] == x) {
            return mr;
        } else if (x < arr[ml]) {
            return binSearchNew(arr, x, l, ml);
        } else if (x > arr[ml] && x < arr[mr]){
            return binSearchNew(arr, x, ml + 1, mr);
        } else {
            return binSearchNew(arr, x, mr + 1, r);
        }
    }
}
