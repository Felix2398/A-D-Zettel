import java.util.HashMap;
import java.util.LinkedList;

public class DirectedGraph {

    public HashMap<Integer, LinkedList<Integer>> adjacencyList;

    public DirectedGraph() {
        adjacencyList = new HashMap<>();
    }

    public DirectedGraph(Integer n) {
        this();
        for (int i = 0; i < n; i++) {
            this.adjacencyList.put(i + 1, new LinkedList<>());
        }
    }

    public void addVertex(Integer i) {
        if (!adjacencyList.containsKey(i)) {
            this.adjacencyList.put(i, new LinkedList<>());
        } else {
            System.out.println("AddVertexError: Vertex " + i.toString() + " already exists");
        }
    }

    public void addEdge(Integer i, Integer j) {
        if (!this.adjacencyList.containsKey(i)) {
            addVertex(i);
        }
        if (!this.adjacencyList.containsKey(j)) {
            addVertex(j);
        }

        this.adjacencyList.get(i).addFirst(j);
    }

    public void deleteEdge(Integer i, Integer j) {
        boolean iExists = this.adjacencyList.containsKey(i);
        boolean jExists = this.adjacencyList.containsKey(j);

        if (iExists && jExists) {
            this.adjacencyList.get(i).remove(j);
        } else if (!iExists && !jExists) {
            System.out.println("DeleteEdgeError: Vertex " + i.toString() + " and " + j.toString() + " don't exist");
        } else if (!iExists) {
            System.out.println("DeleteEdgeError: Vertex " + i.toString() + " doesn't exist");
        } else {
            System.out.println("DeleteEdgeError: Vertex " + i.toString() + " doesn't exist");
        }
    }

    @Override
    public String toString() {
        return adjacencyList.toString();
    }
}

