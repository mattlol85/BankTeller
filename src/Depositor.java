
public class Depositor {
	private Name name;
	private String ssn;

	// No-Argument Constructor
	public Depositor() {
		name = new Name();
		ssn = "";
	}

	// Argument Constructor
	public Depositor(Name n, String s) {
		name = n;
		ssn = s;

	}
	//Copy Constructor
	public Depositor(Depositor d) {
		name = d.getName();
		ssn = d.getSsn();
	}

	// Setters
	public void setName(Name n) {
		name = n;
	}

	public void setSsn(String s) {
		ssn = s;
	}

	// Getters
	public Name getName() {
		return name;
	}

	public String getSsn() {
		return ssn;
	}
}
