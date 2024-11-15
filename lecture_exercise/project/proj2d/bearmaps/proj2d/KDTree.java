/**
 * A class representing a KDTree.
 */
package bearmaps.proj2d;

import bearmaps.proj2ab.Point;
import bearmaps.proj2ab.PointSet;
import java.util.Comparator;
import java.util.List;

public class KDTree implements PointSet {

    private KDNode root; // the root of the KDTree
    private static final boolean VERTICAL = true; // a boolean indicating whether the node is vertical

    /**
     * Instantiates a new KDTree.
     */
    public KDTree() {
        root = null;
    }

    /**
     * Instantiates a new KDTree with a list of points.
     * @param points A list of points to add to the KDTree.
     */
    public KDTree(List<Point> points) {
        for (Point point : points) {
            insert(point);
        }
    }

    /**
     * A private class representing a node in the KDTree.
     */
    private static class KDNode {

        private Point point;
        private KDNode left;
        private KDNode right;
        private boolean isVertical;

        public KDNode(Point point, boolean isVertical) {
            this.isVertical = isVertical;
            this.point = point;
        }

        public double getX() {
            return point.getX();
        }

        public double getY() {
            return point.getY();
        }

        public static double distance(KDNode a, KDNode b) {
            return Point.distance(a.point, b.point);
        }
    }

    /**
     * Compares two KDNodes.
     * @param a The first KDNode to compare which decides the order.
     * @param b The second KDNode to compare.
     * @return The difference between the two KDNodes.
     */
    private Double compare(KDNode a, KDNode b) {
        if (a.isVertical) {
            return a.getX() - b.getX();
        } else {
            return a.getY() - b.getY();
        }
    }

    /**
     * Inserts a point into the KDTree.
     * @param point The point to insert.
     */
    public void insert(Point point) {
        root = insert_p(root, point, VERTICAL);
    }

    /**
     * private method to insert a point into the KDTree.
     * @param root The root of the KDTree to insert the point into.
     * @param point The point to insert.
     * @param isVertical A boolean indicating whether the node is vertical.
     * @return The root of the KDTree with the point inserted.
     */
    private KDNode insert_p(KDNode root, Point point, boolean isVertical) {
        /* if find the leaf node, insert the point */
        if (root == null) {
            return new KDNode(point, isVertical);
        }

        /* if it's vertical
         * compare its lontitude
         * else compare its latitude
         */
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
    @Override
    public Point nearest(double x, double y) {
        return null;
    }

    /**
     * private method to find the nearest point in the KDTree.
     * @param best The best point found so far.
     * @return The nearest point in the KDTree.
     */
    private KDNode nearest_p(KDNode root, KDNode goal, KDNode best) {
        KDNode goodSide; // the good side of the KDNode
        KDNode badSide; // the bad side of the KDNode

        /* if get to the leaf node, return the point */
        if (root == null) {
            return best;
        }

        /* if the current point is closer to the goal than the best point
         * update the best point
         */
        if (KDNode.distance(root, goal) < KDNode.distance(best, goal)) {
            best = root;
        }

        /* split the KDTree into two sides
         * if the goal is on the good side
         * search the good side first
         * then search the bad side
         */
        if (compare(root, goal) > 0) {
            goodSide = root.left;
            badSide = root.right;
        } else {
            goodSide = root.right;
            badSide = root.left;
        }

        best = nearest_p(goodSide, goal, best);

        /* if the distance between the goal and the best point is greater than the distance between the goal and the current point
         * search the bad side
         */
        if (KDNode.distance(best, goal) > Math.pow(compare(root, goal), 2)) {
            best = nearest_p(badSide, goal, best);
        }
        return best;
    }
}
