import bearmaps.Point;
import java.util.*;

/* the naivepointset
 * use the loop to find out the specific element
 * the time comsumption is O(n)
 * this is not a perfect algorithom
 * but it is used to test the K-D tree
 */
public class NaivePointSet implements PointSet {
    /* the private array to store the points */
    private Point[] points;

    /* the constructor to build the array */
    public NaivePointSet(List<Point> points){
        this.points = new Point[points.size()];

        int position = 0;
        for (Point p : points){
            this.points[position++] = new Point(p.getX(), p.getY());
        }
    }

    @Override
    public Point nearest(double x, double y){
        Point start = new Point(x, y);
        Point target = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);

        for (Point p : points){
            Point temp = new Point(p.getX(), p.getY());
            target = Point.distance(temp, start) < Point.distance(target, start) ? temp : target;
        }

        return target;
    }
}
