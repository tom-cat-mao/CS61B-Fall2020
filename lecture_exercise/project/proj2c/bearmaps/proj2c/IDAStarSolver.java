/* this is the IDAStar algorithm to find even quicker */
package bearmaps.proj2c;

import edu.princeton.cs.algs4.Stopwatch;
import java.util.*;

public class IDAStarSolver<Vertex> implements ShortestPathsSolver<Vertex> {

    private double timeSpent; // the whole time spent
    private SolverOutcome outcome; // the outcome of the solution use enum to specifiy
    private double solutionWeight; // the total weight of the solution path
    private List<Vertex> solution; // the solution
    private int numStates;
    private Vertex goal; // the goal vertex
    private double bound; // the initial bound for the search
    private Stopwatch sw;

    public IDAStarSolver(
        AStarGraph<Vertex> input,
        Vertex start,
        Vertex end,
        double timeout
    ) {
        numStates = 0; // set the number of states explored as 0
        bound = input.estimatedDistanceToGoal(start, end); // set the the initial bound for the search
        goal = end; // set the goal
        sw = new Stopwatch(); // start the timer
        Stack<Vertex> path = new Stack<>(); // the path to the current node worked like a stack
        Map<Vertex, Double> disTo = new HashMap<>(); // store the distance to the current node
        List<Vertex> visited; // store the visited nodes
        double t; // store the current distance to the goal
        outcome = SolverOutcome.UNSOLVABLE; // set the outcome as unsolvable

        disTo.put(start, 0.0); // set the distance to the start node as 0
        path.push(start); // add the start node to the path

        /* search the whole graph with the initial bound
         * if the search time is greater than the timeout, return
         * if the search is unsolved, return
         * else reset the bound and continue the search
         */
        while (true) {
            visited = new ArrayList<>(); // reset the visited list
            /* set t as the whole path weight of the current node */
            t = search(input, start, path, visited, disTo);

            /* if find the goal, return */
            if (outcome == SolverOutcome.SOLVED) {
                timeSpent = sw.elapsedTime(); // get the whole time spent
                solutionWeight = disTo.get(goal); // get the solution weight
                solution = new ArrayList<>(path); // get the solution path
                return;
            }

            /* if the wholw search weight is limitless, return */
            if (t == Double.POSITIVE_INFINITY) {
                outcome = SolverOutcome.UNSOLVABLE;
                return;
            }

            /* if the search time is greater than the timeout, return */
//            if (sw.elapsedTime() > timeout) {
//                outcome = SolverOutcome.TIMEOUT;
//            }

            bound = t; // reset the bound for the next search
        }

    }

    private double search(
        AStarGraph<Vertex> input,
        Vertex start,
        Stack<Vertex> path,
        List<Vertex> visited,
        Map<Vertex, Double> disTo
    ) {
        Vertex node = path.peek(); // store the current node`

        /* store the current distance to the goal */
        double f = disTo.get(node) + input.estimatedDistanceToGoal(start, goal);

        /* if the current distance is greater than the bound, return */
        if (f > bound) {
            return f;
        }

        /* if the current node is visited, return */
        if (visited.contains(node)) {
            return f;
        }

        visited.add(node); // add the current node to the visited list

        /* if find the goal
         * get the solution
         * get the solutionweight
         * and change the outcome to SOLVED
         */
        if (isgoal(node)) {
            solution = new ArrayList<>(path);
            solutionWeight = disTo.get(node);
            outcome = SolverOutcome.SOLVED;
            timeSpent = sw.elapsedTime();
            return f;
        }

        double min = Double.POSITIVE_INFINITY; // set the minimum distance to the goal as positive infinity

        /* search the neighbors of the current node */
        for (WeightedEdge<Vertex> neighbor : input.neighbors(node)) {
            Vertex next = neighbor.to(); // the next node

            path.push(next); // add the next node to the path

            /* if the visited list does not contain the next node
             * or the distance to the next node is greater than the current distance
             * to the next node
             * update the distance to the next node
             */
            if (
                !visited.contains(next) ||
                disTo.get(next) > neighbor.weight() + disTo.get(node)
            ) {
                disTo.put(next, neighbor.weight() + disTo.get(node));
            }

            numStates++; // increment the number of states explored

            /* search the next node with the current bound */
            double t = search(input, next, path, visited, disTo);

            /* if the search is unsolved or timeout, return */
            if (outcome == SolverOutcome.SOLVED) {
                return t;
            }

            /* if it run out of the time , return */
//            if (outcome == SolverOutcome.TIMEOUT) {
//                return t;
//            }

            /* update the minimum distance to the goal */
            min = Math.min(min, t);

            /* remove the next node from the path */
            path.pop();
        }

        /* return the minimum distance to the goal */
        return min;
    }

    private boolean isgoal(Vertex v) {
        return v.equals(goal);
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
