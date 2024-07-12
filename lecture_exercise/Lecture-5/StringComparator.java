public class StringComparator {
    public interface NullSafeStringComparator {
        /** Return a negative number if s1 is 'less than' s2, 0 if 'equal',
         * and a positive number otherwise. Null is considered less than 
         * any String. If both inputs are null return 0.
         */

         public int compare(String s1, String s2);
    }

    public class LengthComparator implements NullSafeStringComparator {

        @Override
        public int compare(String s1, String s2) {
            if (s1 == null && s2 != null)
                return -1;
            
            if (s2 == null)
                return 1;
            
            if (s1.length() < s2.length()) 
                return -1;
            
            return 1;
        }
    }
}