
public class Name {
	private String first;
	private String last;

	// No Argument Constructor
	public Name() {
		first = "test";
		last = "test";
	}

	// Argument Constructor
	public Name(String f, String l) {
		first = f;
		last = l;
	}

	// Copy Constructor
	public Name(Name n) {
		first = n.getFirst();
		last = n.getLast();
	}

	// Setters
	public void setFirst(String f) {
		first = f;
	}

	public void setLast(String l) {
		last = l;
	}

	// Getters
	public String getFirst() {
		return first;
	}

	public String getLast() {
		return last;
	}

	// Methods
	// equals method
	public boolean equals(Name n) {
		if (last.equals(n.last) && first.equals(n.first))
			return true; // Name found
		else
			return false; // Name not found
	}
	
	public String toString() {
			String str = String.format("%-10s%-10s", first,last);
			return str;
		
	}
}
