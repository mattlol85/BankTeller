import java.io.*;
import java.util.Scanner;

/*
 * Name: Matthew Fitzgerald
 * BankAccounts v3.0
 * 
 */
public class BankAccounts_3 {
	public static void main(String a[]) throws IOException {
		// Constant declaration of maximum accounts
		final int MAX_ACCOUNTS = 50;
		char choice; // menu item selected
		boolean not_done = true; // loop control flag
		Account[] accounts = new Account[MAX_ACCOUNTS];

		// open input test cases file
		File testFile = new File("testcases.txt");

		// create Scanner object
		Scanner kybd = new Scanner(testFile);
		// Scanner kybd = new Scanner(System.in);

		// open the output file
		PrintWriter outFile = new PrintWriter("myoutput.txt");

		/* first part */
		/* fill and print initial database */
		Bank bankOfAmerica = new Bank(accounts, readDatabase(accounts, MAX_ACCOUNTS));
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
			default:
				outFile.println("|********************************************|");
				outFile.println("Error: " + choice + " is an invalid selection -  try again");
				outFile.println("|********************************************|");
				outFile.println("");
				outFile.flush();
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

	private static void balance(Bank bank, PrintWriter outFile, Scanner kybd) {
		int requestedAccount;
		int index;
		Account[] accounts = bank.getAccounts();

		System.out.println();
		System.out.print("Enter the account number: "); // prompt for account number
		requestedAccount = kybd.nextInt(); // read-in the account number
		System.out.println(requestedAccount);
		index = bank.findAcctIndex(requestedAccount);
		// call findAcct to search if requestedAccount exists
		if (index == -1) // invalid account
		{
			outFile.println("|************************************|");
			outFile.println("Transaction Requested: Balance Inquiry");
			outFile.println("Error: Account number " + requestedAccount + " does not exist");
			outFile.println("|************************************|");
			outFile.println("");
			outFile.flush(); // flush the output buffer

		} else // valid account
		{
			outFile.println("|********************************************|");
			outFile.println("Transaction Requested: Balance Inquiry");
			outFile.println("Account Number: " + requestedAccount);
			outFile.printf("Current Balance: $%.2f\n", accounts[index].getBalance());
			outFile.println("|********************************************|");
			outFile.println("");
			outFile.flush(); // flush the output buffer
		}
	}

	private static void deposit(Bank bank, PrintWriter outFile, Scanner kybd) {
		int requestedAccount;
		int index;
		double amountToDeposit;
		Account[] accounts = bank.getAccounts();

		System.out.println();
		System.out.print("Enter the account number: "); // prompt for account number
		requestedAccount = kybd.nextInt(); // read-in the account number

		// call findAcct to search if requestedAccount exists
		index = bank.findAcctIndex(requestedAccount);

		if (index == -1) // invalid account
		{
			outFile.println("|************************************|");
			outFile.println("Transaction Requested: Deposit");
			outFile.println("Error: Account number " + requestedAccount + " does not exist");
			outFile.println("|************************************|");
			outFile.println("");
		} else // valid account
		{
			System.out.print("Enter amount to deposit: "); // prompt for amount to deposit
			amountToDeposit = kybd.nextDouble(); // read-in the amount to deposit

			if (amountToDeposit <= 0.00) {
				// invalid amount to deposit
				outFile.println("|************************************|");
				outFile.println("Transaction Requested: Deposit");
				outFile.println("Account Number: " + requestedAccount);
				outFile.printf("Error: $%.2f is an invalid amount\n", amountToDeposit);
				outFile.println("|************************************|");
				outFile.println();
			} else {
				outFile.println("|************************************|");
				outFile.println("Transaction Requested: Deposit");
				outFile.println("Account Number: " + requestedAccount);
				outFile.printf("Old Balance: $%.2f\n", accounts[index].getBalance());
				outFile.println();
				outFile.println("Amount to Deposit: $" + amountToDeposit);
				accounts[index].makeDeposit(amountToDeposit); // make the deposit
				outFile.printf("New Balance: $%.2f\n", accounts[index].getBalance());
				System.out.println("Deposit complete.");
				outFile.println("|************************************|");

			}
		}
		outFile.println();

		outFile.flush(); // flush the output buffer
	}

	/*
	 * Input: bank object, outFile for logging, kybd for user input Method Desc:
	 * Asks user to enter acct # to close if the acct number is valid it checks the
	 * balance if the balance is zero, it will remove the account
	 */
	private static void deleteAcct(Bank bank, PrintWriter outFile, Scanner kybd) {
		int accountToDelete;
		int index;
		Account[] tempAccts = bank.getAccounts();
		System.out.println();
		System.out.print("Please enter the account number you would like to close.\n");
		accountToDelete = kybd.nextInt();
		index = bank.findAcctIndex(accountToDelete);

		if (index != -1) {
			// ACCOUNT FOUND
			if (tempAccts[index].getBalance() == 0.00) {
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
				outFile.println("Balance: " + tempAccts[index].getBalance());
				outFile.println("|**************************************************|");
				outFile.println("");
				System.out.println("");
			}
		} else if (index == -1) {
			outFile.println("|**************************************************|");
			System.out.println("Account number " + accountToDelete + " not found.");
			outFile.println("Account number " + accountToDelete + " not found.");
			outFile.println("|**************************************************|");
			outFile.println("");
		}
	}

	/*
	 * Input: Bank object, Scanner kybd for user input Method Desc: Asks the user
	 * for the reqested accounts Social Security Number, if it is found it will
	 * print their information.
	 */
	private static void accountInfo(Bank bank, Scanner kybd) {
		// TODO Auto-generated method stub
		String requestedSsn;
		// Import array of bank account objects
		Account[] accounts = bank.getAccounts();
		Depositor tempDep;
		Name tempName;
		Account tempAcc;
		int index;

		System.out.println();
		System.out.println("Please enter your Social Security Number as one whole number\n");
		requestedSsn = kybd.next();
		index = bank.findAcctSsn(requestedSsn);
		// Checks to see if the request matches the database

		if (index != -1) { // If match is found; create temporary account
			tempAcc = accounts[index];
			tempDep = tempAcc.getDepositor();
			tempName = tempDep.getName();
			System.out.println("|**************************************************|");
			System.out.println("Social Security Number: " + requestedSsn + "\n");
			System.out.println("Name: " + tempName.getFirst() + " " + tempName.getLast());
			System.out.println("Account number: " + tempAcc.getAccountNumber());
			System.out.println("Account Balance: " + tempAcc.getBalance());
			System.out.println("Account Type: " + tempAcc.getAccountType());
			System.out.println("|**************************************************|");
			System.out.println();

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
		boolean not_done = true;
		System.out.print("Enter your deisred account number.");
		int newAcctNum = kybd.nextInt();
		System.out.println("Enter your Social Security Number\n");
		System.out.println("ENTER AS ONE NUMBER\n");
		String ssn = kybd.next();
		if (bank.findAcctSsn(ssn) != -1) {
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

		if ((bank.findAcctIndex(newAcctNum)) == -1) {
			// Account number does not exist
			do {
				showAcctMenu();
				choice = kybd.next().charAt(0);
				switch (choice) {
				case '1':
					System.out.println("Account Sucessfully created!");
					System.out.println();
					Account newAccountSavings = new Account(newAcctNum, "Savings", 0.0, newDepositor);
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
					Account newAccountChecking = new Account(newAcctNum, "Checking", 0.0, newDepositor);
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
					Account newAccountCD = new Account(newAcctNum, "CD", 0.0, newDepositor);
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
		} else if (bank.findAcctIndex(newAcctNum) != -1) {
			outFile.println("|****************************************************|");
			System.out.println("Account " + newAcctNum + " already in database.");
			outFile.println("Account " + newAcctNum + " already in database.");
			outFile.println("|****************************************************|");
			outFile.println("");
		}

	}

	// This method reads the database and fills the array
	// of account objects. Returns number of active accounts.
	private static int readDatabase(Account[] accounts, int maxAcc) throws IOException {
		// TODO Add comments
		// Attach database
		File dbFile = new File("myinput.txt");
		// Create Scanner
		Scanner sc = new Scanner(dbFile);
		int count = 0;
		String line;

		while (sc.hasNext() && count < maxAcc) {
			line = sc.nextLine();
			String tokens[] = line.split(" ");
			Name tempName = new Name(tokens[2], tokens[3]);
			Depositor tempDep = new Depositor(tempName, tokens[4]);
			Account tempAcc = new Account(Integer.parseInt(tokens[0]), tokens[5], Double.parseDouble(tokens[1]),
					tempDep);
			accounts[count] = tempAcc;
			count++;
		}
		// Close file.
		sc.close();

		// Returns number of accounts in file.
		return count;
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
		System.out.println();
		System.out.print("\tEnter your selection: ");
	}

	/*
	 * Method printAccts: Input: accounts - array of BankAccount objects numAccts -
	 * number of active accounts outFile - reference to the output file Process:
	 * Prints the database of accounts and balances Output: Prints the database of
	 * accounts and balances
	 */
	public static void printAccts(Bank bankOfAmerica, PrintWriter outFile) {
		Account[] tempAccs = bankOfAmerica.getAccounts();
		Account tempAcc = new Account();
		Depositor tempDep = new Depositor();
		Name tempName = new Name();
		outFile.println();
		outFile.println("\t\tDatabase of Bank Accounts");
		outFile.printf("");
		outFile.println("|***************************************************************************************|");
		outFile.printf("%-15s|%-14s|%-11s|%-12s|%-18s|%-13s|", "|Account Number", "Balance", "First", "Last", "SSN",
				"Account Type");
		outFile.println("");

		for (int index = 0; index < bankOfAmerica.getActiveAccounts(); index++) {
			tempAcc = tempAccs[index];
			tempDep = tempAcc.getDepositor();
			tempName = tempDep.getName();

			outFile.printf("%-15d|", tempAcc.getAccountNumber());
			outFile.printf("%-14.2f|", tempAcc.getBalance());
			outFile.printf("%-11s|", tempName.getFirst());
			outFile.printf("%-12s|", tempName.getLast());
			outFile.printf("%-18s|", tempDep.getSsn());
			outFile.printf("%-13s|", tempAcc.getAccountType());
			outFile.println("");
		}
		outFile.println("|***************************************************************************************|");
		outFile.println();

		// flush the output file
		outFile.flush();
	}

	/*
	 * TODO Add method description
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
	 * Method Desc: Asks the user for their account number. If the account is found
	 * it will then ask for the amount to deposit. If that amount is valid it will
	 * deposit into the account.
	 */

	public static void withdrawal(Bank bank, PrintWriter outFile, Scanner kybd) {
		int requestedAccount;
		int index;
		double amountToWithdraw;
		Account[] accounts = bank.getAccounts();

		System.out.println();
		System.out.print("Enter the account number: "); // prompt for account number
		requestedAccount = kybd.nextInt(); // read-in the account number

		// call findAcct to search if requestedAccount exists
		index = bank.findAcctIndex(requestedAccount);

		if (index == -1) // invalid account
		{
			outFile.println("|************************************|");
			outFile.println("Transaction Requested: Withdrawal");
			outFile.println("Error: Account number " + requestedAccount + " does not exist");
			outFile.println("|************************************|");
			outFile.println("");
		} else // valid account
		{
			System.out.print("Enter amount to withdraw: "); // prompt for amount to withdraw
			amountToWithdraw = kybd.nextDouble(); // read-in the amount to withdraw

			if (amountToWithdraw <= 0.00 || amountToWithdraw > accounts[index].getBalance()) { // Checks for invalid
																								// amount to
				// withdraw
				outFile.println("|************************************|");
				outFile.println("Transaction Requested: Withdrawal");
				outFile.println("Account Number: " + requestedAccount);
				if (amountToWithdraw > accounts[index].getBalance()) {
					outFile.printf("You have $%.2f in your account.\n", accounts[index].getBalance());
					outFile.println("Insufficient Funds.");
					System.out.printf("You have $%.2f in your account.\n", accounts[index].getBalance());
					System.out.println("Insufficient Funds.");
					System.out.println("");
					outFile.println("|************************************|");
					outFile.println("");
				}
				outFile.printf("Error: $%.2f is an invalid amount\n", amountToWithdraw);
				outFile.println("|************************************|");
				outFile.println("");
			} else {
				outFile.println("|************************************|");
				outFile.println("Transaction Requested: Withdrawal");
				outFile.println("Account Number: " + requestedAccount);
				outFile.printf("Old Balance: $%.2f\n", accounts[index].getBalance());
				outFile.println();
				outFile.println("Amount to Withdral: $" + amountToWithdraw);
				accounts[index].setBalance(accounts[index].getBalance() - amountToWithdraw);
				; // make the deposit
				outFile.printf("New Balance: $%.2f\n", accounts[index].getBalance());
				outFile.println("|************************************|");
				outFile.println("");
			}
		}

		outFile.flush(); // flush the output buffer

	}
}
