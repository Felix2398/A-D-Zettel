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

    public static void fileToGraph(DirectedGraph graph, String fileName) {
        ArrayList<String> arrayList = fileToList(fileName);
        if (arrayList != null) {
            for (String line : arrayList) {
                createEdgeFormLine(line, graph);
            }
        }
    }

    public static void createEdgeFormLine(String line, DirectedGraph graph) {
        try {
            String[] strings = line.split(" ", 2);
            strings[0] = strings[0].replaceAll(" ", "");
            strings[1] = strings[1].replaceAll(" ", "");

            int i = Integer.parseInt(strings[0]);
            int j = Integer.parseInt(strings[1]);

            graph.addEdge(i,j);
        } catch (NumberFormatException e) {
            System.out.println("Format error for line: " + line);
        }

    }
}
