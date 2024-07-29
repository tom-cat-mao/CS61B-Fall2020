package com.Disjoint;
import static org.junit.Assert.*;

public class DisjointTest {

    @org.junit.Test
    public void connect() {
        Disjoint disjoint = new Disjoint();
        disjoint.connect(1, 2);
        disjoint.connect(3, 4);
        disjoint.connect(5, 6);
        disjoint.connect(7, 8);
        disjoint.connect(1, 3);
        disjoint.connect(5, 7);
        disjoint.connect(1, 5);
        assertTrue(disjoint.isConnected(1, 5));
        assertTrue(disjoint.isConnected(1, 8));
    }

//    @org.junit.Test
//    public void root() {
//        Disjoint disjoint = new Disjoint();
//        disjoint.connect(1, 2);
//        disjoint.connect(3, 4);
//        disjoint.connect(5, 6);
//        disjoint.connect(7, 8);
//
//        assertEquals(1, disjoint.root(1));
//        assertEquals(3, disjoint.root(3));
//        assertEquals(5, disjoint.root(5));
//        assertEquals(7, disjoint.root(7));
//    }
//
//    @org.junit.Test
//    public void isConnected() {
//        Disjoint disjoint = new Disjoint();
//        disjoint.connect(1, 2);
//        disjoint.connect(3, 4);
//        disjoint.connect(5, 6);
//        disjoint.connect(7, 8);
//        disjoint.connect(1, 3);
//        disjoint.connect(5, 7);
//        disjoint.connect(1, 5);
//        assertTrue(disjoint.isConnected(1, 5));
//        assertFalse(disjoint.isConnected(1, 8));
//    }
}
