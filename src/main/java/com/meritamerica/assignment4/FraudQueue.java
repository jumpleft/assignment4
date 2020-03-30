package com.meritamerica.assignment4;

public class FraudQueue {
	
	private List<Transaction> fq;
	public static final double excessiveAmount = 1000;
	
	public FraudQueue() {
		
	}
	
	
	public void addTransaction(Transaction transaction) {
		fq.enqueue(transaction);
	}
	
	
	public Transaction getTransaction() {
		return fq.dequeue();
		
	}
	
	public static double getExcessiveAmount() {
		return excessiveAmount;
	}


}
