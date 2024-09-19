import static org.junit.Assert.*;
import org.junit.Test;

public class PartitioningTest {

    @Test
    public void testLTHS() {
        int[] array = {17, 15, 19, 32, 2, 26, 41, 17, 17};
        int[] expected = {2, 15, 17, 17, 17, 26, 41, 32, 19};

        Partitioning.LTHS(array, 0, array.length - 1);

        assertArrayEquals(expected, array);
    }
}