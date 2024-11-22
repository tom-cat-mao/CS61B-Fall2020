/* this is a AStarSolver for the STP in a graph */
package bearmaps.proj2c;

import edu.princeton.cs.algs4.Stopwatch;
import java.util.*;

public class AStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {

    private double timeSpent; // the whole time spent
    private SolverOutcome outcome; // the outcome of the solution use enum to specifiy
    private double solutionWeight; // the total weight of the solution path
    private List<Vertex> solution; // the solution
    private int numStates; // the number of the operations

    public AStarSolver(
        AStarGraph<Vertex> input,
        Vertex start,
        Vertex end,
        double timeout
    ) {
        solution = new ArrayList<>();
        ExtrinsicMinPQ<Vertex> pq = new ArrayHeapMinPQ<>(); //   the priority query to store the current compatitors
        Map<Vertex, Double> disTo = new HashMap<>();
        List<Vertex> visited = new ArrayList<>(); // the visited vertices
        Map<Vertex, Vertex> path = new HashMap<>(); // the whole path
        Stopwatch sw = new Stopwatch();
        timeSpent = 0.0;
        numStates = -1;

        disTo.put(start, 0.0);
        pq.add(start, 0.0);

        while (pq.size() != 0) {
            /* pop the smallest vertex from the priority queue */
            Vertex current = pq.removeSmallest();
            numStates++;

            /* if find the end vetex,
             * outcome solved and get the solutionWeight
             */
            if (current.equals(end)) {
                outcome = SolverOutcome.SOLVED;
                solutionWeight = disTo.get(end);
                for (Vertex v = end; v != null; v = path.get(v)) {
                    solution.addFirst(v);
                }
                return;
            }
            visited.add(current); // add the current vertex to the visited list

            /* relax all the unvisited neighbors */
            for (WeightedEdge<Vertex> neighbor : input.neighbors(current)) {
                if (!visited.contains(neighbor.to())) {
                    relax(input, neighbor, disTo, pq, path, end);
                    timeSpent += sw.elapsedTime() / 1000; // add the timespent
                    /* if the time spent is greater than the timeout,
                     * outcome is timeout
                     */
                    if (timeSpent > timeout) {
                        outcome = SolverOutcome.TIMEOUT;
                        return;
                    }
                }
            }
        }

        outcome = SolverOutcome.UNSOLVABLE; // if no solution, outcome is unsolvable
    }

    private void relax(
        AStarGraph<Vertex> input,
        WeightedEdge<Vertex> e,
        Map<Vertex, Double> disTo,
        ExtrinsicMinPQ<Vertex> pq,
        Map<Vertex, Vertex> path,
        Vertex goal
    ) {
        Vertex p = e.from(); // the vertex that the edge come from
        Vertex q = e.to(); // the vertex that the edge point to
        double w = e.weight(); // the weight or priority of the edge

        /* if the vertex is not in the disTo map
         * put it in the map
         * and give it the max double value
         */
        if (!disTo.containsKey(q)) {
            disTo.put(q, Double.MAX_VALUE);
        }

        /* if current distance to q is bigger than the relax path
         * calculate the distance
         * reset to the smaller one
         * and put it into the path
         */
        if (disTo.get(p) + w < disTo.get(q)) {
            disTo.put(q, disTo.get(p) + w);
            path.put(q, p);

            /* if the priority queue has the element q
             * then change the priority
             * else add the current distance plus the estimatedDistancetoFoal
             */
            if (pq.contains(q)) {
                pq.changePriority(
                    q,
                    disTo.get(q) + input.estimatedDistanceToGoal(q, goal)
                );
            } else {
                pq.add(
                    q,
                    disTo.get(q) + input.estimatedDistanceToGoal(q, goal)
                );
            }
        }
    }

    @Override
    public SolverOutcome outcome() {
        return outcome;
    }

    @Override
    public List<Vertex> solution() {
        return solution;
    }

    @Override
    public double solutionWeight() {
        return solutionWeight;
    }

    @Override
    public int numStatesExplored() {
        return numStates;
    }

    @Override
    public double explorationTime() {
        return timeSpent;
    }
}
