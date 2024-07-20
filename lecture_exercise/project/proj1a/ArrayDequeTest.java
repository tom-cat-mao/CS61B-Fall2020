

/* write a test to examine whether the arraydeque is useful
 * test addfirst, add last, remove first remove last and so on
 */
public class ArrayDequeTest {
    public static void main(String[] args) {
        ArrayDeque<String> array = new ArrayDeque<String>();
        array.addFirst("Tom");
        array.addLast("Cat");
        array.printDeque();

        array.addFirst("Jerry");
        array.addLast("Mouse");
        array.printDeque();

        array.removeFirst();
        array.printDeque();

        array.removeLast();
        array.printDeque();
    }
}
