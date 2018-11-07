import java.util.ArrayList;



/*
 * totalAmountInSavingsAccts - sum total of balances in all Savings accounts
   totalAmountInCheckingAccts - sum total of balances in all Checking accounts
   totalAmountInCDAccts - total - sum total of balances in all CD accounts
   totalAmountInAllAccts - total - sum total of balances in all accounts
 */
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
	}

	public void makeWithdrawl(Double with) {
		balance = balance - with;

	}

	public void addTransaction(Transaction t) {
		transactions.add(t);
	}
	public  String toString() {
		String str = String.format("%-10s%-10s%-10s%-10s%-10s",
														depositor.toString(),
														accountNumber,
														balance,
														accountType,
														isOpen ? "Open" : "Closed");
		return str;
	}
}
