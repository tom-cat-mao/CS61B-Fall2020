/* define the Vertex of the graph */
package main.java.AStar;

import main.java.Point.Point;

public class Vertex {
    private Point p;

    public Vertex(double x, double y) {
        p = new Point(x, y);
    }

    public double getX() {
        return p.getX();
    }

    public double getY() {
        return p.getY();
    }

    public static double distance(Vertex v1, Vertex v2) {
        return Point.distance(v1.p, v2.p);
    }

    @Override
    public boolean equals(Object other) {
        if (other == null) {
            return false;
        }

        return p.equals(other);
    }

    @Override
    public int hashCode() {
        return p.hashCode();
    }

    @Override
    public String toString() {
        return p.toString();
    }
}
