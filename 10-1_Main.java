import java.util.ArrayList;

public class Main {
    public static void main(String[] args)  {
        ArrayList<String> officialWords = MyFileReader.fileToList("OfficialScrabbleWordListGerman.txt");
        ArrayList<String> friendsCheating = MyFileReader.fileToList("AreMyFriendsCheating.txt");

        ArrayList<OpenHashmapForString> hashmaps = new ArrayList<>();
        hashmaps.add(new OpenHashmapForString(200_000, OpenHashmap.Probing.LINEAR));
        hashmaps.add(new OpenHashmapForString(200_000, OpenHashmap.Probing.QUADRATIC));
        hashmaps.add(new OpenHashmapForString(200_000, OpenHashmap.Probing.DOUBLE));
        hashmaps.add(new OpenHashmapForString(1_000_000, OpenHashmap.Probing.LINEAR));
        hashmaps.add(new OpenHashmapForString(1_000_000, OpenHashmap.Probing.QUADRATIC));
        hashmaps.add(new OpenHashmapForString(1_000_000, OpenHashmap.Probing.DOUBLE));

        for (OpenHashmapForString hashmap : hashmaps) {
            long time1 = AnalysisTools.getInsertTime(hashmap, officialWords);
            long time2 = AnalysisTools.getSearchTime(hashmap, friendsCheating);
            int errors = AnalysisTools.getNotInList(hashmap, friendsCheating);

            System.out.format("Probing: %-10s\nm: %,9d\nInsert:%,12d ns\nSearch:%,12d ns\nErrors: %d\n\n",
                    hashmap.probing.name(), hashmap.m, time1, time2, errors);
        }

        /*
        Probing: LINEAR
        m:   200.000
        Insert: 301.722.000 ns
        Search:  47.761.800 ns
        Errors: 1814

        Probing: QUADRATIC
        m:   200.000
        Insert: 109.780.400 ns
        Search:   8.417.900 ns
        Errors: 1814

        Probing: DOUBLE
        m:   200.000
        Insert: 179.167.800 ns
        Search:   8.717.500 ns
        Errors: 1814

        Probing: LINEAR
        m: 1.000.000
        Insert:  49.452.600 ns
        Search:   1.450.100 ns
        Errors: 1814

        Probing: QUADRATIC
        m: 1.000.000
        Insert:  50.675.200 ns
        Search:   1.452.600 ns
        Errors: 1814

        Probing: DOUBLE
        m: 1.000.000
        Insert:  80.200.900 ns
        Search:   2.068.300 ns
         Errors: 1814
         */
    }
}
