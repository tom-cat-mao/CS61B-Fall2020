package bearmaps.proj2d;

import bearmaps.proj2ab.Point;
import bearmaps.proj2ab.PointSet;
import java.util.List;

public class KDTree implements PointSet {
    private Node root;

    /**
     * Instantiates a new KDTree with a list of points.
     * @param points A list of points to add to the KDTree.
     * @param lon The longitude of the point to find the nearest neighbor of.
     * @param lat The latitude of the point to find the nearest neighbor of.
     */
    public KDTree(List<Point> points, double lon, double lat) {
        root = new Node(new Point(lon, lat), true);
    }

    /**
     * A private class representing a node in the KDTree.
     */
    private class Node {
        private Point point;
        private Node left;
        private Node right;
        private boolean isVertical;

        public Node(Point point, boolean isVertical) {
            this.point = point;
            this.isVertical = isVertical;
        }
    }

    private void insert(Node node, boolean isVertical) {
        return;
    }

    /**
     * Returns the point in this set closest to the given point (x, y).
     * @param x x-coordinate of the point to find the nearest neighbor of.
     * @param y y-coordinate of the point to find the nearest neighbor of.
     * @return The point in this set closest to (x, y).
     */
    public Point nearest(double x, double y) {
        return null;
    }
}