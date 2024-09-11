/* a 2D KDTree */
import bearmaps.Point;
import java.util.*;

//package bearmaps;

public class KDTree implements PointSet {

    private KDNode root; // the root of the KD tree
    private static final boolean VERTICAL = true; // default vertical

    /* definite the node of the tree */
    private class KDNode {

        private Point point;
        private KDNode left;
        private KDNode right;
        private boolean isVertical;

        KDNode(Point p, boolean isVertical) {
            this.point = p;
            this.isVertical = isVertical;
        }

        /* get the X positon */
        public double getX() {
            return point.getX();
        }

        /* get the Y position */
        public double getY() {
            return point.getY();
        }

        /* get the distance between the points */
        public static double distance(KDNode p1, KDNode p2) {
            return Point.distance(p1.point, p2.point);
        }
    }

    /* the comparator of the KDNode */
    public class KDNodeComparator implements Comparator<KDNode> {

        @Override
        public int compare(KDNode p1, KDNode p2) {
            /* if the node is vertical
             * compare the X position
             * else compare the Y position
             * return the difference
             */
            if (p1.isVertical) {
                return (int) (p1.getX() - p2.getX());
            } else {
                return (int) (p1.getY() - p2.getY());
            }
        }
    }

    /* constructor
     * set the default orientation to vetical
     */
    public KDTree(List<Point> points) {
        for (Point p : points) {
            root = insert(root, p, VERTICAL);
        }
    }

    /* public insert method */
    public KDNode insert(KDNode root, Point p, boolean isVertical) {
        return insert_helper(root, p, isVertical);
    }

    /* private insert helper method ot insert node in KD mode */
    private KDNode insert_helper(KDNode root, Point p, boolean isVertical) {
        /* if the root is null return the Node */
        if (root == null) {
            return new KDNode(p, isVertical);
        }

        /* if it's vertical, compare the x position
         * put bigger on the right
         * put smaller on the left
         * otherwise compare the y position
         */
        if (isVertical) {
            if (p.getX() > root.getX()) {
                root.right = insert_helper(root.right, p, !isVertical);
            } else {
                root.left = insert_helper(root.left, p, !isVertical);
            }
        } else {
            if (p.getY() > root.getY()) {
                root.right = insert_helper(root.right, p, !isVertical);
            } else {
                root.left = insert_helper(root.left, p, !isVertical);
            }
        }

        return root;
    }

    /* public nearest method */
    public Point nearest(double x, double y) {
        return nearest_helper(
            root,
            new KDNode(new Point(x, y), VERTICAL),
            new KDNode(root.point, VERTICAL),
            VERTICAL
        ).point;
    }

    /* private nearest help method to find the smallest KDNode */
    private KDNode nearest_helper(
        KDNode root,
        KDNode goal,
        KDNode best,
        boolean isVertical
    ) {
        KDNode goodSide; // the goodSide
        KDNode badSide; // the badSide

        /* if get the leaf return the best */
        if (root == null) {
            return best;
        }

        /* if root is nearer change the best to root */
        if (KDNode.distance(root, goal) < KDNode.distance(best, goal)) {
            best = root;
        }

        Comparator<KDNode> comparator = new KDNodeComparator();

        /* find the position of the goal to the root
         * use the orientation to seperate the area and find the good and bad side
         * goodside is nearer to the goal
         */
        if (comparator.compare(root, goal) > 0) {
            goodSide = root.left;
            badSide = root.right;
        } else {
            goodSide = root.right;
            badSide = root.left;
        }

        /* find the best point on the goodSide */
        best = nearest_helper(goodSide, goal, best, !isVertical);

        /* as the comparator between the root and goal is bigger than the shortest distance
         * find the best in the badside
         */
        if (
            KDNode.distance(best, goal) >
            Math.pow(comparator.compare(root, goal), 2)
        ) {
            best = nearest_helper(badSide, goal, best, !isVertical);
        }

        return best;
    }

    /* public find method */
    public boolean find(Point p) {
        return find_helper(root, p, VERTICAL) != null;
    }

    /* private find help method */
    private KDNode find_helper(KDNode root, Point p, boolean isVertical) {
        /* if we get the leaf return */
        if (root == null) {
            return null;
        }

        /* if find the point return the root */
        if (root.point.equals(p)) {
            return root;
        }

        /* if the node is vertical
         * compare the X position and find in the next node
         * otherwise compare the Y position
         */
        if (isVertical) {
            if (p.getX() > root.getX()) {
                return find_helper(root.right, p, !isVertical);
            } else {
                return find_helper(root.left, p, !isVertical);
            }
        } else {
            if (p.getY() > root.getY()) {
                return find_helper(root.right, p, !isVertical);
            } else {
                return find_helper(root.left, p, !isVertical);
            }
        }
    }
}
