import org.junit.Test;
import static org.junit.Assert.*;

public class HeapBSTTest {

    @Test
    public void testInsert() {
        HeapBST<Integer> heap = new HeapBST<>(10);
        heap.insert(5);
        heap.insert(3);
        heap.insert(8);
        heap.insert(1);

        assertEquals(Integer.valueOf(1), heap.pop());
        assertEquals(Integer.valueOf(3), heap.pop());
        assertEquals(Integer.valueOf(5), heap.pop());
        assertEquals(Integer.valueOf(8), heap.pop());
    }

    @Test
    public void testPop() {
        HeapBST<Integer> heap = new HeapBST<>(10);
        heap.insert(5);
        heap.insert(3);
        heap.insert(8);
        heap.insert(1);

        assertEquals(Integer.valueOf(1), heap.pop());
        assertEquals(Integer.valueOf(3), heap.pop());
        assertEquals(Integer.valueOf(5), heap.pop());
        assertEquals(Integer.valueOf(8), heap.pop());
        assertNull(heap.pop());
    }
}