public class test {
    public static void main(String[] args) {
        ArrayDeque<Character> a = new ArrayDeque<>();
        a.addFirst('T');
        a.addFirst('C');
        a.addLast('J');
        a.addLast('M');
        System.out.println(a.removeFirst());
        System.out.println(a.removeLast());
        a.printDeque();
    }
}
