package com.meritamerica.assignment4;

import java.io.*;



public class MeritBank {
	
	public static AccountHolder[] accountHolders;
	public static CDOffering[] CDOfferings;
	public static long nextAccountNumber = 12345001;
	private static FraudQueue fraudQueue;
	
	
	
	public static boolean readFromFile(String fileName) {
		
		boolean resoseultes = true;
		try {
			BufferedReader bufRead = new BufferedReader(new FileReader(fileName));
				
		
			
				
				int tempIntForProssessing;
				
				setNextAccountNumber(Long.parseLong(bufRead.readLine()));
				
				
				tempIntForProssessing = Integer.parseInt(bufRead.readLine());
				clearCDOfferings();
				CDOffering[] temp = new CDOffering[tempIntForProssessing];				
				for(int i = 0 ; i < tempIntForProssessing ; i++) {
					
					temp[i] = CDOffering.readFromString(bufRead.readLine());									
				}				
				setCDOfferings(temp);
				
				
				
				tempIntForProssessing = Integer.parseInt(bufRead.readLine());
				accountHolders = null;
				for(int i = 0 ; i < tempIntForProssessing ; i++){
					addAccountHolder(AccountHolder.readFromString(bufRead.readLine()));
					int secoundTempIntForProssessing = Integer.parseInt(bufRead.readLine());
					for(int j = 0 ; j < secoundTempIntForProssessing ; j++){
						CheckingAccount ch = CheckingAccount.readFromString(bufRead.readLine());
						int thirdTempIntForProssessing = Integer.parseInt(bufRead.readLine());
						for(int k = 0 ; k < thirdTempIntForProssessing ; k++) {
						ch.addTransaction(Transaction.readFromString(bufRead.readLine() , ch));
						}
						BankAccount[] baa = {ch};
						accountHolders[i].setBankAccounts(baa);
					}
					secoundTempIntForProssessing = Integer.parseInt(bufRead.readLine());
					for(int j = 0 ; j < secoundTempIntForProssessing ; j++){
						SavingsAccount ch = SavingsAccount.readFromString(bufRead.readLine());
						int thirdTempIntForProssessing = Integer.parseInt(bufRead.readLine());
						for(int k = 0 ; k < thirdTempIntForProssessing ; k++) {
						ch.addTransaction(Transaction.readFromString(bufRead.readLine() , ch));
						}
						BankAccount[] baa = {ch};
						accountHolders[i].setBankAccounts(baa);
					}
					secoundTempIntForProssessing = Integer.parseInt(bufRead.readLine());
					for(int j = 0 ; j < secoundTempIntForProssessing ; j++){
						CDAccount ch = CDAccount.readFromString(bufRead.readLine());
						int thirdTempIntForProssessing = Integer.parseInt(bufRead.readLine());
						for(int k = 0 ; k < thirdTempIntForProssessing ; k++) {
						ch.addTransaction(Transaction.readFromString(bufRead.readLine() , ch));
						}
						BankAccount[] baa = {ch};
						accountHolders[i].setBankAccounts(baa);
					}
					
				}
			
			bufRead.close();
		} catch (IOException e) {
			e.printStackTrace();
			resoseultes = false;
		}catch(NumberFormatException exception) {
			resoseultes = false;
		}
		return resoseultes;
	}
	
	
	
	static boolean writeToFile(String fileName) {
		try {
			
			BufferedWriter bufWrite = new BufferedWriter(new FileWriter(fileName));
			StringBuilder lastSB = new StringBuilder(getNextAccountNumberForWriteToFileOnly() + "\n" + CDOfferings.length + "\n");
			for(CDOffering cdO : CDOfferings){
				lastSB.append(cdO.writeToString() + "\n");
			}
			lastSB.append(accountHolders.length + "\n");
			for(AccountHolder sbac : accountHolders) {
				lastSB.append(sbac.writeToString() + "\n");
			}
			String toBeWritten = lastSB.toString();
			System.out.println(toBeWritten);
			//put writer here
			bufWrite.write(toBeWritten);
			
			bufWrite.close();
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
		return true;
		
	}

	
	
	
	
	
	static void addAccountHolder(AccountHolder accountHolder){
		if(accountHolders == null){
			AccountHolder[] freshstart = {accountHolder};
			accountHolders = freshstart;
		}else{			
			AccountHolder[] temp = new  AccountHolder[accountHolders.length + 1];
			for(int i = 0 ; i < accountHolders.length ; i++){
				temp[i] = accountHolders[i];				
			}
			temp[accountHolders.length] = accountHolder;
			accountHolders = temp;
				
		}		
	}
	
	
	static AccountHolder[] getAccountHolders(){
		return accountHolders;
	}
	
	public static AccountHolder[] sortAccountHolders() {
		return accountHolders = AccountHolder.sortAccounts(accountHolders);
		
	}
	
	public static CDOffering[] getCDOfferings(){
		return CDOfferings;
	}
	
	static long getNextAccountNumber(){
		long tempNAC = nextAccountNumber;
		nextAccountNumber++;
		return tempNAC;
	}
	
	static long getNextAccountNumberForWriteToFileOnly() {
		return nextAccountNumber;
	}
	
	
	public static void setNextAccountNumber(long nextAccountNumbe) {
		nextAccountNumber = nextAccountNumbe;
		
	}
			
	
	
	//static CDOffering getBestCDOffering(double depositAmount){}
	
	
	//static CDOffering getSecondBestCDOffering(double depositAmount){}
	
	
	static void clearCDOfferings(){
		CDOfferings = null;
	}
	
	
	static void setCDOfferings(CDOffering[] offerings){
		if(CDOfferings == null){
			CDOfferings = offerings;
		}else{
			
			CDOffering[] temp = new  CDOffering[CDOfferings.length + offerings.length];
			
			for(int i = 0 ; i < CDOfferings.length ; i++){
				temp[i] = CDOfferings[i];
			}
			
			for(int i = CDOfferings.length ; i < CDOfferings.length + offerings.length ; i++){
			    temp[i] = offerings[i - CDOfferings.length];
			}
			CDOfferings = temp;
		}
				
	}
	
	public static double recursiveFutureValue(double amount, int years, double interestRate) {
		

		double p = amount;
		double interest = interestRate;
		int n = years;
		
		
		if(n == 0){
		return p;
		}
		return (1 + interest) * (recursiveFutureValue(p , n -1, interest));  
	
		
		
		
	}
	
	
	public static boolean processTransaction(Transaction transaction) throws NegativeAmountException, ExceedsAvailableBalanceException, ExceedsFraudSuspicionLimitException {
		
		boolean result = true;
		double test = transaction.getAmount();
		BankAccount target = transaction.getTargetAccount();
		
		if(test > FraudQueue.getExcessiveAmount()){
			fraudQueue.addTransaction(transaction);
			throw new ExceedsFraudSuspicionLimitException();
			
		}else if(test < 0){
			result = false;
			throw new NegativeAmountException();
			
		}else{
			if(transaction.getSourceAccount() != null){
				double testie = transaction.getSourceAccount().getBalance();
				if(testie < test) {
					result = false;
					throw new ExceedsAvailableBalanceException();
				}	
			}
			
		}
		target.addTransaction(transaction);
		return result;
	}
	
	
	public static FraudQueue getFraudQueue() {
		return fraudQueue;
	}
	
	
	public BankAccount getBankAccount(long accountId) {
		
		
		BankAccount[] tempBankAccountArray = null;
		
		for(int i = 0 ; i < accountHolders.length ; i++) {			
				BankAccount[] temptempBankAccountArray = accountHolders[i].getBankAccounts();
				
				if(tempBankAccountArray == null){					 
					tempBankAccountArray = temptempBankAccountArray;
				}else{			
					BankAccount[] temp = new  BankAccount[temptempBankAccountArray.length + tempBankAccountArray.length];
					for(int j = 0 ; j < tempBankAccountArray.length ; j++){
						temp[j] = tempBankAccountArray[j];				
					}
					for(int j = tempBankAccountArray.length ; j < temp.length ; j++){
						temp[i] = temptempBankAccountArray[j - tempBankAccountArray.length];				
					}

					
					
				tempBankAccountArray = temp;
						
				}
		 }
		 BankAccount temp = null;
			for(int i = 0 ; i < tempBankAccountArray.length ; i++) {
				if(tempBankAccountArray[i].getAccountNumber() == accountId) {
				temp = tempBankAccountArray[i];
			    }			
		    }		
		 return temp;
				
			
		}
		
		
				
	
	
	public static void addToFraudQueue(Transaction transaction) {
		if(fraudQueue == null){
			fraudQueue = new FraudQueue();
		}
		fraudQueue.addTransaction(transaction);
	}
	
	

	
	//static double totalBalances(){}
	
	
	//static double futureValue(double presentValue, double interestRate, int term){}

	
	

}
