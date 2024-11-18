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
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class Trie {

    private TrieNode root; // Root node of the Trie

    /** private trie node class
     * stroe the children of the node and the boolean value to check if it is the end of the word
     */
    private class TrieNode {

        private char c; // The character associated with the node
        private boolean isEnd; // Indicates if the node represents the end of a valid word
        private TrieNode[] children; // Array of child nodes, one for each possible character

        public TrieNode() {
            isEnd = false;
            children = new TrieNode[26];
        }

        public TrieNode(char c) {
            this.c = c;
            isEnd = false;
            children = new TrieNode[26];
        }

        public boolean isEnd() {
            return isEnd;
        }

        public void setEnd() {
            isEnd = true;
        }

        public TrieNode get(char c) {
            if (containsKey(c)) {
                return children[c - 'a'];
            }

            return null;
        }

        public boolean containsKey(char c) {
            return children[c - 'a'] != null;
        }

        public void addchild(char c) {
            children[c - 'a'] = new TrieNode(c);
        }

        public char getChar() {
            return c;
        }

        /** use dfs to get all the words in the Trie Tree
         * @return a list of words
         */
        public List<String> getwords() {
            List<String> words = new ArrayList<>();
            this.dfs(words, "");
            return words;
        }

        /** dfs algorithmm to store all the words in the Trie Tree
         * @param words a list of stored words
         * @param prefix current words
         */
        private void dfs(List<String> words, String prefix) {
            if (this.isEnd()) {
                words.add(prefix);
            }

            for (TrieNode child : this.getneighbors()) {
                child.dfs(words, prefix + child.getChar());
            }
        }

        /** get the children of the trinode
         * @return a list of children nodes
         */
        private List<TrieNode> getneighbors() {
            List<TrieNode> children = new ArrayList<>();
            for (int i = 0; i < 26; i++) {
                if (this.children[i] != null) {
                    children.add(this.children[i]);
                }
            }

            return children;
        }
    }

    public Trie() {
        root = new TrieNode();
    }

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
        Character c = characters.poll(); // get the first character in the queue

        /** if poll method returned null
         * then just set the end and return the root
         */
        if (c.equals(null)) {
            root.setEnd();
            return root;
        }

        /** if the root don't have the child
         * just add the child
         */
        if (!root.containsKey(c)) {
            root.addchild(c);
        }

        root = insert_r(root.get(c), characters);

        return root;
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

    public List<String> getwords() {
        return root.getwords();
    }
}
