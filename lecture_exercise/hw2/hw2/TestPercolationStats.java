package hw2;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import static org.junit.Assert.*;

public class TestPercolationStats {

    PercolationStats ps;
    @Before
    public void setUp() {
        ps = new PercolationStats(20, 100);
    }

    @Test
    public void testMean() {
        assertEquals(0.592, ps.mean(), 0.01);
    }

    /* test the standard deviation but not correct*/
    @Test
    public void testStddev() {
        assertEquals(0.008, ps.stddev(), 0.01);
    }

    /* test the confidenceLow but not correct*/
    @Test
    public void testConfidenceLow() {
        assertEquals(0.591, ps.confidenceLow(), 0.01);
    }

    /* test the confidenceHigh but not correct*/
    @Test
    public void testConfidenceHigh() {
        assertEquals(0.593, ps.confidenceHigh(), 0.01);
    }

}