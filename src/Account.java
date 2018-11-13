import java.util.ArrayList;

public class Account {
	private Depositor depositor;
	private int accountNumber;
	private String accountType;
	private double balance;
	private ArrayList<Transaction> transactions;
	private boolean isOpen; // TRUE = OPEN FALSE = CLOSED



	// No-Arg Constructor
	public Account() {
		depositor = new Depositor();
		accountNumber = 0;
		accountType = "";
		balance = 0.0;
		transactions = new ArrayList<>();

	}

	// Argument Constructor
	public Account(int an, String at, double b, Depositor d, ArrayList<Transaction> newTrans, Boolean status) {
		accountNumber = an;
		accountType = at;
		balance = b;
		depositor = d;
		transactions = newTrans;
		isOpen = status;

	}
	//Copy Constructor
	public Account (Account a) {
		accountNumber = a.getAccountNumber();
		accountType = a.getAccountType();
		balance = a.getBalance();
		depositor = a.getDepositor();
		transactions = a.getTransactions();
		isOpen = a.getAccountStatus();
		
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

	public void closeAccount() {
		isOpen = false;
	}

	public void openAccount() {
		isOpen = true;
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

	public ArrayList<Transaction> getTransactions() {
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
		Transaction depTransaction = new Transaction("Deposit", deposit, true);
		addTransaction(depTransaction);
		Bank.updateTotals(deposit, accountType);
	}

	public void makeWithdrawl(Double with) {
		balance = balance - with;
		Transaction withTransaction = new Transaction("Withdraw", with, true);
		addTransaction(withTransaction);
		Bank.updateTotals(-with, accountType);


	}

	public void addTransaction(Transaction t) {
		transactions.add(t);
	}
	public  String toString() {
		Name n = new Name(depositor.getName());
		String str = String.format("%-16s|$%-15.2f|%-11s|%-12s|%-18s|%-13s|",accountNumber,balance,n.getFirst(),n.getLast(),depositor.getSsn(),accountType);
		//outFile.printf("%-16s|%-15s|%-11s|%-12s|%-18s|%-13s|", "|Account Number", "Balance", "First", "Last", "SSN",
		//		"Account Type");
		//outFile
		return str;
	}
	public boolean equals(Account a) {
		if(accountNumber == a.getAccountNumber() && accountType == a.getAccountType()) {
			return true;
		}else {
			return false;
		}
	}
}
