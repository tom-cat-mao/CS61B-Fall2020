public class insertsort {
    public static void main(String[] args) {
        int [] arr = {9, 1, 3, 5, 23, 87};
      insertationsort t = new insertationsort(arr);
        t.sort();
        for (int i :arr) {
            System.out.println(i);
        }
    }
}
class insertationsort {
    private int [] arr;
    insertationsort(int[] arr)
    {
        this.arr = arr;
    }

    public void sort()
    {
        int i, j;
        for (i = 1; i < arr.length; i++) {
            int key = arr[i];
            j = i;
            while (j > 0 && arr[j - 1] > key) {
                arr[j] = arr[j - 1];
                j--;
            }
            arr[j] = key;
        }
    }
}

