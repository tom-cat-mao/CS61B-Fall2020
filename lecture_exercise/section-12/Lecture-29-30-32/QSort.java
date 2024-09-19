/* the quick sort algorithm use LTHS method to partion the array */
import java.util.*;

public class QSort {

    /* public static Quick sort method */
    public static void Q_sort(int[] arr) {
        int length = arr.length;
        Q_sorthelper(arr, 0, length - 1);
    }

    /* private static quick sort helper method */
    private static void Q_sorthelper(int[] arr, int start, int end) {
        int length = end - start + 1; // get length of the subarray
        /* if the length is 1 or less than 1
         * then the section is done
         */
        if (length <= 1) {
            return;
        }

        /* partition the subarray
         * return the current index of the pivot
         */
        int pivot = Partitioning.LTHS(arr, start, end);

        Q_sorthelper(arr, pivot + 1, end); // sort the right side
        Q_sorthelper(arr, start, pivot - 1); // sort the left side
    }
}
