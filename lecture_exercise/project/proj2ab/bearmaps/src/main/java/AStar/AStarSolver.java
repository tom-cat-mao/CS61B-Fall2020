package main.java.AStar;

import java.util.*;
import main.java.AStar.*;
import main.java.ArrayHeapMinPQ.ArrayHeapMinPQ;
import main.java.ExtrinsicMinPQ.ExtrinsicMinPQ;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {

    /* the constructor of AStarSolver */
    public AStarSolver(
        AStarGraph<Vertex> input,
        Vertex start,
        Vertex goal,
        double timeout
    ) {
        /* the priority queue
         *  to save the unsolved vertices
         */
        ExtrinsicMinPQ<Vertex> pq = new ArrayHeapMinPQ<>();
        List<Vertex> visited = new ArrayList<>();
        Map<Vertex, Vertex> edgeTo = new HashMap<>();
        Map<Vertex, Double> distTo = new HashMap<>();

        pq.add(start, 0);

        distTo.put(start, 0.0);
        edgeTo.put(start, null);

        Vertex current = null;

        while (current != goal) {
            if (pq.size() == 0) {
                return;
            }

            current = (Vertex) pq.removeSmallest();
            visited.add(current);

            if (current == goal) {
                break;
            }

            for (WeightedEdge<Vertex> e : input.neighbors(current)) {
                relax(e, visited, pq, input, edgeTo, distTo);
            }
        }
    }

    public void relax(
        WeightedEdge<Vertex> e,
        List<Vertex> visited,
        ExtrinsicMinPQ<Vertex> pq,
        AStarGraph<Vertex> input,
        Map<Vertex, Vertex> edgeTo,
        Map<Vertex, Double> distTo
    ) {
        Vertex p = e.from();
        Vertex q = e.to();
        double w = e.weight();

        if (visited.contains(q)) {
            return;
        }

        if (!distTo.containsKey(q)) {
            distTo.put(q, Double.POSITIVE_INFINITY);
        }

        if (distTo.get(p) + w < distTo.get(q)) {
            distTo.put(q, distTo.get(p) + w);
            edgeTo.put(q, p);

            if (pq.contains(q)) {
                pq.changePriority(
                    q,
                    distTo.get(q) + input.estimatedDistanceToGoal(q, q)
                );
            } else {
                pq.add(q, distTo.get(q) + input.estimatedDistanceToGoal(q, q));
            }
        }
    }

    /* outcome return SolverOutCome.SolVED, SolverOutCome.TIMEOUT, SolverOutCome.UNSOLVABLE */
    @Override
    public SolverOutcome outcome(int i) {
        return switch (i) {
            case 0 -> SolverOutcome.SOLVED;
            case 1 -> SolverOutcome.TIMEOUT;
            case 2 -> SolverOutcome.UNSOLVABLE;
            default -> null;
        };
    }

    /* a list of vertices on the shortest path from start to goal */
    @Override
    public List<Vertex> solution() {
        return null;
    }

    /* the total weight of the solution */
    @Override
    public double solutionWeight() {
        return 0;
    }

    /* the total number of priority queue dequeue operations */
    @Override
    public int numStatesExplored() {
        return 0;
    }

    /* the total time spent in seconds */
    @Override
    public double explorationTime() {
        return 0;
    }
}
