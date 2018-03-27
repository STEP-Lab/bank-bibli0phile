package com.thoughtworks.step.bank;


import java.util.ArrayList;

public class Account {
  private final AccountNumber accountNumber;
  private final String accountHolder;
  private Transactions transactions;
  private double balance;
  
  private Account(String accountHolder, AccountNumber accountNumber, double balance){
    this.accountHolder = accountHolder;
    this.accountNumber = accountNumber;
    this.balance = balance;
    this.transactions = new Transactions();
  }
  
  public static Account createAccount(String accountHolder, AccountNumber accountNumber, double balance) throws MinimumBalanceException {
    if(!validAmountForDebit(balance)){
      throw new MinimumBalanceException("Insufficient minimum balance!");
    }
    return new Account(accountHolder, accountNumber, balance);
  }
  
  private boolean canDebit(double amount){
    double balanceAfter = getBalance()-amount;
    return validAmountForDebit(balanceAfter);
  }
  
  private static boolean validAmountForDebit(double balance) {
    return balance > 1000;
  }
  
  public double getBalance() {
    return balance;
  }
  

  public String getAccountHolder() {
    return accountHolder;
  }
  
  public double debit(double amount) throws MinimumBalanceException {
    if(canDebit(amount)) {
      balance -= amount;
      transactions.debit(amount,accountNumber,"debit");
      return balance;
    }
    throw new MinimumBalanceException("Cannot process your request due to minimum balance!");
  }
  
  private boolean canCredit(double amount){
    return amount > 0;
  }
  
  public double credit(double amount) throws MinimumBalanceException {
    if(canCredit(amount)) {
      balance += amount;
      transactions.credit(amount,accountNumber,"credit");
      return balance;
    }
    throw new MinimumBalanceException("Invalid credit request!");
  }
  
  public ArrayList<Transaction> getAllTransactions() {
    return transactions.getAllTransactions();
  }

}
