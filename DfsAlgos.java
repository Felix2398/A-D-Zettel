import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class DfsAlgos {

    private enum Color {
        WHITE, GREY, BLACK
    }

    private static class Edge {
        Integer i;
        Integer j;
        Edge(Integer i, Integer j) {
            this.i = i;
            this.j = j;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            } else if (!(obj instanceof Edge edge)) {
                return false;
            } else {
                return this.i.equals(edge.i) && this.j.equals(edge.j);
            }
        }

        @Override
        public int hashCode() {
            return 31 * this.i + this.j;
        }

        @Override
        public String toString() {
            return this.i + "->" + this.j;
        }
    }


    public static LinkedList<Integer> topSort(DirectedGraph g) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        HashMap<Integer, Color> vertexColor = new HashMap<>();
        HashMap<Edge, Color> edgeColor = new HashMap<>();

        for (Integer v : g.adjacencyList.keySet()) {
            vertexColor.put(v, Color.WHITE);
            for (Integer u : g.adjacencyList.get(v)) {
                edgeColor.put(new Edge(v, u), null);
            }
        }

        dfs(g, vertexColor, edgeColor, linkedList);

        for (Color color : edgeColor.values()) {
            if (color == Color.GREY) {
                return null;
            }
        }
        return linkedList;
    }

    public static LinkedList<Integer> detectCycle(DirectedGraph g) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        HashMap<Integer, Color> vertexColor = new HashMap<>();
        HashMap<Edge, Color> edgeColor = new HashMap<>();

        for (Integer v : g.adjacencyList.keySet()) {
            vertexColor.put(v, Color.WHITE);
            for (Integer u : g.adjacencyList.get(v)) {
                edgeColor.put(new Edge(v, u), null);
            }
        }

        dfs(g, vertexColor, edgeColor, linkedList);

        vertexColor.replaceAll((k,v) -> v = Color.WHITE);
        linkedList.clear();

        for (Edge edge : edgeColor.keySet()) {
            if (edgeColor.get(new Edge(edge.i, edge.j)) == Color.GREY) {
                return dfsVisit(g, edge.j, edge.i, vertexColor, linkedList);
            }
        }

        return null;
    }

    private static void dfs(DirectedGraph graph, HashMap<Integer, Color> vertexColor,
                            HashMap<Edge, Color> edgeColor, LinkedList<Integer> linkedList) {

        for (Integer v : graph.adjacencyList.keySet()) {
            if(vertexColor.get(v) == Color.WHITE) {
                dfsVisit(graph, v, vertexColor, edgeColor, linkedList);
            }
        }
    }

    private static void dfsVisit(DirectedGraph graph, Integer u, HashMap<Integer, Color> vertexColor,
                                 HashMap<Edge, Color> edgeColor, LinkedList<Integer> linkedList) {
        
        vertexColor.replace(u, Color.GREY);
        for (Integer v : graph.adjacencyList.get(u)) {
            edgeColor.replace(new Edge(u, v), vertexColor.get(v));
            if (vertexColor.get(v) == Color.WHITE) {
                dfsVisit(graph, v, vertexColor, edgeColor, linkedList);
            }
        }
        vertexColor.replace(u, Color.BLACK);
        linkedList.addFirst(u);
    }

    private static LinkedList<Integer> dfsVisit(DirectedGraph graph, Integer u, Integer v,
                                                HashMap<Integer, Color> vertexColor, LinkedList<Integer> linkedList) {

        if (u.equals(v)) {
            linkedList.add(u);
            return linkedList;
        }

        vertexColor.replace(u, Color.GREY);
        linkedList.add(u);

        ArrayList<LinkedList<Integer>> arrayListOfLists = new ArrayList<>();

        for (Integer w : graph.adjacencyList.get(u)) {
            if (vertexColor.get(w) == Color.WHITE) {
                arrayListOfLists.add(dfsVisit(graph, w, v, vertexColor, linkedList));
            }
        }

        for (LinkedList<Integer> list : arrayListOfLists) {
            if (list != null && list.contains(v)) {
                return list;
            }
        }

        return null;
    }
}
