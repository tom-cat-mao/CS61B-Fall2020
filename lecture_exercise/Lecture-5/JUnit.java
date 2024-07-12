public class JUnit<T> {
	private IntList<T> sentinel;
	private int size;

	private class IntList<T> {
		T x;
		IntList<T> next;
		IntList<T> prev;
	
		public IntList(T x, IntList<T> next, IntList<T> prev) {
			this.x = x;
			this.prev = prev;
			this.next = next;
		}
	}
	
	public JUnit() {
		this.sentinel = new IntList<>(null, null, null);
		this.sentinel.next = this.sentinel;
		this.sentinel.prev = this.sentinel;
		this.size = 0;
	}

	public JUnit(T x) {
		this();
		addLast(x);
	}

	public void addFirst(T x) {
		IntList<T> temp = new IntList<>(x, sentinel.next, sentinel);
		sentinel.next.prev = temp;
		sentinel.next = temp;
		size++;
	}

	public void addLast(T x) {
		IntList<T> temp = new IntList<>(x, sentinel, sentinel.prev);
		sentinel.prev.next = temp;
		sentinel.prev = temp;
		size++;
	}

	public T getFirst() {
		if (size == 0) {
			return null;
		}
		return sentinel.next.x;
	}

	public T getLast() {
		if (size == 0) {
			return null;
		}
		return sentinel.prev.x;
	}

	public T popFirst() {
		if (size == 0) {
			return null;
		}
		T temp = sentinel.next.x;
		sentinel.next = sentinel.next.next;
		sentinel.next.prev = sentinel;
		size--;
		return temp;
	}

	public T popLast() {
		if (size == 0) {
			return null;
		}
		T temp = sentinel.prev.x;
		sentinel.prev = sentinel.prev.prev;
		sentinel.prev.next = sentinel;
		size--;
		return temp;
	}

	public static void testDream() {
		int arr[] = {1, 2, 3, 4, 5};
		JUnit<Integer> list = new JUnit<>();
		for (int i : arr) {
			list.addLast(i);
		}

		System.out.println(list.getFirst()); // Should print 1
		System.out.println(list.getLast());  // Should print 5
		System.out.println(list.popFirst()); // Should print 1
		System.out.println(list.getFirst()); // Should print 2
		System.out.println(list.popLast());  // Should print 5
		System.out.println(list.getLast());  // Should print 4
	}

	public static void main(String[] args) {
		testDream();
	}
}