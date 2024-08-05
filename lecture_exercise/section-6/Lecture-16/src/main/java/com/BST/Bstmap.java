package com.BST;

/* binary sear/ch tree
 * make, inset, search, delete elements
 */
public class Bstmap {

    Bstmap right;
    Bstmap left;
    int num;
    int time;

    public Bstmap(int num) {
        this.num = num;
        this.right = null;
        this.left = null;
        time = 1;
    }

    /* plus one time */
    public void addtime() {
        time++;
    }

    /* insert element */
    public void insert(Bstmap root, int num) {
        insert_r(root, num);
        return;
    }

    private Bstmap insert_r(Bstmap root, int num) {
        /* if root is null
         * it means this is the last node
         * so return current number to connect
         */
        if (root == null) {
            return new Bstmap(num);
        }

        /* if input number is bigger than current
         * it should appear in the left
         * otherwise it should appear in the right
         */
        if (num < root.num) {
            root.left = insert_r(root.left, num);
        } else if (num > root.num) {
            root.right = insert_r(root.right, num);
        } else {
            /* if input number equal current number
             * add current number's time
             */
            root.addtime();
        }

        return root;
    }

    /* search specific number */
    public boolean search(Bstmap root, int num) {
        if (search_r(root, num) == null) {
            return false;
        } else {
            return true;
        }
    }

    private Bstmap search_r(Bstmap root, int num) {
        /* if root is null, it means it's over */
        if (root == null) {} /* if input number is bigger than current
         * it should appear in the left
         * otherwise it should appear in the right
         */else if (num < root.num) {
            return search_r(root.left, num);
        } else if (num > root.num) {
            return search_r(root.right, num);
        }

        /* if the input equals the node number
         * return the node
         */
        return root;
    }

    /* delete specific number */
    public void delete(Bstmap root, int number) {
        delete_r(root, number);
        return;
    }

    /* private part of the delete method  */
    private Bstmap delete_r(Bstmap root, int number) {
        if (root == null) {
            return null;
        }

        if (number < root.num) {
            root.left = delete_r(root.left, number);
        } else if (number > root.num) {
            root.right = delete_r(root.right, number);
        } else {
            /* if the node has no child
             * then return null
             */
            if (root.left == null && root.right == null) {
                return null;
            }

            /* if the node has only one child */
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            } else {
                /* if the node has two children */
                Bstmap temp = s_p_node(root.left);
                root.num = temp.num;
                root.time = temp.time;
                root.left = delete_r(root.left, temp.num);
            }
        }
        return root;
    }

    /* find the predecessor of the root */
    private Bstmap s_p_node(Bstmap root) {
        /* if the right side is null then we find it and return it */
        if (root.right == null) {
            return root;
        }

        return s_p_node(root.right);
    }
    /* find the successor of the root */
    // private Bstmap s_s_node(Bstmap root) {
    /* if the left side is null then we find it and return it */
    //     if (root.left == null) {
    //         return root;
    //     }

    //     return s_s_node(root.left);

}
