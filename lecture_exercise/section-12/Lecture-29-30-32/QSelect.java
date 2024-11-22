/* the Quick select class to find the middle element in an inordered array */
import java.util.*;

public class QSelect {

    private static int middle; // store the middle of the array

    /* public static method to find the middle element */
    public static int QSelect(int[] arr) {
        int length = arr.length;
        middle = (length - 1) / 2; // get the middle of the array 
        return arr[QSelect_helper(arr, 0, length - 1)];
    }

    /* private static qselect helper method return the index of the element */
    private static int QSelect_helper(int[] arr, int start, int end) {
        int length = end - start + 1; // the length of the subarray
        int pivot = Partitioning.LTHS(arr, start, end); // use the LTHS partition to find the pivot

        /* if the pivot is on the right side of the middle, select the right side
         * else select the left side
         */
        if (pivot > middle) {
            pivot = QSelect_helper(arr, start, pivot - 1);
        } else if (pivot < middle) {
            pivot = QSelect_helper(arr, pivot + 1, end);
        }

        return pivot;
    }
}
