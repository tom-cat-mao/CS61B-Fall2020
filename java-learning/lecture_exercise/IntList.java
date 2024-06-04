public class IntList {

	int x;
	IntList rest;

	IntList(IntList L, int x) {
		this.x = x;
		this.rest = L;
	}

	public int get(int i) {
		if (i == 0)
			return x;
		return this.get(i--);
	}

	public int size() {
		if (this.rest == null)
			return 1;
		return this.rest.size() + 1;
	}
}
