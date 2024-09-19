import static org.junit.Assert.*;
import org.junit.Test;

public class QSelectTest {

    @Test
    public void testQSelectOddLength() {
        int[] array = {-15, 19, 32, 26, 41, -32, 0, 5, -1, 3, 3, 3, 100, -100, 50, 50, 50};
        int expected = 5; // The middle element after sorting would be 5

        int result = QSelect.QSelect(array);

        assertEquals(expected, result);
    }
}