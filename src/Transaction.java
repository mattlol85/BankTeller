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
	boolean sucess;
	
	
	
	public Transaction() {
		transactionType = "";
		transactionAmount = 0.0;
	}
	
	public Transaction(String transType,Double reqAmount) {
		transactionType = transType;
		transactionAmount = reqAmount;
	}
	
	//Setters
	public void setTransactionType(String t) {
		 transactionType = t;
	}
	public void setTransactionAmount(Double ta) {
		transactionAmount = ta;
	}
	public void setSuccess(Boolean s) {
		sucess = s;
	}
	
	//getters
	public String getTransactionType() {
		return transactionType;
	}
	public double getTransactionAmount() {
		return transactionAmount;
	}
	public boolean getTransactionSuccess() {
		return sucess;
	}
	
	public void newAcctTrans(String transType,boolean s) {
		
	}
	public void depositTrans(String transType,Double reqAmount,boolean s) {
		
	}
	public void withdrawalTrans(String transType,Double reqAmount,boolean s) {
		
	}
	public void balanceTrans(String transType) {
		
	}
	
}
