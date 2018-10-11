
public class Name {
	private String first;
	private String last;
	//No Argument Constructor
	public Name() {
		first = "";
		last = "";
	}
	//Argument Constructor
	public Name(String f, String l) {
		first = f;
		last = l;
	}
	//Setters
	public void setFirst(String f) {
		first = f;
	}
	public void setLast(String l) {
		last = l;
	}
	
	//Getters
	public String getFirst() {
		return first;
	}
	public String getLast() {
		return last;
	}
}
