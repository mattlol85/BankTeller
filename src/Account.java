public class Account {
	private Depositor depositor;
	private int accountNumber;
	private String accountType;
	private double balance;

	// No-Arg Constructor
	public Account() {
		depositor = new Depositor();
		accountNumber = 0;
		accountType = "";
		balance = 0.0;

	}

	// Argument Constructor
	public Account(int an, String at, double b, Depositor d) {
		accountNumber = an;
		accountType = at;
		balance = b;
		depositor = d;
	}

	// Setters
	public void setDepositor(Depositor d) {
		depositor = d;
	}

	public void setAccountNumber(int an) {
		accountNumber = an;
	}

	public void setAccountType(String at) {
		accountType = at;
	}

	public void setBalance(Double b) {
		balance = b;
	}

	// Getters
	public Depositor getDepositor() {
		return depositor;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public String getAccountType() {
		return accountType;
	}

	public double getBalance() {
		return balance;
	}
	public void makeDeposit(Double deposit) {
		balance += deposit;
	}
	public void makeWithdrawl(Double with) {
		balance = balance - with;

	}
}
