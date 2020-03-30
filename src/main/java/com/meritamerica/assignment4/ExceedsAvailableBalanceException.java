package com.meritamerica.assignment4;

@SuppressWarnings("serial")
public class ExceedsAvailableBalanceException extends Exception {
	
	public ExceedsAvailableBalanceException() {}
	
	public ExceedsAvailableBalanceException(String message) {
		super(message);
	}

}
