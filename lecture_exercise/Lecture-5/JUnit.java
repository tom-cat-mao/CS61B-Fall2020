public class JUnit<T> {
    private IntList<T> sentinel;
    private int size;

    private class IntList<T> {
        T x;
        IntList<T> next;
        IntList<T> prev;
    
        public IntList(T x, IntList next, IntList prev) {
            this.x = x;
            this.prev = prev;
            this.next = next;
        }

    }
    
    public JUnit() {
        this.sentinel = new IntList(114514, null, null);
        this.size = 0;
    }

    public JUnit(T x) {
        this.sentinel = new IntList(114514, null, null);
        this.sentinel.next = new IntList(x, sentinel, sentinel);
        this.sentinel.prev = sentinel.next;
        size = 1;
    }

    public void addFirst(T x) {
        IntList<T> temp = new IntList(x, sentinel.next, sentinel);
        temp.next.prev = temp;
        sentinel.next = temp;
    }

    public void addLast(T x) {
        IntList<T> temp = new IntList(x, sentinel, sentinel.prev);
        sentinel.prev = temp;
        temp.prev.next = temp;
    }

    public T getFirst() {
        return sentinel.next.x;
    }

    public T getLast() {
        return sentinel.prev.x;
    }

    public T popFirst() {

        if (size == 0) {
            return null;
        }

        T temp = sentinel.next.x;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        return temp;
    }

    public T popLast() {

        if (size == 0) {
            return null;
        }

        T temp = sentinel.prev.x;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        return temp;
    }
}