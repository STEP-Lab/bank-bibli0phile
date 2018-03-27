package com.thoughtworks.step.bank;

import java.io.PrintWriter;
import java.util.ArrayList;

public class Transactions {
  private ArrayList<Transaction> allTransactions;
  
  public Transactions() {
    this.allTransactions = new ArrayList<>();
  }
  
  public void debit(double amount, AccountNumber to) {
    this.allTransactions.add(new DebitTransaction(amount,to));
  }
  public void credit(double amount, AccountNumber to) {
    this.allTransactions.add(new CreditTransaction(amount,to));
  }
  
  public ArrayList<Transaction> getAllTransactions() {
    return allTransactions;
  }
  
  public Transactions filterByAmountGreaterThan(double amount) {
    Transactions transactions = new Transactions();
    for (Transaction transaction : allTransactions) {
      if(transaction.getAmount()>=amount) {
        transactions.allTransactions.add(transaction);
      }
    }
    return transactions;
  }
  
  public void print(PrintWriter writer) {
    for (Transaction transaction : allTransactions) {
      writer.println(transaction.toString());
    }
  }
}
