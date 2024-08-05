package com.BST;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestMap {

    private Bstmap bst;

    @Before
    public void setUp() {
        bst = new Bstmap(10);
    }

    @Test
    public void testInsert() {
        bst.insert(bst, 5);
        bst.insert(bst, 15);
        bst.insert(bst, 3);
        bst.insert(bst, 7);
        bst.insert(bst, 12);
        bst.insert(bst, 18);

        assertTrue(bst.search(bst, 5));
        assertTrue(bst.search(bst, 15));
        assertTrue(bst.search(bst, 3));
        assertTrue(bst.search(bst, 7));
        assertTrue(bst.search(bst, 12));
        assertTrue(bst.search(bst, 18));
    }

    @Test
    public void testSearch() {
        bst.insert(bst, 5);
        bst.insert(bst, 15);

        assertTrue(bst.search(bst, 5));
        assertTrue(bst.search(bst, 15));
        assertFalse(bst.search(bst, 20));
    }

    @Test
    public void testDelete() {
        bst.insert(bst, 5);
        bst.insert(bst, 15);
        bst.insert(bst, 3);
        bst.insert(bst, 7);

        bst.delete(bst, 5);
        assertFalse(bst.search(bst, 5));

        bst.delete(bst, 15);
        assertFalse(bst.search(bst, 15));

        bst.delete(bst, 3);
        assertFalse(bst.search(bst, 3));

        bst.delete(bst, 7);
        assertFalse(bst.search(bst, 7));
    }
}
