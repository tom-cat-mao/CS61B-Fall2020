/** A Trie (pronounced as "try") is a tree-like data structure that stores a dynamic set of strings,
 * usually used to store a predictive text or autocomplete dictionary. Each node in the Trie represents
 * a single character of a string. The root node is associated with an empty string, and each subsequent
 * node represents a character of the string being stored.
 * Key properties of a Trie:
 * - Each node can have multiple children, one for each possible character.
 * - The path from the root to a node represents a prefix of the strings stored in the Trie.
 * - Nodes can be marked to indicate the end of a valid string.
 * Common operations on a Trie:
 * - Insertion: Adding a string to the Trie by creating nodes for each character if they do not already exist.
 * - Search: Checking if a string exists in the Trie by traversing the nodes corresponding to each character.
 * - Deletion: Removing a string from the Trie by unmarking the end node and potentially removing nodes that are no longer part of any other string.
 * Tries are particularly useful for tasks involving prefix matching, such as autocomplete and spell checking.
 */

package bearmaps.proj2d;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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
            children = new TrieNode[27];
        }

        public TrieNode(char c) {
            this.c = c;
            isEnd = false;
            children = new TrieNode[27];
        }

        public boolean isEnd() {
            return isEnd;
        }

        public void setEnd() {
            isEnd = true;
        }

        public TrieNode get(char c) {
            if (containsKey(c)) {
                return children[getIndex(c)];
            }

            return null;
        }

        public boolean containsKey(char c) {
            return children[getIndex(c)] != null;
        }

        public void add(char c) {
            children[getIndex(c)] = new TrieNode(c);
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

        /** get the children of the Trienode
         * @return a list of children nodes
         */
        private List<TrieNode> getneighbors() {
            List<TrieNode> childnode = new ArrayList<>();
            for (TrieNode child : children) {
                if (child != null) {
                    childnode.add(child);
                }
            }

            return childnode;
        }

        /** get the index of the character
         * if the character is letter, use its lowercase to locate it from 0 to 25
         * else if it's a special character
         * just return 26 (so it will be put into the last position)
         * @param c the character that need to be located
         * @return the index of the character
         */
        private int getIndex(char c) {
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                return Character.toLowerCase(c) - 'a';
            }

            return 26;
        }
    }

    public Trie() {
        root = new TrieNode();
    }

    /** insert a node into the Trie
     * @param name the node that will be inserted
     */
    public void insert(String name) {
        Queue<Character> characters = split(name);
        insert_r(root, characters);
    }

    /** the private insert method
     * use recursion to insert each Character
     * @param root the root of the Trie
     * @param characters the list of characters
     * @return the root of the Trie
     */
    private void insert_r(TrieNode root, Queue<Character> characters) {
        Character c = characters.poll(); // get the first character in the queue

        /** if poll method returned null
         * then just set the end and return the root
         */
        if (c == null) {
            root.setEnd();
            return;
        }

        /** if the root don't have the child
         * just add the child
         */
        if (!root.containsKey(c)) {
            root.add(c);
        }

        insert_r(root.get(c), characters);
    }

    /** split name into a queue
     * @param name the name of the node
     * @return an arraylist of charactor
     */
    private Queue<Character> split(String name) {
        Queue<Character> characters = new LinkedList<>();
        for (char c : name.toCharArray()) {
            characters.add(c);
        }

        return characters;
    }

    /** Find the node that represents the given prefix in the Trie.
     * @param prefix The prefix to search for.
     * @return a list of strings that have the prefix
     */
    public List<String> nearby(String prefix) {
        StringBuilder sb = new StringBuilder();
        TrieNode node = find(root, prefix, sb);
        if (node == null) {
            return null;
        }

        prefix = sb.toString();

        List<String> words = node.getwords();
        for (int i = 0; i < words.size(); i++) {
            words.set(i, prefix + words.get(i));
        }

        return words;
    }

    /** private find method to find the node that represents the given prefix in the Trie.
     * @param root the root of the Trie
     * @param prefix the prefix to search for
     * @param sb the stringbuilder to store the prefix
     * @return the node that represents the given prefix in the Trie
     */
    private TrieNode find(TrieNode root, String prefix, StringBuilder sb) {
        for (char c : prefix.toCharArray()) {
            if (!root.containsKey(c)) {
                return null;
            }

            sb.append(root.get(c).getChar());
            root = root.get(c);
        }

        return root;
    }
}
