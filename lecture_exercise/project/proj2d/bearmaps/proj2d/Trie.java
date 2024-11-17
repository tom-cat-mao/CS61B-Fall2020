/** A Trie (pronounced as "try") is a tree-like data structure that stores a dynamic set of strings,
 * usually used to store a predictive text or autocomplete dictionary. Each node in the Trie represents
 * a single character of a string. The root node is associated with an empty string, and each subsequent
 * node represents a character of the string being stored.
 *
 * Key properties of a Trie:
 * - Each node can have multiple children, one for each possible character.
 * - The path from the root to a node represents a prefix of the strings stored in the Trie.
 * - Nodes can be marked to indicate the end of a valid string.
 *
 * Common operations on a Trie:
 * - Insertion: Adding a string to the Trie by creating nodes for each character if they do not already exist.
 * - Search: Checking if a string exists in the Trie by traversing the nodes corresponding to each character.
 * - Deletion: Removing a string from the Trie by unmarking the end node and potentially removing nodes that are no longer part of any other string.
 *
 * Tries are particularly useful for tasks involving prefix matching, such as autocomplete and spell checking.
 */

package bearmaps.proj2d;

import bearmaps.proj2c.streetmap.Node;
import java.util.LinkedList;
import java.util.Queue;

public class Trie {

    private TrieNode root; // Root node of the Trie

    /** private trie node class
     * stroe the children of the node and the boolean value to check if it is the end of the word
     */
    private class TrieNode {

        private boolean isEnd; // Indicates if the node represents the end of a valid word
        private TrieNode[] children; // Array of child nodes, one for each possible character

        public TrieNode() {
            isEnd = false;
            children = new TrieNode[26];
        }

        public boolean isEnd() {
            return isEnd;
        }

        public void setEnd() {
            isEnd = true;
        }

        public TrieNode get(char ch) {
            if (containsKey(ch)) {
                return children[ch - 'a'];
            }

            return null;
        }

        public boolean containsKey(char ch) {
            return children[ch - 'a'] != null;
        }
    }

    public Trie() {}

    /** insert a node into the Trie
     * @param node the node that will be inserted
     */
    public void insert(Node node) {
        Queue<Character> characters = split(node.name);
    }

    /** the private insert method
     * use recursion to insert each Character
     * @param root the root of the Trie
     * @param characters the list of characters
     * @return the root of the Trie
     */
    private TrieNode insert_r(TrieNode root, Queue<Character> characters) {
        return null;
    }

    /** split name into a queue
     * @param the name of the node
     * @return the an arraylist of charactor
     */
    private Queue<Character> split(String name) {
        Queue<Character> characters = new LinkedList<>();
        for (char ch : name.toCharArray()) {
            characters.add(ch);
        }
        return characters;
    }
}
