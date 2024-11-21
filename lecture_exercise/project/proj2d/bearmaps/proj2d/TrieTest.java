/* this is a test file for trie tree */
package bearmaps.proj2d;

import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import java.util.List;


public class TrieTest {
    private Trie trie;

    @Before
    public void setUp() {
        trie = new Trie();
    }

    @Test
    public void testInsert() {
        trie.insert("Hello");
        trie.insert("Hollo");
        trie.insert("Hello's");
        trie.insert("bee");
        trie.insert("Be");

        List<String> heinputexpected = List.of("Hello", "Hello's");
        List<String> beinputexpected = List.of("be", "bee");

        Assert.assertEquals(heinputexpected, trie.nearby("hE"));
        Assert.assertEquals(beinputexpected, trie.nearby("be"));
    }
}