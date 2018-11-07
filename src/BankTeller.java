import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * Name: Matthew Fitzgerald
 * BankAccounts v5.0
 * 
 */

/*
 * Specifically, you should minimally do the following:
1. The Bank class: do not overload either the toString(0 or the equals() method.
Be sure to print the values of all of these static variables when you print the database of accounts.
2. The Account class: overload both the toString() and the equals() methods.
3. The Depositor class: overload both the toString() and the equals() methods.
4. The Name class: overload both the toString() and the equals() methods.
5. The Transaction class: overload the toString() method.
Minimally, in addition to adding the static members, rewrite the following methods:
printAccts()
findAcct()
findAcctSSN()
printAcctInfo()
printAcctInfoWithTransactionHistory()
... (other methods as deemed necessary
 */
//TODO Add Account open/closed functionality
//TODO
//TODO
public class BankTeller {
	public static void main(String a[]) throws IOException {
		char choice; // menu item selected
		boolean not_done = true; // loop control flag
		ArrayList<Account> accounts = new ArrayList<>();

		// open input test cases file
		//File testFile = new File("testcases.txt");

		// create Scanner object
		//Scanner kybd = new Scanner(testFile);
		 Scanner kybd = new Scanner(System.in);

		// open the output file
		PrintWriter outFile = new PrintWriter("myoutput.txt");

		/* first part */
		/* fill and print initial database */
		Bank bankOfAmerica = new Bank(accounts);
		readDatabase(bankOfAmerica);
		printAccts(bankOfAmerica, outFile);

		/* second part */
		/* prompts for a transaction and then */
		/* call functions to process the requested transaction */
		do {
			menu();
			choice = kybd.next().charAt(0);
			switch (choice) {
			case 'q':
			case 'Q':
				not_done = false;
				printTransactions(bankOfAmerica, outFile);
				printAccts(bankOfAmerica, outFile);
				break;
			case 'b':
			case 'B':
				balance(bankOfAmerica, outFile, kybd);
				break;
			case 'd':
			case 'D':
				deposit(bankOfAmerica, outFile, kybd);
				break;
			case 'w':
			case 'W':
				withdrawal(bankOfAmerica, outFile, kybd);
				break;
			case 'n':
			case 'N':
				newAcct(bankOfAmerica, outFile, kybd);
				break;
			case 'x':
			case 'X':
				deleteAcct(bankOfAmerica, outFile, kybd);
				break;
			case 'i':
			case 'I':
				accountInfo(bankOfAmerica, kybd);
				break;
			case 'c':
			case 'C':
				closeAccount(bankOfAmerica, kybd, outFile);
				break;
			case 'h':
			case 'H':
				advancedAccountInfo(bankOfAmerica, kybd);
				break;
			case 'r':
			case 'R':
				reopenAcct(bankOfAmerica, kybd);
				break;
			case 'm':
				printAllDataToConsole(bankOfAmerica);
				break;
			default:
				outFile.println("|********************************************|");
				outFile.println("Error: " + choice + " is an invalid selection -  try again");
				outFile.println("|********************************************|");
				outFile.println("");
				outFile.flush();
				System.out.println("|********************************************|");
				System.out.println("Error: " + choice + " is an invalid selection -  try again");
				System.out.println("|********************************************|");
				System.out.println("");
				break;
			}

		} while (not_done);

		// close the output file
		outFile.close();

		// close the test cases input file
		kybd.close();
		System.out.println();
		System.out.println("The program is terminating");
	}

	/*
	 * Method Description: Gets called when quitting the program after printAccts()
	 * This method iterates though the array of accounts and then iterates through
	 * it's array of transactions and prints them.
	 */
	private static void printTransactions(Bank bank, PrintWriter outFile) {
		ArrayList<Account> accounts = bank.getAccounts();
		Account tempAcc;
		Depositor tempDep;
		Name tempName;
		for (int i = 0; i < bank.getActiveAccounts(); i++) {
			tempAcc = accounts.get(i);
			tempDep = tempAcc.getDepositor();
			tempName = tempDep.getName();
			outFile.println("*********************************************************");
			outFile.println("\t\t\tName: " + tempName.getFirst() + " " + tempName.getLast());
			outFile.println("\t\t\t\tTransactions");
			outFile.println("");
			// Import ArrayList of transactions from account
			ArrayList<Transaction> tempTrans = tempAcc.getTransactions();
			outFile.printf("|%-16s|%-20s|%-18s|", "Transaction Type", "Transaction Amount", "Transaction Status");
			outFile.println("");
			for (int j = 0; j < tempAcc.getTransactionSize(); j++) {
				tempTrans = tempAcc.getTransactions();
				// Print transaction data
				String sucessString = tempTrans.get(j).getTransactionSuccess() ? "Success" : "Failed";
				outFile.printf("|%-16s|", tempTrans.get(j).getTransactionType());
				outFile.printf("$%-19s",
						tempTrans.get(j).getTransactionAmount() == 0 ? "--" : tempTrans.get(j).getTransactionAmount());
				outFile.printf("|%-18s|", sucessString);
				outFile.println();
				outFile.flush();

			}
			outFile.println("|********************************************************|");
			outFile.println("");
			outFile.flush();
		}
	}

	private static void printAllDataToConsole(Bank bank) {
		ArrayList<Account> accounts = bank.getAccounts();
		ArrayList<Transaction> tempTrans;
		Depositor tempDep;
		Name tempName;
		for (int i = 0; i < accounts.size(); i++) { 
			/*
			 * 
			// Print Account data tempAcc = accounts.get(i);
			tempDep = accounts.get(i).getDepositor();
			tempName = tempDep.getName();
			System.out.println("****************************************************************");
			System.out.println("Name: " + tempName.getFirst() + " " + tempName.getLast());
			System.out.println("Account Number: " + accounts.get(i).getAccountNumber());
			System.out.println("SSN: " + tempDep.getSsn());
			System.out.println("Account Type: " + accounts.get(i).getAccountType());
			System.out.println("Balance: " + accounts.get(i).getBalance());
			System.out.println("Account Status: " + accounts.get(i).getAccountStatus());
			System.out.println("****************************************************************");
			*/
			System.out.println(accounts.get(i).toString());
			for (int j = 0; j < accounts.get(i).getTransactionSize(); j++) {
				tempTrans = accounts.get(i).getTransactions(); // Print transaction data
				System.out.println("Transaction Type: " + tempTrans.get(j).getTransactionType());
				System.out.println("Transaction Amount: " + tempTrans.get(j).getTransactionAmount());
				System.out.println("Transaction Status: " + tempTrans.get(j).getTransactionSuccess());
				System.out.println();
			}
		}

	}

	private static void reopenAcct(Bank bank, Scanner kybd) {
		ArrayList<Account> accounts = bank.getAccounts();
		System.out.println("Enter account number to reopen.");
		int acctToReopen = kybd.nextInt();
		int index = bank.findAccountByNumber(acctToReopen);

		if (accounts.get(index).getAccountStatus()) {
			// ACCOUNT IS ALREADY OPEN
			Transaction reopenTrans = new Transaction("Reopen Account", false);
			accounts.get(index).addTransaction(reopenTrans);
			System.out.println("Account is already open !");
			System.out.println("");

		} else if (!accounts.get(index).getAccountStatus()) {
			// ACCOUNT IS CLOSED; REOPEN ACCOUNT
			accounts.get(index).setAccountStatus(true);
			Transaction reopenTrans = new Transaction("Reopen Account", true);
			accounts.get(index).addTransaction(reopenTrans);
			accounts.get(index).setAccountStatus(true);
		}
	}

	/*
	 * 
	 * Method Description: Prompts the user enter their SSN. If the SSN is valid and
	 * in the database it will then display their account information, as well as
	 * their transaction history.
	 */
	private static void advancedAccountInfo(Bank bank, Scanner kybd) {
		String requestedSsn;
		// Import array of bank account objects
		ArrayList<Account> accounts = bank.getAccounts();
		Depositor tempDep;
		Name tempName;
		Account tempAcc;
		int index;

		System.out.println();
		System.out.println("Please enter your Social Security Number as one whole number\n");
		requestedSsn = kybd.next();
		index = bank.findAcctBySsn(requestedSsn);
		// Checks to see if the request matches the database

		if (index != -1) { // If match is found; create temporary account
			tempAcc = accounts.get(index);
			tempDep = tempAcc.getDepositor();
			tempName = tempDep.getName();
			System.out.println("|**************************************************|");
			System.out.println("Social Security Number: " + requestedSsn + "\n");
			System.out.println("Name: " + tempName.getFirst() + " " + tempName.getLast());
			System.out.println("Account number: " + tempAcc.getAccountNumber());
			System.out.println("Account Balance: " + tempAcc.getBalance());
			System.out.println("Account Type: " + tempAcc.getAccountType());
			System.out.println("Is Open?: " + tempAcc.getAccountStatus());
			System.out.println("|* * * * * * * * * * * * * * * * * * * * * * * * * *|");
			System.out.println("\tTransactions");
			// Import ArrayList of transactions from account
			ArrayList<Transaction> tempTrans = tempAcc.getTransactions();
			for (int i = 0; i < tempAcc.getTransactionSize(); i++) {
				tempTrans = tempAcc.getTransactions();
				// Print transaction data
				System.out.println("Transaction Type: " + tempTrans.get(i).getTransactionType());
				System.out.println("Transaction Amount: " + tempTrans.get(i).getTransactionAmount());
				System.out.println("Transaction Status: " + tempTrans.get(i).getTransactionSuccess());
				System.out.println();
			}
			System.out.println();

		} else if (index == -1) {
			System.out.println("|**************************************************|");
			System.out.println("ERROR SSN: " + requestedSsn + " not found!");
			System.out.println("|**************************************************|");
			System.out.println("");

		}
	}

	/*
	 * Method Description: This method prompts the user to enter their account
	 * number. If the account is found and valid then check the balance to ensure
	 * that it is zero. If the balance is zero the account is set to closed.
	 */
	private static void closeAccount(Bank bank, Scanner kybd, PrintWriter outFile) {
		int accountToClose;
		ArrayList<Account> accounts = bank.getAccounts();
		System.out.println("Please enter the account number you would like to close.\n");
		accountToClose = kybd.nextInt();

		int index = bank.findAccountByNumber(accountToClose);

		if (index != -1) {
			// ACCOUNT FOUND; CLOSE ACCOUNT
			if (accounts.get(index).getBalance() == 0.00) {
				if (!accounts.get(index).getAccountStatus()) { // ACCOUNT FOUND; ALREADY CLOSED
					System.out.println("|**************************************************|");
					System.out.println("Account: " + accountToClose + " is already closed");
					System.out.println("|**************************************************|");
					System.out.println("");
					return;
				}
				System.out.println("|**************************************************|");
				System.out.println("Account: " + accountToClose + " has been closed");
				System.out.println("|**************************************************|");
				System.out.println("");
				accounts.get(index).setAccountStatus(false);
				Transaction closeTransaction = new Transaction("Close Account", true);
				accounts.get(index).addTransaction(closeTransaction);
				// BALANCE IS ZERO
			} else if (accounts.get(index).getBalance() != 0.00) {
				// BALANCE IS NOT ZERO; CANNOT CLOSE
				System.out.println("|**************************************************|");
				System.out.println("Account: " + accountToClose + " still has a positive balance.");
				System.out.println("Balance: " + accounts.get(index).getBalance());
				System.out.println("|**************************************************|");
				System.out.println("");
				Transaction hasBalTransaction = new Transaction("Close Account", false);
				accounts.get(index).addTransaction(hasBalTransaction);
			}
		} else if (index == -1) {
			// ACCOUNT NOT FOUND
			outFile.println("|**************************************************|");
			outFile.println("Transaction Type: Close Account");
			outFile.println("Account number " + accountToClose + " not found.");
			outFile.println("Account number " + accountToClose + " not found.");
			outFile.println("|**************************************************|");
			outFile.println("");
			outFile.flush();
		}
	}

	/*
	 * Method Description: Prompts the user to enter their account number. If the
	 * number is valid, and the account is NOT closed, it will print the balance.
	 */
	private static void balance(Bank bank, PrintWriter outFile, Scanner kybd) {
		int requestedAccount;
		int index;
		ArrayList<Account> accounts = bank.getAccounts();
		System.out.println();
		System.out.print("Enter the account number: "); // prompt for account number
		requestedAccount = kybd.nextInt(); // read-in the account number
		System.out.println(requestedAccount);
		index = bank.findAccountByNumber(requestedAccount);
		// call findAcctByNumber to search if requestedAccount exists
		if (index == -1) // invalid account
		{
			outFile.println("|****************************************|");
			outFile.println("Transaction Requested: Balance Inquiry");
			outFile.println("Error: Account number " + requestedAccount + " does not exist");
			outFile.println("|****************************************|");
			outFile.println("");
			outFile.flush(); // flush the output buffer

		} else // valid account
		{
			if (!accounts.get(index).getAccountStatus()) {
				System.out.println("|************************************|");
				System.out.println("Account number " + requestedAccount + " is closed !");
				System.out.println("Please reopen the account to continue.");
				System.out.println("|************************************|");
				Transaction newTransaction = new Transaction("Balance Inquiry", false);
				accounts.get(index).addTransaction(newTransaction);
				return;

			}
			outFile.println("|********************************************|");
			outFile.println("Transaction Requested: Balance Inquiry");
			outFile.println("Account Number: " + requestedAccount);
			outFile.printf("Current Balance: $%.2f\n", accounts.get(index).getBalance());
			outFile.println("|********************************************|");
			outFile.println("");
			System.out.println("|********************************************|");
			System.out.println("Transaction Requested: Balance Inquiry");
			System.out.println("Account Number: " + requestedAccount);
			System.out.printf("Current Balance: $%.2f\n", accounts.get(index).getBalance());
			System.out.println("|********************************************|");
			System.out.println("");
			outFile.flush(); // flush the output buffer
			// Save transaction to array
			Transaction newTransaction = new Transaction("Balance Inquiry", true);
			accounts.get(index).addTransaction(newTransaction);

		}
	}

	/*
	 * Method Description: Prompts the user for an account number. If the account is
	 * found and valid it will again prompt the user for input, this time for the
	 * amount to deposit. If it is a valid amount it will make the deposit.
	 * 
	 */
	private static void deposit(Bank bank, PrintWriter outFile, Scanner kybd) {
		int requestedAccount;
		int index;
		double amountToDeposit;
		ArrayList<Account> accounts = bank.getAccounts();

		System.out.println();
		System.out.print("Enter the account number: "); // prompt for account number
		requestedAccount = kybd.nextInt(); // read-in the account number

		// call findAcct to search if requestedAccount exists
		index = bank.findAccountByNumber(requestedAccount);

		if (index == -1) // invalid account
		{
			outFile.println("|************************************|");
			outFile.println("Transaction Requested: Deposit");
			outFile.println("Error: Account number " + requestedAccount + " does not exist");
			outFile.println("|************************************|");
			outFile.println("");
			outFile.flush();
		} else // valid account
		{
			if (!accounts.get(index).getAccountStatus()) { // Valid Account; invalid status [CLOSED]
				outFile.println("|************************************|");
				outFile.println("Transaction Requested: Deposit");
				outFile.println("Error: Account number " + requestedAccount + " is closed.");
				outFile.println("|************************************|");
				outFile.println("");
				outFile.flush();
				System.out.println("|************************************|");
				System.out.println("Transaction Requested: Deposit");
				System.out.println("Error: Account number " + requestedAccount + " is closed.");
				System.out.println("|************************************|");
				Transaction closedAcctTransaction = new Transaction("Deposit", false);
				accounts.get(index).addTransaction(closedAcctTransaction);
				return;
			}
			System.out.print("Enter amount to deposit: "); // prompt for amount to deposit
			amountToDeposit = kybd.nextDouble(); // read-in the amount to deposit

			if (amountToDeposit <= 0.00) {
				// invalid amount to deposit
				outFile.println("|************************************|");
				outFile.println("Transaction Requested: Deposit");
				outFile.println("Account Number: " + requestedAccount);
				outFile.printf("Error: $%.2f is an invalid amount\n", amountToDeposit);
				outFile.println("|************************************|");
				outFile.println("");
				outFile.flush();
				Transaction newTrans = new Transaction("Deposit", amountToDeposit, false);
				accounts.get(index).addTransaction(newTrans);
			} else {
				outFile.println("|************************************|");
				outFile.println("Transaction Requested: Deposit");
				outFile.println("Account Number: " + requestedAccount);
				outFile.printf("Old Balance: $%.2f\n", accounts.get(index).getBalance());
				outFile.println("Amount to Deposit: $" + amountToDeposit);
				accounts.get(index).makeDeposit(amountToDeposit); // make the deposit
				outFile.printf("New Balance: $%.2f\n", accounts.get(index).getBalance());
				System.out.println("Deposit complete.");
				outFile.println("|************************************|");
				outFile.println("");
				outFile.flush();
				Transaction newTrans = new Transaction("Deposit", amountToDeposit, true);
				accounts.get(index).addTransaction(newTrans);

			}
		}
	}

	/*
	 * Input: bank object, outFile for logging, kybd for user input Method Desc:
	 * Asks user to enter acct # to close if the acct number is valid it checks the
	 * balance if the balance is zero, it will remove the account
	 */
	private static void deleteAcct(Bank bank, PrintWriter outFile, Scanner kybd) {
		int accountToDelete;
		int index;
		ArrayList<Account> accounts = bank.getAccounts();
		System.out.println();
		System.out.print("Please enter the account number you would like to delete.\n");
		accountToDelete = kybd.nextInt();

		index = bank.findAccountByNumber(accountToDelete);

		if (index != -1) {
			// ACCOUNT FOUND
			if (accounts.get(index).getBalance() == 0.00) {
				outFile.println("|**************************************************|");
				outFile.println("Account: " + accountToDelete + " has been deleted");
				outFile.println("|**************************************************|");
				outFile.println("");
				bank.deleteAcct(accountToDelete);
				// BALANCE IS ZERO
			} else {
				// BALANCE IS NOT ZERO
				outFile.println("|**************************************************|");
				outFile.println("Account: " + accountToDelete + " still has a positive balance.");
				outFile.printf("Balance: $%-9.2s \n", accounts.get(index).getBalance());
				outFile.println("|**************************************************|");
				outFile.println("");
				Transaction newTrans = new Transaction("Delete", accounts.get(index).getBalance(), false);
				accounts.get(index).addTransaction(newTrans);
			}
		} else if (index == -1) {
			// ACCOUNT NOT FOUND
			outFile.println("|**************************************************|");
			System.out.println("Account number " + accountToDelete + " not found.");
			outFile.println("Account number " + accountToDelete + " not found.");
			outFile.println("|**************************************************|");
			outFile.println("");
		}
		outFile.flush();
	}

	/*
	 * Input: Bank object, Scanner kybd for user input Method Desc: Asks the user
	 * for the reqested accounts Social Security Number, if it is found it will
	 * print their information.
	 */
	private static void accountInfo(Bank bank, Scanner kybd) {
		String requestedSsn;
		// Import array of bank account objects
		ArrayList<Account> accounts = bank.getAccounts();
		Depositor tempDep;
		Name tempName;
		Account tempAcc;
		int index;

		System.out.println();
		System.out.println("Please enter your Social Security Number as one whole number\n");
		requestedSsn = kybd.next();
		index = bank.findAcctBySsn(requestedSsn);
		// Checks to see if the request matches the database

		if (index != -1) { // If match is found; create temporary account
			tempAcc = accounts.get(index);
			tempDep = tempAcc.getDepositor();
			tempName = tempDep.getName();
			System.out.println("|***************************************************|");
			System.out.println("Social Security Number: " + requestedSsn + "\n");
			System.out.println("Name: " + tempName.getFirst() + " " + tempName.getLast());
			System.out.println("Account number: " + tempAcc.getAccountNumber());
			System.out.println("Account Balance: " + tempAcc.getBalance());
			System.out.println("Account Type: " + tempAcc.getAccountType());
		} else if (index == -1) {
			System.out.println("|**************************************************|");
			System.out.println("ERROR SSN: " + requestedSsn + " not found!");
			System.out.println("|**************************************************|");
			System.out.println("");

		}
	}

	/*
	 * Input: bank object, outFile for logging, kybd for user input Method Desc:
	 * Asks the user for their personal information. If the information doesn't
	 * conflict with current database it will add the account to the array.
	 */
	public static void newAcct(Bank bank, PrintWriter outFile, Scanner kybd) {
		int choice;
		// Initialize new ArrayList for the new account
		ArrayList<Transaction> transactions = new ArrayList<>();
		// Set a boolean for when the user needs to choose an account type
		boolean not_done = true;
		System.out.print("Enter your deisred account number.");
		int newAcctNum = kybd.nextInt();
		if ((bank.findAccountByNumber(newAcctNum)) == -1) {
			// Account number does not exist
			System.out.println("Enter your Social Security Number\n");
			System.out.println("ENTER AS ONE NUMBER\n");
			String ssn = kybd.next();
			if (bank.findAcctBySsn(ssn) != -1) {
				// SSN already exists
				outFile.println("|************************************|");
				outFile.println("SSN: " + ssn + " aready exists");
				outFile.println("|************************************|");
				outFile.println("");
			}
			System.out.print("Enter first name: ");
			String firstName = kybd.next();
			System.out.print("Enter last name: ");
			String lastName = kybd.next();

			Name myName = new Name(lastName, firstName);
			Depositor newDepositor = new Depositor(myName, ssn);

			do {
				showAcctMenu();
				choice = kybd.next().charAt(0);
				switch (choice) {
				case '1':
					System.out.println("Account Sucessfully created!");
					System.out.println();
					Account newAccountSavings = new Account(newAcctNum, "Savings", 0.0, newDepositor, transactions,
							true);
					Transaction newTrans = new Transaction("New Account", true);
					newAccountSavings.addTransaction(newTrans);
					outFile.println("|************************************|");
					outFile.println("New account created:");
					outFile.println("Social Security Number: " + newDepositor.getSsn() + "\n");
					outFile.println("Name: " + firstName + " " + lastName);
					outFile.println("Account number: " + newAccountSavings.getAccountNumber());
					outFile.println("Account Balance: " + newAccountSavings.getBalance());
					outFile.println("Account Type: " + newAccountSavings.getAccountType());
					outFile.println("|************************************|");
					outFile.println();
					bank.openNewAcct(newAccountSavings);

					not_done = false;
					break;
				case '2':
					System.out.println("Account Sucessfully created!");
					System.out.println();
					Account newAccountChecking = new Account(newAcctNum, "Checking", 0.0, newDepositor, transactions,
							true);
					Transaction checkingTrans = new Transaction("New Account", true);
					newAccountChecking.addTransaction(checkingTrans);
					outFile.println("|************************************|");
					outFile.println("*New account created*:");
					outFile.println("Social Security Number: " + newDepositor.getSsn() + "\n");
					outFile.println("Name: " + firstName + " " + lastName);
					outFile.println("Account number: " + newAccountChecking.getAccountNumber());
					outFile.println("Account Balance: " + newAccountChecking.getBalance());
					outFile.println("Account Type: " + newAccountChecking.getAccountType());
					outFile.println("|************************************|");
					outFile.println();
					bank.openNewAcct(newAccountChecking);
					not_done = false;
					break;
				case '3':
					System.out.println("Account Sucessfully created!");
					System.out.println();
					Account newAccountCD = new Account(newAcctNum, "CD", 0.0, newDepositor, transactions, true);
					Transaction cDTrans = new Transaction("New Account", true);
					newAccountCD.addTransaction(cDTrans);
					bank.openNewAcct(newAccountCD);
					outFile.println("|************************************|");
					outFile.println("New account created:");
					outFile.println("Social Security Number: " + newDepositor.getSsn() + "\n");
					outFile.println("Name: " + firstName + " " + lastName);
					outFile.println("Account number: " + newAccountCD.getAccountNumber());
					outFile.println("Account Balance: " + newAccountCD.getBalance());
					outFile.println("Account Type: " + newAccountCD.getAccountType());
					outFile.println("|************************************|");
					outFile.println();
					not_done = false;
					break;
				default:
					System.out.println("Invalid selction.");
					break;
				}
			} while (not_done);
			outFile.flush();
		} else if (bank.findAccountByNumber(newAcctNum) != -1) {
			outFile.println("|****************************************************|");
			System.out.println("Account " + newAcctNum + " already in database.");
			outFile.println("Account " + newAcctNum + " already in database.");
			outFile.println("|****************************************************|");
			outFile.println("");
		}

	}

	// This method reads the database file and fills the ArrayList
	// of account objects.
	private static void readDatabase(Bank bank) throws IOException {
		// Attach database
		File dbFile = new File("myinput.txt");
		// Create Scanner
		Scanner sc = new Scanner(dbFile);
		String line;
		ArrayList<Account> accounts = bank.getAccounts();

		while (sc.hasNext()) {
			line = sc.nextLine();
			String tokens[] = line.split(" ");

			Name tempName = new Name(tokens[2], tokens[3]);

			Depositor tempDep = new Depositor(tempName, tokens[4]);
			ArrayList<Transaction> transactions = new ArrayList<>();
			Account tempAcc = new Account(Integer.parseInt(tokens[0]), tokens[5], Double.parseDouble(tokens[1]),
					tempDep, transactions, true);

			accounts.add(tempAcc);
		}
		// Close file.
		sc.close();
	}

	/*
	 * Method menu() Input: none Process: Prints the menu of transaction choices
	 * Output: Prints the menu of transaction choices
	 */
	public static void menu() {
		System.out.println();
		System.out.println("Select one of the following transactions:");
		System.out.println("\t****************************");
		System.out.println("\t    List of Choices         ");
		System.out.println("\t****************************");
		System.out.println("\t     W -- Withdrawal");
		System.out.println("\t     D -- Deposit");
		System.out.println("\t     N -- New Account");
		System.out.println("\t     B -- Balance Inquiry");
		System.out.println("\t     I -- Account Info");
		System.out.println("\t     X -- Delete Account");
		System.out.println("\t     Q -- Quit");
		System.out.println("\t     H -- Advanced Account Info");
		System.out.println("\t     C -- Close Account");
		System.out.println("\t     R -- Reopen Account");
		System.out.println("\t     m -- DISPLAY ALL DATA ON SCREEN");
		System.out.println();
		System.out.print("\tEnter your selection: \n");
		System.out.println("");
	}

	/*
	 * Method printAccts: Input: accounts - array of BankAccount objects numAccts -
	 * number of active accounts outFile - reference to the output file Process:
	 * Prints the database of accounts and balances Output: Prints the database of
	 * accounts and balances
	 */
	public static void printAccts(Bank bankOfAmerica, PrintWriter outFile) {
		ArrayList<Account> tempAccts = bankOfAmerica.getAccounts();
		Account tempAcc = new Account();
		Depositor tempDep = new Depositor();
		Name tempName = new Name();
		outFile.println();
		outFile.println("\t\tDatabase of Bank Accounts");
		outFile.printf("");
		outFile.println("|*****************************************************************************************|");
		outFile.printf("%-16s|%-15s|%-11s|%-12s|%-18s|%-13s|", "|Account Number", "Balance", "First", "Last", "SSN",
				"Account Type");
		outFile.println("");

		for (int index = 0; index < tempAccts.size(); index++) {
			tempAcc = tempAccts.get(index);
			tempDep = tempAcc.getDepositor();
			tempName = tempDep.getName();

			outFile.printf("|%-15d|", tempAcc.getAccountNumber());
			outFile.printf("$%-14.2f|", tempAcc.getBalance());
			outFile.printf("%-11s|", tempName.getFirst());
			outFile.printf("%-12s|", tempName.getLast());
			outFile.printf("%-18s|", tempDep.getSsn());
			outFile.printf("%-13s|", tempAcc.getAccountType());
			outFile.println("");
		}
		outFile.println("|*****************************************************************************************|");
		outFile.println();

		// flush the output file
		outFile.flush();
	}

	/*
	 * Method Description: Used in newAcct() to display a small menu for the user to
	 * use when selecting an account.
	 */
	private static void showAcctMenu() {
		System.out.println();
		System.out.println("Select one of the following transactions:");
		System.out.println("\t****************************");
		System.out.println("\t    List of Choices         ");
		System.out.println("\t****************************");
		System.out.println("\t     1 -- Savings");
		System.out.println("\t     2 -- Checking");
		System.out.println("\t     3 -- CD");
		System.out.println();
		System.out.print("\tEnter your selection: ");

	}
	/*
	 * 
	 * Method Description: Asks the user for their account number. If the account is
	 * found it will then ask for the amount to deposit. If that amount is valid it
	 * will deposit into the account.
	 */

	public static void withdrawal(Bank bank, PrintWriter outFile, Scanner kybd) {
		int requestedAccount;
		int index;
		double amountToWithdraw;
		ArrayList<Account> accounts = bank.getAccounts();

		System.out.println();
		System.out.print("Enter the account number: "); // prompt for account number
		requestedAccount = kybd.nextInt(); // read-in the account number

		// call findAcct to search if requestedAccount exists
		index = bank.findAccountByNumber(requestedAccount);

		if (index == -1) {
			// INVALID ACCOUNT
			outFile.println("|************************************************|");
			outFile.println("Transaction Requested: Withdrawal");
			outFile.println("Error: Account number " + requestedAccount + " does not exist");
			outFile.println("|************************************************|");
			outFile.println("");
		} else {
			// VALID ACCOUNT
			if (!accounts.get(index).getAccountStatus()) { // ACCOUNT IS CLOSED

				Transaction closedAcctTransaction = new Transaction("Withdraw", false);
				accounts.get(index).addTransaction(closedAcctTransaction);
				System.out.println("|************************************|");
				System.out.println("Transaction Requested: Deposit");
				System.out.println("Error: Account number " + requestedAccount + "is closed.");
				System.out.println("|************************************|");
				return;
			}
			System.out.print("Enter amount to withdraw: "); // prompt for amount to withdraw
			amountToWithdraw = kybd.nextDouble(); // read-in the amount to withdraw

			if (amountToWithdraw <= 0.00 || amountToWithdraw > accounts.get(index).getBalance()) { // Checks for invalid
																									// // amount to
				// Withdraw
				outFile.println("|************************************|");
				outFile.println("Transaction Requested: Withdrawal");
				outFile.println("Account Number: " + requestedAccount);
				if (amountToWithdraw > accounts.get(index).getBalance()) {
					// NOT ENOUGH MONEY; INSUFFICIENT FUNDS
					outFile.printf("You have $%.2f in your account.\n", accounts.get(index).getBalance());
					outFile.println("Insufficient Funds.");
					System.out.printf("You have $%.2f in your account.\n", accounts.get(index).getBalance());
					System.out.println("Insufficient Funds.");
					System.out.println("");
					outFile.println("|************************************|");
					outFile.println("");
					// TRANSACTION
					Transaction withTransaction = new Transaction("Withdraw", amountToWithdraw, false);
					accounts.get(index).addTransaction(withTransaction);
				}
				outFile.printf("Error: $%.2f is an invalid amount\n", amountToWithdraw);
				outFile.println("|************************************|");
				outFile.println("");
				Transaction withTransaction = new Transaction("Withdraw", amountToWithdraw, false);
				accounts.get(index).addTransaction(withTransaction);

			} else if (amountToWithdraw <= accounts.get(index).getBalance()) {
				outFile.println("|************************************|");
				outFile.println("Transaction Requested: Withdrawal");
				outFile.println("Account Number: " + requestedAccount);
				outFile.printf("Old Balance: $%.2f\n", accounts.get(index).getBalance());
				outFile.println();
				outFile.println("Amount to Withdraw: $" + amountToWithdraw);
				// DEPOSIT
				accounts.get(index).setBalance(accounts.get(index).getBalance() - amountToWithdraw);
				outFile.printf("New Balance: $%.2f\n", accounts.get(index).getBalance());
				outFile.println("|************************************|");
				outFile.println("");
				// TRANSACTION
				Transaction withTransaction = new Transaction("Withdraw", amountToWithdraw, true);
				accounts.get(index).addTransaction(withTransaction);
			}
		}

		outFile.flush(); // flush the output buffer

	}
}
