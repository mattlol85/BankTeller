import java.util.ArrayList;

public class Account {
	private Depositor depositor;
	private int accountNumber;
	private String accountType;
	private double balance;
	private ArrayList<Transaction> transactions;
	private boolean isOpen;			//TRUE = OPEN		FALSE = CLOSED

	// No-Arg Constructor
	public Account() {
		depositor = new Depositor();
		accountNumber = 0;
		accountType = "";
		balance = 0.0;
		transactions = new ArrayList<>();

	}

	// Argument Constructor
	public Account(int an, String at, double b, Depositor d,ArrayList<Transaction> newTrans,Boolean status) {
		accountNumber = an;
		accountType = at;
		balance = b;
		depositor = d;
		transactions = newTrans;
		isOpen = status;
		
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
	public void setAccountStatus(boolean s) {
		isOpen = s;
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
	public int getTransactionSize() {
		return transactions.size();
	}
	public ArrayList<Transaction> getTransactions(){
		return transactions;
	}
	public boolean getAccountStatus() {
		return isOpen;
	}
	/*
	 * Methods
	 */
	public void makeDeposit(Double deposit) {
		balance += deposit;
	}
	public void makeWithdrawl(Double with) {
		balance = balance - with;

	}
	public boolean closeAccount() {
		return true;
	}
	
	public boolean openAccount() {
		return true;
	}
	
	public void addTransaction(Transaction t) {
		transactions.add(t);
	}

}
