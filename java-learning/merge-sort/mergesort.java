class merge_sort {
    private int[] arr;
    private int left;
    private int right;

    merge_sort(int[] arr, int left, int right) {
        this.arr = arr;
        this.left = left;
        this.right = right;
        sort();
    }

    void sort() {
        if (left < right) {
            int middle = (left + right) / 2;
            new merge_sort(arr, left, middle);
            new merge_sort(arr, middle + 1, right);
            new merge_sort_u(arr, left, middle, right);
        }
    }
}

class merge_sort_u {
    int[] arr;
    int left;
    int right;
    int middle;

    merge_sort_u(int[] arr, int left, int middle, int right) {
        this.arr = arr;
        this.left = left;
        this.middle = middle;
        this.right = right;
        union();
    }

    void union() {
        int[] copy = new int[arr.length];
        System.arraycopy(arr, 0, copy, 0, arr.length);

        int i = left, j = middle + 1, k = left;

        while (i <= middle && j <= right) {
            if (copy[i] <= copy[j]) {
                arr[k++] = copy[i++];
            } else {
                arr[k++] = copy[j++];
            }
        }

        while (i <= middle) {
            arr[k++] = copy[i++];
        }

        while (j <= right) {
            arr[k++] = copy[j++];
        }
    }
}

public class mergesort {
    public static void main(String[] args) {
        int[] arr = {3, 5, 1, 732, 21, 20, 45};
        new merge_sort(arr, 0, arr.length - 1);
        for (int num : arr) {
            System.out.println(num);
        }
    }
}
