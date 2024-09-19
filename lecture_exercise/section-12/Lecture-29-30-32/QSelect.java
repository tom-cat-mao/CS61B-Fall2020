/* the Quick select class to find the middle element in an inordered array */
import java.util.*;

public class QSelect {

    private static int middle;

    /* public static method to find the middle element */
    public static int QSelect(int[] arr) {
        int length = arr.length;
        middle = (length - 1) / 2;
        return arr[QSelect_helper(arr, 0, length - 1)];
    }

    private static int QSelect_helper(int[] arr, int start, int end) {
        int length = end - start + 1;
        int pivot = Partitioning.LTHS(arr, start, end);

        if (pivot > middle) {
            pivot = QSelect_helper(arr, start, pivot - 1);
        } else if (pivot < middle) {
            pivot = QSelect_helper(arr, pivot + 1, end);
        }

        return pivot;
    }
}
