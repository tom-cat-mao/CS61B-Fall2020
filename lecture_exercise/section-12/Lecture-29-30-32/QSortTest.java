import static org.junit.Assert.*;
import org.junit.Test;
import java.util.Arrays;

public class QSortTest {

    @Test
    public void testQSortMoreComplex() {
        int[] array = {17, -15, 19, 32, 2, 26, 41, 17, 17, -32, 0, 5, -1, 3, 3, 3, 100, -100, 50, 50, 50, 0, 0, 0,
                       999, -999, 123, -123, 456, -456, 789, -789, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        int[] expected = {-999, -789, -456, -123, -100, -32, -15, -1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                          2, 3, 3, 3, 5, 17, 17, 17, 19, 26, 32, 41, 50, 50, 50, 100, 123, 456, 789, 999};

        QSort.Q_sort(array);

        assertArrayEquals(expected, array);
    }
}