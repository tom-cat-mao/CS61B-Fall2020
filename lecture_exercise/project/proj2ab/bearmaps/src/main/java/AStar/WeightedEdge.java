package main.java.AStar;

public class WeightedEdge<Vertex> {

    private Vertex from;
    private Vertex to;
    private double weight;

    public WeightedEdge(Vertex from, Vertex to, double weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

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