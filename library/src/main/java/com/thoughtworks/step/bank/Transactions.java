package com.thoughtworks.step.bank;

import java.util.ArrayList;

public class Transactions {
  protected ArrayList<Transaction> allTransactions;
  
  public Transactions() {
    this.allTransactions = new ArrayList<>();
  }
  
  public void debit(double amount, String to) {
    this.allTransactions.add(new DebitTransaction(amount,to));
  }
}
