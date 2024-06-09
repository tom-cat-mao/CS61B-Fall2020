/**
 *  @author Josh Hug
 * this si the final version of ALIst
 * written in 2024.6.8
 * by Tom Cat
 */

public class AList {
    /** Creates an empty list. */
    private IntList sentinel;
    private int size;

    private class IntList {

        int x;
        IntList next;

        IntList(int x , IntList next) {
            this.x = x;
            this.next = next;
        }
        
        int get(int i) {
            IntList p = this;
            while (i != 0) {
                p = p.next;
                i--;
            }
            return p.x;
        }

        int getLast() {
            IntList p = this;
            while (p.next != null) {
                p = p.next;
            }

            return p.x;
        }

	public void addAdjacent() {
		IntList p;
		int t;
		boolean flag = true;

		while (flag) {
            flag = false;
            p = this;
            t = p.x;

			while (p.next != null) {

                if (p.next.x != t) {
                    p = p.next;
                    t = p.x;
                    continue;
                }

                p.x += t;
                p.next = p.next.next;
                flag = true;
                size--;
			}

		}

        }
    }

    public AList() {
        this.sentinel = new IntList(114514, null);
        this.size = 0;
    }

    AList(int i) {
        this.sentinel = new IntList(114514, null);
        this.sentinel.next = new IntList(i, sentinel.next);
        size = 1;
    }

    /** Inserts X into the back of the list. */
    public void addLast(int x) {
        size++;
        IntList p = this.sentinel;
        while (p.next != null) {
            p = p.next;
        }

        p.next = new IntList(x, null);
    }

    /** Returns the item from the back of the list. */
    public int getLast() {
        return this.sentinel.next.getLast();
    }

    /** Gets the ith item in the list (0 is the front). */
    public int get(int i) {
        return this.sentinel.next.get(i);
    }

    /** Returns the number of items in the list. */
    public int size() {
        return size;
    }

    public void addFirst(int i) {
        size++;
        this.sentinel.next = new IntList(i, sentinel.next);
    }

    /** Deletes item from back of the list and
      * returns deleted item. */
    public int removeLast() {
        IntList p = this.sentinel;
        while (p.next.next != null) {
            p = p.next;
        }

        int i = p.next.x;
        p.next = null;
        size--;
        return i;
    }

    public void print_out() {
        IntList p = this.sentinel.next;
        while (p != null) {
            System.out.println(p.x);
            p = p.next;
        }
    }

    public static void main(String[] args) {
        AList L = new AList();
        L.addLast(1);
        L.addLast(1);
        L.addLast(2);
        L.addLast(3);
        L.sentinel.next.addAdjacent();

        // L.addFirst(10);
        // System.out.println(L.size());
        // L.addFirst(20);
        // L.addLast(30);
        // System.out.println(L.size());
        // L.print_out();
        // L.removeLast();
        // L.print_out();
        L.print_out();
        System.out.println(L.size());
    }
} 
