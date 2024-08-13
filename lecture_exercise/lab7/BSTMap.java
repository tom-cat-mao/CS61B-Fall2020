import java.util.Iterator;
import java.util.TreeSet;
import java.util.Set;
import java.util.Stack;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    /* the size of the BSTMap
     * the root of the BST
     */
    private int size;
    private BSTNode root;

    /* defination of the BSTNode */
    private class BSTNode implements Iterable<K> {

        private K key;
        private V value;
        private BSTNode left;
        private BSTNode right;

        public BSTNode(K key, V value) {
            this.key = key;
            this.value = value;
        }

        /* put a new node */
        public BSTNode put(BSTNode root, K key, V value) {
            if (root == null) {
                size++;
                return new BSTNode(key, value);
            }

            int cmp = key.compareTo(root.key);

            if (cmp < 0) {
                root.left = put(root.left, key, value);
            } else if (cmp > 0) {
                root.right = put(root.right, key, value);
            } else {
                root.value = value;
            }

            return root;
        }

        /* get the value of the key */
        public BSTNode get(BSTNode root, K key) {
            if (root == null) {
                return null;
            }

            int cmp = key.compareTo(root.key);

            if (cmp < 0) {
                return get(root.left, key);
            } else if (cmp > 0) {
                return get(root.right, key);
            } else {
                return root;
            }
        }

        /* whether it contain the a mapping for the specified key. */
        public boolean containsKey(BSTNode root, K key) {
            return get(root, key) != null;
        }

        /* delete a key from the BST */
        public BSTNode delete(BSTNode root, K key) {
            if (root == null) {
                return null;
            }

            int cmp = key.compareTo(root.key);

            if (cmp < 0) {
                root.left = delete(root.left, key);
            } else if (cmp > 0) {
                root.right = delete(root.right, key);
            } else {
                if (root.left == null) {
                    return root.right;
                } else if (root.right == null) {
                    return root.left;
                } else {
                    BSTNode min = min(root.right);
                    root.key = min.key;
                    root.value = min.value;
                    root.right = delete(root.right, min.key);
                }
            }

            return root;
        }

        /* delete the key only if it contains the specified key from the map */
        public BSTNode delete(BSTNode root, K key, V value) {
            if (root == null) {
                return null;
            }

            int cmp = key.compareTo(root.key);

            if (cmp < 0) {
                root.left = delete(root.left, key, value);
            } else if (cmp > 0) {
                root.right = delete(root.right, key, value);
            } else {
                if (root.value.equals(value)) {
                    size--;
                    return delete(root, key);
                }
            }

            return root;
        }

        /* get the min node */
        public BSTNode min(BSTNode root) {
            if (root.left == null) {
                return root;
            }

            return min(root.left);
        }

        /* print the BSTMap */
        public void printInOrder(BSTNode root) {
            if (root == null) {
                return;
            }

            printInOrder(root.left);
            System.out.println(root.key + " : " + root.value);
            printInOrder(root.right);
        }

        public Iterator<K> iterator() {
            return new BSTMapIter();
        }

        private class BSTMapIter implements Iterator<K> {
            private BSTNode current;
            private Stack<BSTNode> stack;

            public BSTMapIter() {
                stack = new Stack<BSTNode>();
                current = root;
            }

            public boolean hasNext() {
                return !stack.isEmpty() || current != null;
            }

            public K next() {
                while (current != null) {
                    stack.push(current);
                    current = current.left;
                }

                BSTNode node = stack.pop();
                current = node.right;
                return node.key;
            }
        }
    }

    /* constructor */
    public BSTMap() {
        size = 0;
        root = null;
    }

    /* clear the BSTMap */
    @Override
    public void clear() {
        size = 0;
        root = null;
    }

    /* whether it contain the a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
        return root.containsKey(root, key);
    }

    /* get the value of the key */
    @Override
    public V get(K key) {
        BSTNode node = root.get(root, key);
        return node == null ? null : node.value;
    }

    /* get the size of the BSTMap */
    @Override
    public int size() {
        return size;
    }

    /* put a new key-value pair */
    @Override
    public void put(K key, V value) {
        if (size == 0) {
            root = new BSTNode(key, value);
            size++;
            return;
        }

        root = root.put(root, key, value);
    }

    /* delete a key from the BST */
    @Override
    public V remove(K key) {
        BSTNode node = root.delete(root, key);
        if (node == null) {
            return null;
        }

        size--;
        return node.value;
    }

    /* print the BSTMap */
    public void printInOrder() {
        root.printInOrder(root);
    }

    /* Return a Set view of the keys contained in this map. */
    public Set<K> keySet() {
        Set<K> keySet = new TreeSet<K>();
        inOrderTraversal(root, keySet);
        return keySet;
    }

    private void inOrderTraversal(BSTNode root, Set<K> keySet) {
        if (root == null) {
            return;
        }

        inOrderTraversal(root.left, keySet);
        keySet.add(root.key);
        inOrderTraversal(root.right, keySet);
    }

    public V remove(K key, V value) {
        BSTNode node = root.delete(root, key, value);
        if (node == null) {
            return null;
        }

        size--;
        return node.value;
    }

    public Iterator<K> iterator() {
        return root.iterator();
    }
}
