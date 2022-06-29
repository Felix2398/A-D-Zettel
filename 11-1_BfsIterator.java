import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;

public class BfsIterator {

    private final UndirectedGraph g;
    private final HashMap<Integer, VertexData> vertexDataHashMap = new HashMap<>();
    private final PriorityQueue<Integer> queue = new PriorityQueue<>();

    public BfsIterator(UndirectedGraph g, Integer s) {
        this.g = g;

        if (!g.adjacencyList.containsKey(s)) {
            System.out.println("Error: Start vertex doesn't exist");
        }

        for (Integer vertex : g.adjacencyList.keySet()) {
                this.vertexDataHashMap.put(vertex, new VertexData(vertex, Color.WHITE, -1));
        }

        VertexData sData = vertexDataHashMap.get(s);
        sData.color  = Color.GREY;
        sData.dist = 0;
        queue.add(s);
    }

    private enum Color{
        WHITE, GREY, BLACK
    }

    private static class VertexData {
        Integer vertex;
        Color color;
        Integer dist;

        VertexData(Integer vertex, Color color, Integer dist) {
            this.vertex = vertex;
            this.color = color;
            this.dist = dist;
        }
    }

    public Integer next() {
        VertexData uData = vertexDataHashMap.get(queue.poll());
        LinkedList<Integer> vertices = g.adjacencyList.get(uData.vertex);

        for (Integer v : vertices) {
            VertexData vData = vertexDataHashMap.get(v);
            if (vData.color == Color.WHITE) {
                vData.color = Color.GREY;
                vData.dist = uData.dist + 1;
                this.queue.add(v);
            }
        }

        uData.color = Color.BLACK;
        return this.queue.peek();
    }

    public Integer dist(Integer v) {
        return vertexDataHashMap.get(v).dist;
    }
}
