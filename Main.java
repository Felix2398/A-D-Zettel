public class Main {
    public static void main(String[] args) {
        /*
        UndirectedGraph graph = new UndirectedGraph();
        MyFileReader.fileToGraph(graph, "out.ucidata-zachary.txt");
        System.out.println(graph);
         */

        UndirectedGraph graph = new UndirectedGraph();
        MyFileReader.fileToGraph(graph, "soc-twitter-follows.txt");
        System.out.println(graph);

        BfsIterator bfsIterator = new BfsIterator(graph, 1);

        Integer i = 0;
        while (i != null) {
            i = bfsIterator.next();
            System.out.println(i);
        }

        for (Integer vertex : graph.adjacencyList.keySet()) {
            System.out.println(bfsIterator.dist(vertex));
        }
    }
}
