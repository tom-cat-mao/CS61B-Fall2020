/* the Shortest Paths Solver interface */
package main.java.AStar;

public interface ShortestPathsSolver<Vertex> {

    /* the outcome of the solver */
    public enum SolverOutcome {
        SOLVED, TIMEOUT, UNSOLVABLE
    }

    /* the outcome of the solver */
    public SolverOutcome outcome(int i);

    /* the solution of the solver */
    public Iterable solution();

    /* the weight of the solution */
    public double solutionWeight();

    /* the number of states explored */
    public int numStatesExplored();

    /* the time spent in exploration */
    public double explorationTime();
}