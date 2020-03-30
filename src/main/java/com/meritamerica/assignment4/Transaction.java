package com.meritamerica.assignment4;

public abstract class Transaction {
	
	private BankAccount sourceAccount;
	private BankAccount targetAccount;
	private double amount;
	private java.util.Date transactionDate;
	
	
	public Transaction(BankAccount targetAccount , double amount) {
		
		this.targetAccount = targetAccount;
		this.amount = amount;
		this.transactionDate = new java.util.Date();
		
	} 
	
	public Transaction(BankAccount sourceAccount , BankAccount targetAccount , double amount) {
		
		this.sourceAccount = sourceAccount;
		this.targetAccount = targetAccount;
		this.amount = amount;
		this.transactionDate = new java.util.Date();
		
	}
	
	
	
	
	
	//getters and setters
	public BankAccount getSourceAccount() {
		return sourceAccount;
	}
	
	public void setSourceAccount(BankAccount sourceAccount) {
		this.sourceAccount = sourceAccount;
	}
	
	public BankAccount getTargetAccount() {
		return targetAccount;
	}
	
	public void setTargetAccount(BankAccount targetAccount) {
		this.targetAccount = targetAccount;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	public java.util.Date getTransactionDate() {
		return transactionDate;
	}
	
	public void setTransactionDate(java.util.Date transactionDate) {
		this.transactionDate = transactionDate;
	}
		
	
		
	public String writeToString() {
		
	}
	
	
	public static Transaction readFromString(String transactionDataString) {
		
	}
	
	


}
