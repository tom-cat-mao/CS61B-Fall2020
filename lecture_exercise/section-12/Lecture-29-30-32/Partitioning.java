/* all the partion method for quick sort */

public class Partitioning {

    /* the LTHS method to partion the array */
    public static int LTHS(int[] array, int start, int end) {
        int pivot = array[start];

        int i, j;
        i = start + 1;
        j = end;

        /* the left pointer find the bigger or equal one and stop
         * till the right pointer find the smaller or equal one
         * then swap it and get to the next position
         * the loop will stop when i is more than j
         */
        while (true) {
            for (; i <= end && array[i] < pivot; i++) {} // find the index of the first bigger one on the left, be careful not get uot of the bound
            for (; j >= start && array[j] > pivot; j--) {} // find the index of the first smaller one on the right, be careful not get out of the bound

            /* if j get the left of i
             * breck the loop
             */
            if (i > j) {
                break;
            }

            swap(array, i++, j--); // swap the element in index i and j
        }

        /* if the loop is break then j is the index of the smaller element on the left
         * so swap the pivot and the element
         */
        swap(array, start, j);

        /* return the position that the pivot last placed */
        return j;
    }

    /* private static method to swap the element */
    private static void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
