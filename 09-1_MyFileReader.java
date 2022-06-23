import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class MyFileReader {
    static ArrayList<String> fileToList(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new java.io.FileReader(fileName));
            ArrayList<String> arrayList = new ArrayList<>();
            String line = br.readLine();

            while (line != null) {
                arrayList.add(line);
                line = br.readLine();
            }
            return arrayList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
