/*package bearmaps.proj2d;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import bearmaps.proj2c.streetmap.Node;

public class TrieTest {

    private Trie trie;

    @Before
    public void setUp() {
        trie = new Trie();
    }

    @Test
    public void testInsertAndSearch() {
        Node node1 = Node.of(1, 0.0, 0.0);
        node1.setName("BEE");
        trie.insert(node1);

        Node node2 = Node.of(2, 0.0, 0.0);
        node2.setName("BED");
        trie.insert(node2);

        Node node3 = Node.of(3, 0.0, 0.0);
        node3.setName("BAT");
        trie.insert(node3);


        Node node4 = Node.of(3, 0.0, 0.0);
        node4.setName("BAD");
        trie.insert(node4);

        Node node5 = Node.of(3, 0.0, 0.0);
        node5.setName("BBC");
        trie.insert(node5);

        assertTrue(trie.getwords().contains("bee"));
        assertTrue(trie.getwords().contains("bed"));
        assertTrue(trie.getwords().contains("bat"));
    }

    @Test
    public void testInsertAndSearchEmpty() {
        assertTrue(trie.getwords().isEmpty());
    }

    @Test
    public void testInsertAndSearchPrefix() {
        Node node1 = Node.of(1, 0.0, 0.0);
        node1.setName("apple");
        trie.insert(node1);

        Node node2 = Node.of(2, 0.0, 0.0);
        node2.setName("app");
        trie.insert(node2);
 assertTrue(trie.getwords().contains("apple")); assertTrue(trie.getwords().contains("app")); }

    @Test
    public void testInsertAndSearchNonExistent() {
        Node node1 = Node.of(1, 0.0, 0.0);
        node1.setName("apple");
        trie.insert(node1);

        assertFalse(trie.getwords().contains("banana"));
    }
}*/