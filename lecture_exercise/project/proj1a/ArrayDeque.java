public class ArrayDeque<object> {
    private object[] array;
    private int nextFirst;
    private int nextLast;
    private int size;

    public ArrayDeque() {
        size = 0;
        array = (object[]) new Object[8];
        nextFirst = 7;
        nextLast = 0;
    }

    /* justify if the array should be resize */
    public boolean isOversize() {
        return size >= array.length / 4;
    }

    /* 4 times the current array */
    public void resizearray() {
        object[] newArray = (object[]) new Object[array.length * 4];

        /* copy all the items which was added from first to the newArray */
        System.arraycopy(this.array, nextFirst + 1, newArray, newArray.length - array.length + nextFirst + 1, array.length - nextFirst - 1);
        // for (int i = array.length - 1; i > nextFirst; i--) {
        //     newArray[newArray.length - array.length + i] = array[i];
        // }

        /* copy all the items whilch was added from last to the newArray */
        System.arraycopy(this.array, 0, newArray, 0, nextLast);

        /* reset the nextFirst place */
        nextFirst = newArray.length - array.length + nextFirst;

        array = newArray;

    }

    public void addFirst(object item) {
        /* we need to justify whether the array is oversized
         * the whole size should be at most 25% of the array length
        */
        if (isOversize()) {
            resizearray();
        }

        array[nextFirst] = item;
        nextFirst--;
        size++;
    }

    public void addLast(object item) {
        if (isOversize()) {
            resizearray();
        }

        array[nextLast] = item;
        nextLast++;
        size++;
    }

    public void removeFirst() {
        if (size == 0) {
            return;
        }

        array[nextFirst + 1] = null;
        nextFirst++;
        size--;
    }

    public void removeLast() {
        if (size == 0) {
            return;
        }

        array[nextLast - 1] = null;
        nextLast--;
        size--;
    }

    public int size() {
        return size;
    }

    public object get(int index) {
        if (index >= size) {
            return null;
        }
        else if (array.length - nextFirst - 1 > index) {
            return array[1 + nextFirst + index];
        }
        else {
            return array[index - (array.length - nextFirst - 1)];
        }
    }

    public void printDeque() {
        for (int i = 0; i < array.length; i++) {
            System.out.print(get(i) + " ");
        }
        System.out.println();
    }
}