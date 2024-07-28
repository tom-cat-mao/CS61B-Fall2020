package com.Iterable;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class TestRODI {

    @Test
    public void testRODI() {
        ReverseOddDigitIterator odi = new ReverseOddDigitIterator(114514);
        List<Integer> l = new ArrayList<>();
        l = IterableUtils.toList(odi);
        // Add an assertion to verify the list
        assertEquals(List.of(1, 5, 1, 1), l);

        odi = new ReverseOddDigitIterator(1919810);
        l = IterableUtils.toList(odi);
        // Add an assertion to verify the list
        assertEquals(List.of(1, 9, 1, 9, 1), l);

        /*odi = new ReverseOddDigitIterator(1145141919810L);
        l = IterableUtils.toList(odi);
        assertEquals(List.of(1, 9, 1, 9, 1, 1, 5, 1, 1), l);*/
    }
}