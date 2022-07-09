import java.util.HashMap;
import java.util.Iterator;
import java.util.Stack;

public class DfsIterator implements Iterator<Integer> {
    int time;
    DirectedGraph graph;
    HashMap<Integer, Color> color;
    HashMap<Integer, Integer> dTime;
    HashMap<Integer, Integer> fTime;
    Stack<Integer> stack;

    DfsIterator(DirectedGraph graph, Integer s) {
        this.graph = graph;
        this.time = 0;
        this.color = new HashMap<>();
        this.dTime = new HashMap<>();
        this.fTime = new HashMap<>();
        this.stack = new Stack<>();

        for (Integer vertex : graph.adjacencyList.keySet()) {
            color.put(vertex, Color.WHITE);
            dTime.put(vertex, null);
            fTime.put(vertex, null);
        }

        stack.push(s);
    }

    @Override
    public boolean hasNext() {
        return !stack.empty();
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            return null;
        }

        Integer v = stack.pop();

        if (color.get(v) == Color.WHITE) {
            color.replace(v, Color.GREY);
            for (Integer u : graph.adjacencyList.get(v)) {
                if (color.get(u) == Color.WHITE) {
                    stack.push(u);
                }
            }
        }

        if (color.get(v) != Color.BLACK) {
            color.replace(v, Color.BLACK);
            return v;
        } else {
            return next();
        }


    }

    private enum Color {
        WHITE, GREY, BLACK
    }

    public String printTimes() {
        StringBuilder sb = new StringBuilder();
        for (Integer vertex : graph.adjacencyList.keySet()) {
            sb.append(vertex).append(": ");
            sb.append(dTime.get(vertex)).append(", ");
            sb.append(fTime.get(vertex)).append("\n");
        }
        return sb.toString();
    }







}
