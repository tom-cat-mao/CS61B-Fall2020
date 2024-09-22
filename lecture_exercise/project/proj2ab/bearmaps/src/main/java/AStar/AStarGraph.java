package main.java.AStar;

import java.util.*;
import main.java.AStar.*;

public interface AStarGraph<Vertex> {
    /** Provides a list of all edges that go out from v to its neighbors. */
    List<WeightedEdge<Vertex>> neighbors(Vertex v);

    /** Provides an estimate of the number of moves to reach the goal from
     *  the start position. For results to be correct, this estimate must
     *  be less than or equal to the correct distance. */
    double estimatedDistanceToGoal(Vertex s, Vertex goal);

    Set<Vertex> getNodes();

    void addVertex(Vertex node);

    void addEdge(Vertex from, Vertex to, int weight);

    void removeVertex(Vertex node);

    int getNumVertices();

    int getWeight(Vertex from, Vertex to);
}
