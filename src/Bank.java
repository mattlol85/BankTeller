public class Bank {
	private Account[] accounts;
	private int activeAccounts;

	// No-Arg
	public Bank() {

	}

	// Argument Constructor
	public Bank(Account[] a, int b) {
		accounts = a;
		activeAccounts = b;
	}

	// Setters
	public void setAccounts(Account[] a) {
		accounts = a;
	}

	public void setActiveAccounts(int aac) {
		activeAccounts = aac;
	}

	// Getters
	public Account[] getAccounts() {
		return accounts;
	}

	public int getActiveAccounts() {
		return activeAccounts;
	}

	/*
	 * METHODS:
	 */

	/*
	 * Input: Account object containing newly opened account Return: True if added
	 * to array sucessfully Return: False cannot add to array.
	 */
	public boolean openNewAcct(Account newAccount) {
		int index;
		index = findAcctIndex(newAccount.getAccountNumber());
		if (index == -1) {
			accounts[activeAccounts] = newAccount;
			activeAccounts++;
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
		for (int i = 0; i < activeAccounts; i++) {
			tempDep = accounts[i].getDepositor();
			if (reqSsn.equals(tempDep.getSsn()))
				return i;
		}
		return -1;
	}

	/*
	 * Input: Account number to find Return: Index of acctToFind
	 */
	public int findAcctIndex(int acctToFind) {
		for (int i = 0; i < activeAccounts; i++) {
			if (acctToFind == accounts[i].getAccountNumber())
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
			accounts[activeAccounts] = accounts[index];
			activeAccounts--;
			return true;
		} else {
			return false;
		}
	}
}
