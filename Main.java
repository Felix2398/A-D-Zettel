public class Main {
    public static void main(String[] args) {
        String[] fileNames = {"outbnet.txt", "outmaayan-figeys.txt", "outmoreno_dense_comm.txt",
                "outmoreno_taro_taro.txt", "outsimple.txt"};

        for (String fileName : fileNames) {
            System.out.println(fileName);
            DirectedGraph g = new DirectedGraph();
            MyFileReader.fileToGraph(g, fileName);

            long start = System.nanoTime();
            System.out.println("TopSort: " + DfsAlgos.topSort(g));
            System.out.println("DetectCycle: " + DfsAlgos.detectCycle(g));
            long time = System.nanoTime() - start;

            System.out.println(time + " ns");
            System.out.println();
        }

        /*
        outbnet.txt
        TopSort: [2, 10, 4, 1, 9, 7, 3, 5, 6, 8]
        DetectCycle: null
        2912600 ns

        outmaayan-figeys.txt
        TopSort: null
        DetectCycle: [470, 523, 471]
        12856100 ns

        outmoreno_dense_comm.txt
        TopSort: [12, 26, 38, 23, 39, 2, 54, 31, 40, 18, 49, 3, 44, 55, 8, 50, 41, 24, 27, 43, 30, 9, 20, 19, 16, 10, 17, 32, 46, 28, 52, 13, 29, 15, 25, 42, 14, 51, 34, 35, 45, 53, 33, 36, 37, 4, 6, 7, 11, 5, 47, 22, 48, 21, 1]
        DetectCycle: null
        1097100 ns

        outmoreno_taro_taro.txt
        TopSort: null
        DetectCycle: [21, 12, 4, 22, 13, 18, 20, 16, 20, 14, 15, 17, 10, 9, 3, 7, 8, 11, 6, 5, 2, 1, 19, 20]
        179800 ns

        outsimple.txt
        TopSort: [111, 106, 100, 96, 93, 62, 90, 71, 43, 31, 48, 101, 107, 85, 108, 58, 102, 16, 38, 55, 104, 51, 50, 78, 29, 79, 23, 53, 64, 33, 46, 110, 84, 112, 37, 63, 49, 65, 39, 17, 56, 41, 86, 9, 24, 69, 70, 74, 3, 34, 32, 89, 7, 25, 14, 15, 52, 68, 99, 22, 75, 5, 73, 60, 26, 57, 36, 54, 81, 77, 2, 1, 10, 61, 28, 103, 13, 6, 27, 82, 20, 83, 11, 44, 76, 109, 40, 98, 94, 105, 80, 35, 95, 18, 12, 21, 4, 8, 45, 72, 92, 42, 67, 88, 87, 66, 91, 30, 59, 97, 47, 19]
        DetectCycle: null
        2766400 ns
         */

    }
}
