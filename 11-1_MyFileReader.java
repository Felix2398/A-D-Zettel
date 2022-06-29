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

    public static void fileToGraph(UndirectedGraph graph, String fileName) {
        ArrayList<String> arrayList = fileToList(fileName);
        if (arrayList != null) {
            for (String line : arrayList) {
                createEdgeFormLine(line, graph);
            }
        }
    }

    public static void createEdgeFormLine(String line, UndirectedGraph graph) {
        int middle = line.indexOf(' ');
        int i = Integer.parseInt(line.substring(0, middle));
        int j = Integer.parseInt(line.substring(middle +1));
        graph.addEdge(i,j);
    }
}
