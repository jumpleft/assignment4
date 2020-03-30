package com.meritamerica.assignment4;

public class MeritAmericaBankApp {
	public static void main(String[] args) {
		MeritBank.readFromFile("src/test/testMeritBank_good.txt");
	
	
	double results = MeritBank.recursiveFutureValue(100, 3, .01);
	System.out.println(results);
	
	}
	
}