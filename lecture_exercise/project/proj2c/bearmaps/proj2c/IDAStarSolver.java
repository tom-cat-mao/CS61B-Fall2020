/* this is the IDAStar algorithm to find even quicker */
package bearmaps.proj2c;

import edu.princeton.cs.algs4.Stopwatch;
import java.util.*;

public class IDAStarSolver<Vertex> {

    private double timeSpent; // the whole time spent
    private SolverOutcome outcome; // the outcome of the solution use enum to specifiy
    private double solutionWeight; // the total weight of the solution path
    private List<Vertex> solution; // the solution
    private int numStates;
    private Vertex goal;
    private final double bound;

    public IDAStarSolver(
        AStarGraph<Vertex> input,
        Vertex start,
        Vertex end,
        double timeout
    ) {
        goal = end;
        Stopwatch sw = new Stopwatch();
        bound = input.estimatedDistanceToGoal(start, end);
        Map<Vertex, Vertex> path = new HashMap<>();
        Stack<Vertex> visited = new Stack<>();
        Map<Vertex, Double> disTo = new HashMap<>();

        disTo.put(start, 0.0);
        visited.add(start);

        while (true) {}
    }

    private void search(
        AStarGraph<Vertex> input,
        WeightedEdge<Vertex> e,
        Map<Vertex, Vertex> path,
        ExtrinsicMinPQ<Vertex> pq,
        Stack<Vertex> visited,
        Map<Vertex, Double> disTo
    ) {}

    private boolean isgoal(Vertex v) {
        return v.equals(goal);
    }
}
