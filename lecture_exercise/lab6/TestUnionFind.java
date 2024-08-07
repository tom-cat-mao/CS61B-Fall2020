/* test the union find data structure with junit4 */

import org.junit.Test;
import static org.junit.Assert.*;

public class TestUnionFind {
    @Test
    public void testUnionFind() {
        UnionFind uf = new UnionFind(5);
        uf.connect(0, 1);
        uf.connect(1, 2);
        uf.connect(3, 4);

        assertTrue(uf.isConnected(0, 2));
        assertFalse(uf.isConnected(1, 3));
        assertFalse(uf.isConnected(0, 4));

        uf.connect(0, 4);

        assertTrue(uf.isConnected(0, 3));
        assertTrue(uf.isConnected(1, 4));

    }

}