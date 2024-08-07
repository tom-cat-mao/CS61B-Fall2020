import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Created by hug.
 */
public class TestRedBlackFloorSet {
    @Test
     /* Generate 1,000,000 random doubles between -5000 and 5000, and add them to both an AListFloorSet and a RedBlackFloorSet.
       * Repeats the same random call 100,000 times to the floor method of the AListFloorSet and RedBlackFloorSet. Use assertEquals to compare the results. Note that since we’re using doubles, you’ll need to specify a tolerance, e.g. if you pick 0.000001, then two doubles will be considered equal so long as they are within 0.000001 of each other.
     */
    public void randomizedTest() {
       // TODO: YOUR CODE HERE
        AListFloorSet als = new AListFloorSet();
        RedBlackFloorSet rbfs = new RedBlackFloorSet();
        for (int i = 0; i < 1000000; i++) {
            double random = StdRandom.uniform(-5000, 5000);
            als.add(random);
            rbfs.add(random);
        }
        for (int i = 0; i < 100000; i++) {
            double random = StdRandom.uniform(-5000, 5000);
            assertEquals(als.floor(random), rbfs.floor(random), 0.000001);
        }
    }
}
