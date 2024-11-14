package bearmaps.proj2d;

import bearmaps.proj2ab.Point;
import bearmaps.proj2ab.PointSet;
import java.util.List;

public class KDTree implements PointSet {
    private Node root; // the root of the KDTree

    /**
     * Instantiates a new KDTree with a list of points.
     * @param points A list of points to add to the KDTree.
     * @param lon The longitude of the point to find the nearest neighbor of.
     * @param lat The latitude of the point to find the nearest neighbor of.
     */
    public KDTree(List<Point> points, double lon, double lat) {
        root = new Node(new Point(lon, lat));

        for (Point point : points) {
            insert(root, point);
        }
    }

    /**
     * A private class representing a node in the KDTree.
     */
    private class Node {
        private Point point;
        private Node left;
        private Node right;

        public Node(Point point) {
            this.point = point;
        }

        public double getX() {
            return point.getX();
        }

        public double getY() {
            return point.getY();
        }
    }

    /**
     * Inserts a point into the KDTree.
     * @param root The root of the KDTree to insert the point into.
     * @param point The point to insert.
     */
    public void insert(Node root, Point point) {
        insert_p(root, point, true);
    }

    /**
     * private method to insert a point into the KDTree.
     * @param root The root of the KDTree to insert the point into.
     * @param point The point to insert.
     * @param isVertical A boolean indicating whether the node is vertical.
     * @return The root of the KDTree with the point inserted.
     */
    private Node insert_p(Node root, Point point, boolean isVertical) {
        if (root == null) {
            return new Node(point);
        }
        if (isVertical) {
            if (point.getX() < root.getX()) {
                root.left = insert_p(root.left, point, !isVertical);
            } else {
                root.right = insert_p(root.right, point, !isVertical);
            }
        } else {
            if (point.getY() < root.getY()) {
                root.left = insert_p(root.left, point, !isVertical);
            } else {
                root.right = insert_p(root.right, point, !isVertical);
            }
        }
        return root;
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

    /**
     * private method to find the nearest point in the KDTree.
     * @param best
     * @return The nearest point in the KDTree.
     */
    private Point nearest_p(Point best) {
        return null;
    }
}