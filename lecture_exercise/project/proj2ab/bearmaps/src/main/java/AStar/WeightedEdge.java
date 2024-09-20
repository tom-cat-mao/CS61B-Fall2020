package main.java.AStar;

import main.java.AStar.Vertex;

public class WeightedEdge<Vertex> {

    private Vertex from;
    private Vertex to;
    private double weight;

    /** The source of this edge. */
    public Vertex from() {
        return from;
    }

    /** The destination of this edge. */
    public Vertex to() {
        return to;
    }

    /** The weight of this edge. */
    public double weight() {
        return weight;
    }
}