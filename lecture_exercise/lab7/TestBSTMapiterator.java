import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.Set;
import org.junit.Test;

/* test the iterator in the BSTMap
 * also test the enhanced for loop in the BSTMap
 */

public class TestBSTMapiterator {

    @Test
    public void testIterator() {
        BSTMap<String, Integer> b = new BSTMap<String, Integer>();
        b.put("hi", 1);
        b.put("bye", 2);
        b.put("hello", 3);
        b.put("goodbye", 4);
        b.put("yo", 5);

        for (String s : b) {
            System.out.println(s);
        }
    }
}
