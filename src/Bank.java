import java.util.ArrayList;

public class Bank {
	private ArrayList<Account> accounts;

	// No-Arg
	public Bank() {

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

	/*
	 * Input: Account object containing newly opened account Return: True if added
	 * to array successfully Return: False cannot add to array.
	 */
	public boolean openNewAcct(Account newAccount) {
		int index;
		index = findAcctIndex(newAccount.getAccountNumber());
		if (index == -1) {
			accounts.add(newAccount);
			return true;
		} else {
			return false;
		}
	}

	/*
	 * Input: SSN to lookup as string Return: Index of Account
	 */
	public int findAcctSsn(String reqSsn) {
		Depositor tempDep;
		for (int i = 0; i < accounts.size(); i++) {
			tempDep = accounts.get(i).getDepositor();
			if (reqSsn.equals(tempDep.getSsn()))
				return i;
		}
		return -1;
	}

	/*
	 * Input: Account number to find Return: Index of acctToFind
	 */
	public int findAcctIndex(int acctToFind) {
		for (int i = 0; i < accounts.size(); i++) {
			if (acctToFind == accounts.get(i).getAccountNumber())
				return i;
		}
		return -1;
	}

	/*
	 * Input: Account number to delete Return: True if account was found Return:
	 * False if account was not found
	 */
	public boolean deleteAcct(int acctToDelete) {
		int index;
		index = findAcctIndex(acctToDelete);
		if (index != -1) {
			accounts.remove(index);
			return true;
		} else {
			return false;
		}
	}
}
