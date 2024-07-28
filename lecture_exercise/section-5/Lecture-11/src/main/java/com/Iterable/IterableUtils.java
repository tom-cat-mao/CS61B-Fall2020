package com.Iterable;
import java.util.*;

public class IterableUtils {
    public static List<Integer> toList(Iterable<Integer> iterable) {
        List<Integer> newList = new ArrayList<>();
        for (int item : iterable) {
            /* if (item == null) {
                throw new IllegalArgumentException("You can't add null!!");
            } */

            if ((item % 2) == 0) {
                continue;
            }

            newList.add(item);
        }

        return newList;
    }
} // assume any classes you need are imported
