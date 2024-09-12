/* the Topological sort class */
import java.util.*;

public class T_sort {

    public static <T> Stack<T> t_sort(Graph<T> graph) {
        Stack<T> stack = new Stack<>();
        List<T> visited = new ArrayList<>();
        List<T> startNodes = graph.getlevel0();

        for (T node : startNodes) {
            dfs(stack, visited, graph, node);
        }

        return stack;
    }

    private static <T> void dfs(Stack<T> stack, List<T> visited, Graph<T> graph, T node) {
        if (visited.contains(node)) {
            return;
        }
        visited.add(node);
        for (T neighbor : graph.getNeighbors(node).keySet()) {
            dfs(stack, visited, graph, neighbor);
        }
        stack.push(node);
    }

}
