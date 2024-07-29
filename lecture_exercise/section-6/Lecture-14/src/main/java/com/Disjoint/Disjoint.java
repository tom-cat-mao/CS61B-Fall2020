package com.Disjoint;

public class Disjoint {
    private int array[];

    /* the constructor that
     * put all the value to zero
     */
    public Disjoint() {
        array = new int[100];
        for (int i = 0; i < array.length; i++) {
            array[i] = -1;
        }
    }
    /* connect two trees
     * which a and b belong to
     */
    public void connect(int a, int b) {
        int i = root(a);
        int j = root(b);
        if (array[i] < array[j]) {
            array[i] += array[j];
            array[j] = i;
        } else {
            array[j] += array[i];
            array[i] = j;
        }
    }

    /* find the root of the tree
     * which x belongs to
     * use recusion
     * return the root
     */
    public int root(int a) {
        if (array[a] < 0) {
            return a;
        } else {
            return root(array[a]);
        }
    }

    /* find out whether two element is connected
     * and splite the tree
     * so ehat all the element is hang on the root
     */
    public boolean isConnected(int a, int b) {
        int ra = root(a);
        int rb = root(b);

        for (int i = array[a]; i != array[ra]; i = array[a]) {
            array[a] = ra;
            a = i;
        }

        for (int i = array[b]; i !=array[rb]; i = array[b]) {
            array[b] = rb;
            b = i;
        }

        return ra == rb;
    }
}
