package com.meritamerica.assignment4;

@SuppressWarnings("serial")
public class NegativeAmountException extends Exception {
	
	public NegativeAmountException() {}
	
	public NegativeAmountException(String message) {
		super(message);
	}

}
