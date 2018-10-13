/**
 * A Transaction class which consists of a transaction type 
 * (e.g., create, deposit, withdrawal, balance, etc.) and a
 *  transaction amount (where applicable)
 */

/**
 * @author Matthew
 *
 */
public class Transaction {
	private String transactionType;
	private double transactionAmount;
	boolean success;

	// No-Arguments
	public Transaction() {
		transactionType = "";
		transactionAmount = 0.0;
		success = false;
	}

	// With Arguments
	public Transaction(String transType, Double reqAmount, boolean s) { // For deposit/withdraw
		transactionType = transType;
		transactionAmount = reqAmount;
		success = s;
	}

	public Transaction(String transType, boolean s) { // For Open/Close Account
		transactionType = transType;
		success = s;
	}

	// Setters
	public void setTransactionType(String t) {
		transactionType = t;
	}

	public void setTransactionAmount(Double ta) {
		transactionAmount = ta;
	}

	public void setSuccess(Boolean s) {
		success = s;
	}

	// getters
	public String getTransactionType() {
		return transactionType;
	}

	public double getTransactionAmount() {
		return transactionAmount;
	}

	public boolean getTransactionSuccess() {
		return success;
	}
}
