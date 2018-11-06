import java.util.ArrayList;



/*
 * 
 * TODO totalAmountInSavingsAccts - sum total of balances in all Savings accounts
 * TODO totalAmountInCheckingAccts - sum total of balances in all Checking accounts
 * TODO totalAmountInCDAccts - total - sum total of balances in all CD accounts
 * TODO totalAmountInAllAccts - total - sum total of balances in all accounts
 * Add several static member variables and method:
 * Make sure to provide appropriate methods so as to allow for the addition to,
 *  subtraction from, and reading of, the current values each of these static variables.
 * 
 * 
 */
public class Bank {
	
	private  ArrayList<Account> accounts;
	public static double totalAmountInSavingsAccts = 0.0;
	public static double totalAmountInCheckingAccts = 0.0;
	public static double totalAmountInCDAccts = 0.0;
	public static double totalAmountInAllAccts = 0.0;


	// No-Arg
	public Bank() {
		accounts = new ArrayList<>();
	}

	// Argument Constructor
	public Bank(ArrayList<Account> a) {
		accounts = a;

	}

	// Setters
	public void setAccounts(ArrayList<Account> a) {
		accounts = a;
	}

	// Getters
	public ArrayList<Account> getAccounts() {
		return accounts;
	}

	public int getActiveAccounts() {
		return accounts.size();
	}

	/*
	 * METHODS:
	 */

	public int findAccountByNumber(int acctNumber) {
		return findAcct(acctNumber,1);
	}

	/*
	 * Input: SSN to lookup as string Return: Index of Account
	 */
	public int findAcctBySsn(String reqSsn) {
		return findAcct(Integer.parseInt(reqSsn),2);
	}

	/*
	 * Input: Account number to find Return: Index of acctToFind TODO ADD UPDATED
	 * COMMENTS
	 */
	private int findAcct(int numberToSeach, int searchType) {
		Depositor tempDep;

		if (searchType == 1) {
			for (int i = 0; i < accounts.size(); i++) {
				if (numberToSeach == accounts.get(i).getAccountNumber())
					return i;
			}
		} else if (searchType == 2) {
			for (int i = 0; i < accounts.size(); i++) {
				tempDep = accounts.get(i).getDepositor();
				if (numberToSeach == Integer.parseInt(tempDep.getSsn()))
					return i;
			}
		}
		return -1;
	}

	/*
	 * Input: Account object containing newly opened account Return: True if added
	 * to array successfully Return: False cannot add to array.
	 */
	public boolean openNewAcct(Account newAccount) {
		int index;
		index = findAcct(newAccount.getAccountNumber(),1);
		if (index == -1) {
			accounts.add(newAccount);
			return true;
		} else {
			return false;
		}
	}

	/*
	 * Input: Account number to delete Return: True if account was found Return:
	 * False if account was not found
	 */
	public boolean deleteAcct(int acctToDelete) {

		int index;
		index = findAcct(acctToDelete,1);
		if (index != -1) {
			accounts.remove(index);
			return true;
		} else {
			return false;
		}
	}
	/*
	 * Adds together total of all accounts.
	 */
	public static double addAllAcctsTotal(double savings,double cd,double checking) {
		
		return 0.0;
	}
	//I need some help my guy
	public static double getSavingsTotal(Account[] accounts) {
	
		
		return 0;
	}
}
