/**
 * TODO: Fill in the add and floor methods.
 */
public class AListFloorSet implements Lab5FloorSet {
    AList<Double> items;

    public AListFloorSet() {
        items = new AList<>();
    }

    /* adds x to the set, if x is already in the set, does nothing */
    public void add(double x) {
        items.addLast(x);
    }

    public double floor(double x) {
        double floor = Double.NEGATIVE_INFINITY;

        for (int i = 0; i < items.size(); i++) {
            if (items.get(i) <= x && items.get(i) > floor) {
                floor = items.get(i);
            }
        }

        return floor;
    }
}
