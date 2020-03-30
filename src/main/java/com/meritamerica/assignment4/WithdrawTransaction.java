package com.meritamerica.assignment4;

public class WithdrawTransaction extends Transaction {
	
	WithdrawTransaction(BankAccount targetAccount, double amount) {
		super(targetAccount , amount);
	}
	
	WithdrawTransaction(BankAccount targetAccount, double amount , java.util.Date dateToBeAdded) {
		super(targetAccount , amount , dateToBeAdded);
	}

}
