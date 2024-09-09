/* a 2D KDTree */
import bearmaps.Point;
import java.util.*;

package bearmaps;

public class KDTree {
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
    }

    public KDTree(List<Point> points) {
        for (Point p : points) {
            root = insert(root, p, VERTICAL);
        }
    }

    public KDNode insert(KDNode node, Point p, boolean isVertical) {
        return null;
    }

    private KDNode insert_helper(KDNode node, Point p, boolean isVertical) {
        return null;
    }

    public Point nearest(double x, double y) {
        return null;
    }

    private Point nearest_helper(KDNode node, Point goal, Point best) {
        return null;
    }

    public Point find(Point p) {
        return null;
    }

    private KDNode find_helper(KDNode node, Point p) {
        return null;
    }
}