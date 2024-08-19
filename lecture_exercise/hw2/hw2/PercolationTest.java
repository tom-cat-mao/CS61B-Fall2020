package  hw2;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class PercolationTest {

    private Percolation percolation;

    @Before
    public void setUp() {
        percolation = new Percolation(5);
    }

    @Test
    public void testInitialization() {
        assertNotNull(percolation);
    }

    @Test
    public void testOpen() {
        percolation.open(0, 0);
        assertTrue(percolation.isOpen(0, 0));
    }

    @Test
    public void testIsOpen() {
        assertFalse(percolation.isOpen(1, 1));
        percolation.open(1, 1);
        assertTrue(percolation.isOpen(1, 1));
    }

    @Test
    public void testIsFull() {
        percolation.open(0, 0);
        assertTrue(percolation.isFull(0, 0));
        percolation.open(1, 0);
        assertTrue(percolation.isFull(1, 0));
    }

    @Test
    public void testNumberOfOpenSites() {
        assertEquals(0, percolation.numberOfOpenSites());
        percolation.open(0, 0);
        assertEquals(1, percolation.numberOfOpenSites());
    }

    @Test
    public void testPercolates() {
        assertFalse(percolation.percolates());
        for (int i = 0; i < 5; i++) {
            percolation.open(i, 0);
        }
        assertTrue(percolation.percolates());
    }
}