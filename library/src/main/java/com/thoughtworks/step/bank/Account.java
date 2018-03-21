package com.thoughtworks.step.bank;

import java.util.Objects;

public class Account {
  private final String accountNumber;
  private final String accountHolder;
  private double balance;
  
  public Account(String accountHolder,String accountNumber, double balance) throws MinimumBalanceException, InvalidAccountNumberException {
    this.accountHolder = accountHolder;
    
    this.accountNumber = accountNumber;
    if(!validAmountForDebit(balance)){
      throw new MinimumBalanceException("Insufficient minimum balance!");
    }
    this.balance = balance;
  }
  
  private boolean canDebit(double amount){
    double balanceAfter = getBalance()-amount;
    return validAmountForDebit(balanceAfter);
  }
  
  private boolean validAmountForDebit(double balance) {
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
      return balance;
    }
    throw new MinimumBalanceException("Invalid credit request!");
  }
  
  public String getSummary() {
    return "Account{" +
            "accountNumber='" + accountNumber + '\'' +
            ", accountHolder='" + accountHolder + '\'' +
            ", balance=" + balance +
            '}';
  }
  
 
}
