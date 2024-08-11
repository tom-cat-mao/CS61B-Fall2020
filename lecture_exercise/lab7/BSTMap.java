import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    /* the size of the BSTMap
     * the root of the BST
     */
    private int size;
    private BSTNode root;

    /* defination of the BSTNode */
    private class BSTNode {

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

    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    public V remove(K key, V value) {
        BSTNode node = root.delete(root, key, value);
        if (node == null) {
            return null;
        }

        size--;
        return node.value;
    }
}
