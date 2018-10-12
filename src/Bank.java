import java.util.ArrayList;

public class Bank {
	
	private  ArrayList<Account> accounts;

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
}
