public class JUnit {
    private IntList sentinel;
    private int size;

    private class IntList {
        private int x;
        private IntList next;
        private IntList prev;
    
        public IntList(int x, IntList next, IntList prev) {
            this.x = x;
            this.prev = prev;
            this.next = next;
        }
    
        public int getFirst() {
            return this.x;
        } 
    
        public int getLast() {
            return this.prev.x;
        }
    }
    
    public JUnit() {
        this.sentinel.x = 114514;
        this.sentinel.prev = null;
        this.sentinel.next = null;
    }

    public JUnit(int x) {
        this.sentinel.x = 114514;
        this.sentinel.next = new IntList(x, sentinel, sentinel);
        this.sentinel.prev = sentinel.next;
    }
}