package com.meritamerica.assignment4;

public class ExceedsAvailableBalanceException extends Exception {
	
	public ExceedsAvailableBalanceException() {}
	
	public ExceedsAvailableBalanceException(String message) {
		super(message);
	}

}
