package com.thoughtworks.step.bank;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;

public class Transactions {
  private ArrayList<Transaction> allTransactions;
  
  public Transactions() {
    this.allTransactions = new ArrayList<>();
  }
  
  public void debit(double amount, AccountNumber to,String type) {
    this.allTransactions.add(new DebitTransaction(new Date(), amount,to,type));
  }
  public void credit(double amount, AccountNumber to,String type) {
    this.allTransactions.add(new CreditTransaction(amount,to,type));
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
  
  
  public Transactions filterByAmountLesserThan(double amount) {
    Transactions transactions = new Transactions();
    for (Transaction transaction : allTransactions) {
      if(transaction.getAmount()<amount) {
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
  
  public Transactions filterAllCreditTransactions() {
    Transactions transactions = new Transactions();
    for (Transaction transaction : allTransactions) {
      if (transaction.getType().equals("credit")) {
        transactions.allTransactions.add(transaction);
      }
    }
    return transactions;
  }
  
  public Transactions filterAllDebitTransactions() {
    Transactions transactions = new Transactions();
    for (Transaction transaction : allTransactions) {
      if (transaction.getType().equals("debit")) {
        transactions.allTransactions.add(transaction);
      }
    }
    return transactions;
  }
  public void writeOnTransactions(CSVPrinter printer) throws IOException {
    for(Transaction transaction : allTransactions){
      printer.iterateOverTransactions(transaction);
    }
  }
}
