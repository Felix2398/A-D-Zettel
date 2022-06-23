import java.util.ArrayList;

public class Main {
    @SuppressWarnings("all")
    public static void main(String[] args) {
        ArrayList<String> officialWords = MyFileReader.fileToList("OfficialScrabbleWordListGerman.txt");
        ArrayList<String> friendsCheating = MyFileReader.fileToList("AreMyFriendsCheating.txt");
        HashmapForStrings hashmap = new HashmapForStrings(10000);
        hashmap.insertArrayList(officialWords);
        System.out.println(hashmap.getNumberOfElementsNotFound(friendsCheating));
        //1814

        HashmapForStrings h1 = new HashmapForStrings(1000);
        HashmapForStrings h2 = new HashmapForStrings(10000);
        System.out.println(HashmapAnalysisTools.getInsertTime(h1, officialWords));
        System.out.println(HashmapAnalysisTools.getInsertTime(h2, officialWords));
        System.out.println(HashmapAnalysisTools.getSearchTime(h1, friendsCheating));
        System.out.println(HashmapAnalysisTools.getSearchTime(h2, friendsCheating));
        HashmapAnalysisTools.printData(h1);
        HashmapAnalysisTools.printData(h2);
        //Insert m= 1.000: 424.959.900 ns
        //Insert m=10.000: 145.172.500 ns
        //Search m= 1.000:   6.663.200 ns
        //Search m=10.000:  14.714.700 ns
    }
}
