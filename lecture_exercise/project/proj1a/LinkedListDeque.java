public class LinkedListDeque<item> {
    private int size;
    private ListDeque<item> sentinel;
    
    private class ListDeque<item> {
        item iitem;
        ListDeque<item> next;
        ListDeque<item> prev;

        public ListDeque() {
            next = null;
            prev = null;
        }

        public ListDeque(item i) {
            iitem = i;
            next = null;
            prev = null;
        }

        /* add a ListDeque into the first place */
        public void addFirst(item i, ListDeque<item> first) {
            ListDeque<item> newFirst = new ListDeque<item>(i);
            first.next.prev = newFirst;
            newFirst.next = first.next;
            first.next = newFirst;
            newFirst.prev = first;
        }

        /* add a ListDeque into the last place */
        public void addLast(item i, ListDeque<item> last) {
            ListDeque<item> newLast = new ListDeque<item>(i);
            last.prev.next = newLast;
            newLast.prev = last.prev;
            last.prev = newLast;
            newLast.next = last;
        }

        /* remove the first ListDeque */
        public void removeFirst(ListDeque<item> first) {
            first.next = first.next.next;
            first.next.prev = first;
        }

        /* remove the last ListDeque */
        public void removeLast(ListDeque<item> last) {
            last.prev = last.prev.prev;
            last.prev.next = last;
        }

        /* print the whole list */
        public void printListDeque() {
            ListDeque<item> p = this.next;
            while (p != this) {
                System.out.println(p.iitem);
                p = p.next;
            }
        }

        public item getRecursive(int index) {
            if (index == 0) {
                return this.iitem;
            }

            return this.next.getRecursive(index - 1);
        }

    }

    public LinkedListDeque() {
        size = 0;
        sentinel = new ListDeque<item>();
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    public void addFirst(item i) {
        sentinel.addFirst(i, sentinel);
        size++;
    }

    public void addLast(item i) {
        sentinel.addLast(i, sentinel);
        size++;
    }

    public void removeFirst() {
        /* avoid the size been nagetive */
        if (sentinel.next == sentinel) {
            return;
        }

        sentinel.removeFirst(sentinel);
        size--;
    }

    public void removeLast() {
        /* though it won't miss removing, we should avoid the size been nagetive */
        if (sentinel.prev == sentinel) {
            return;
        }

        sentinel.removeLast(sentinel);
        size--;
    }

    public Boolean isEmpty() {
        return size == 0;
    }

    public void printDeque() {
        sentinel.printListDeque();
    }

    public int size() {
        return size;
    }

    public item getRecursive(int index) {
        if (size == 0 || index >= size) {
            return null;
        }
        return sentinel.next.getRecursive(index);
    }
}