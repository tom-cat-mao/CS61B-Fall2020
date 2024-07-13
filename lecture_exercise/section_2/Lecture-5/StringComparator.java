public class StringComparator {
    public interface NullSafeStringComparator {
        /** Return a positive number if s1 is 'less than' s2, 
         * and a negative number otherwise. Null is considered less than 
         * any String. If both inputs are null return 0.
         */

         public int compare(String s1, String s2);
    }

    public static class LengthComparator implements NullSafeStringComparator {

        @Override
        public int compare(String s1, String s2) {
            if (s1 == null && s2 != null)
                return 1;
            
            if (s2 == null || s1 == null)
                return -1;
            
            if (s1.length() < s2.length()) 
                return 1;
            
            return -1;
        }
    }

    public static String max(String[] a, NullSafeStringComparator sc) {
        String maxStr = a[0];

        for (int i = 1; i < a.length; i++) {

            if (sc.compare(maxStr, a[i]) < 0) {
                maxStr = a[i];
            }
        }

        return maxStr;
    } // bracket on this line for vertical space reasons

    public static void step(String[][] arr, NullSafeStringComparator sc) {
        /* Recall: All String references in stepped are null by
         * default, so the edges are correct on initialization.
         */

        String[][] stepped = new String[arr.length + 2][];

        for (int i = 0; i < arr.length; i++) {
            stepped[i +1] = new String[arr[i].length + 2];
            System.arraycopy(arr[i], 0, stepped[i + 1], 1, arr[i].length);
        }

        // Initialize first and last rows
        stepped[0] = new String[arr[0].length + 2];
        stepped[arr.length + 1] = new String[arr[0].length + 2];
        
        int[][] dir = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        for (int i = 1; i <= arr.length; i++) {
            for (int j = 1; j <= arr[i - 1].length ; j++) {
                String temp = stepped[i][j];
                for (int[] d : dir) {
                    if (sc.compare(temp, stepped[i + d[0]][j + d[1]]) > 0) {
                        temp = stepped[i + d[0]][j + d[1]];
                    }
                }
                arr[i - 1][j - 1] = temp;
            }
        }

    }

    public static void main(String[] args) {
        String[][] arr = {
            {"Hello", "this", "is"},
            {"a", "test", "to"}, 
            {"figure", "out", "the"}, 
            {"of", "the", "system"}
        };

        NullSafeStringComparator sc = new LengthComparator();

        step(arr, sc);

        for (String[] row : arr) {
            for (String s : row) {
                System.out.print(s + " ");
            }
            System.out.println();
        }

    } 

}